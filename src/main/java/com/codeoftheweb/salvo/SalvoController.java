package com.codeoftheweb.salvo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

// The controller holds the methods to handle requests to and from the API.
@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api")
public class SalvoController {

    @Autowired
    private GameRepository gameRepository;

    @Autowired
    private PlayerRepository playerRepository;

    @Autowired
    private GamePlayerRepository gamePlayerRepository;

    @Autowired
    private ShipRepository shipRepository;

    @Autowired
    private SalvoRepository salvoRepository;

    @Autowired
    private ScoreRepository scoreRepository;

    // structuring the api route for all the games
    @RequestMapping("/games")
    public List<Map> getGames() {

        // this map contains the games list - top level map (object)
        //Map<String, Object> games = new LinkedHashMap<>();

        // this list contains all the individual game maps (objects)
        List<Map> gamesList = new ArrayList<>();

        // this set contains all the users currently playing. Each user might be involved in multiple games.
        // THIS SET IS FOR TESTING PURPOSES
        Set<Map> playersSet = new HashSet<>();

        // loop through every game in the database
        gameRepository.findAll().forEach(currentGame -> {
            // create a map for each individual game
            Map<String, Object> gameMap = new LinkedHashMap<>();

            // create a gamePlayers list within each game
            List<Map> gamePlayersList = new ArrayList<>();

            // add the key value pairs to the individual game maps - first nesting within a game map
            gameMap.put("game_ID", currentGame.getId());
            gameMap.put("game_created", currentGame.getGameCreated());
            gameMap.put("gamePlayers", gamePlayersList);

            // loop through the gamePlayers in each game
            gamePlayerRepository.findAll().forEach(currentGamePlayer -> {

                // create a gamePlayer map for each game player within the gamePlayers list
                Map<String, Object> gamePlayerMap = new LinkedHashMap<>();

                // add the gamePlayer_ID key - value pair to the gamePlayer map
                gamePlayerMap.put("gamePlayer_ID", currentGamePlayer.getId());

                // loop through the players in each game
                if (currentGame.getGamePlayers().contains(currentGamePlayer)) {
                    playerRepository.findAll().forEach(currentPlayer -> {

                        // for each gamePlayer map create the player map
                        Map<String, Object> player = new LinkedHashMap<>();

                        if (currentGamePlayer.getPlayer().getUserName().equals(currentPlayer.getUserName())) {

                            // add the key - value pairs to the player map - second nesting within a game map
                            player.put("player_ID", currentPlayer.getId());
                            player.put("player_name", currentPlayer.getUserName());
                            player.put("player_email", currentPlayer.getEmail());

                            // FOR TESTING - add users currently playing to the playersMapSet
                            playersSet.add(player);

                            // add the player map to the gamePlayer map
                            gamePlayerMap.put("player", player);
                        }
                    });
                    // push the individual gamePlayer map in the gamePlayers list
                    gamePlayersList.add(gamePlayerMap);
                }
            });
            // push each individual game in the gamesList
            gamesList.add(gameMap);
        });
        // add the gamesList to the games map
        //games.put("games", gamesList);

        // FOR TESTING - users currently playing
        // games.put("players", playersSet);

        return gamesList;
    }

    // API end point to see the relevent information about each game they are in, including
    // shots, turns, score, etc. This information is scoped with authentication to
    // prevent players from seeing opposing players information
    @RequestMapping("/game_view/{gamePlayer_ID}")
    public Map<String, Object> getGameViewForGamePlayer(@PathVariable long gamePlayer_ID) {

            Map<String, Object> gamePlayerMap = new LinkedHashMap<>();

            //Map<String, Object> gpOBJ = new LinkedHashMap<>();

            // make a map of a game players game information.
            gamePlayerMap.put("gamePlayer_ID", gamePlayerRepository.getOne(gamePlayer_ID).getId());
            gamePlayerMap.put("player_name", gamePlayerRepository.getOne(gamePlayer_ID).getPlayer().getUserName());
            gamePlayerMap.put("ships", getGamePlayerShips(gamePlayerRepository.getOne(gamePlayer_ID)));
            gamePlayerMap.put("salvos", getGamePlayerSalvos(gamePlayerRepository.getOne(gamePlayer_ID)));
            //gpOBJ.put("gamePlayer", gamePlayerMap);

            // opponent information used for development purposes
            //Map<String, Object> gpViewMap = new HashMap<>();
            gamePlayerMap.put("opponent_info", getOpponentInfo(gamePlayerRepository.getOne(gamePlayer_ID)));
            //gpViewMap.put("gamePlayer", gpOBJ);

            return gamePlayerMap;

    }

    // method to return all of the ships from a specific gamePlayer. To use in other methods
    // request mapping API endpoints
    public List<Map> getGamePlayerShips(GamePlayer gamePlayer) {

        List<Map> shipsList = new ArrayList<>();

        gamePlayer.getShips().forEach(ship -> {

            Map<String, Object> shipMap = new LinkedHashMap<>();

            shipMap.put("ship_ID", ship.getId());
            shipMap.put("type", ship.getShipType());
            shipMap.put("location", ship.getLocation());
            shipMap.put("sunk", ship.isSunk());

            shipsList.add(shipMap);
        });
        return shipsList;
    }

    // method to return all of the shots from a specific gamePlayer. To use in other methods
    // request mapping API endpoints
    public List<Object> getGamePlayerSalvos(GamePlayer gamePlayer) {

        List<Object> salvosList = new ArrayList<>();

        gamePlayer.getSalvos().stream().sorted(Comparator.comparing(Salvo::getTurn)).forEach(salvo -> {

            Map<String, Object> shipMap = new HashMap<>();

            shipMap.put("salvo_id", salvo.getId());
            shipMap.put("turn", salvo.getTurn());
            shipMap.put("location", salvo.getLocation());
            salvosList.add(shipMap);
        });
        return salvosList;
    }

    // method to return information about a given gamePlayer's opponents.
    // useful for other API endpoint structuring
    public Map<String, Object> getOpponentInfo (GamePlayer you){

        Map<String, Object> opponentMap = new LinkedHashMap<>();

        you.getGame().getGamePlayers().forEach(opponent -> {
            if (opponent.getId() != you.getId()){

                opponentMap.put("gamePlayer_ID", opponent.getId());
                opponentMap.put("opponent_name", opponent.getPlayer().getUserName());
                opponentMap.put("opponent_salvos", getGamePlayerSalvos(opponent));
                // opponentMap.put("opponent_ships", getGamePlayerShips(opponent));
            }
        });
        return opponentMap;
    }
}