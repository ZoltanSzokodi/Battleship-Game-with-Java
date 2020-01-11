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

    // structuring the api route for all the games
    @RequestMapping("/games")
    public Map<String, Object> getGames() {

        // this map contains the games list - top level map (object)
        Map<String, Object> games = new LinkedHashMap<>();

        // this list contains all the individual game maps (objects)
        List<Object> gamesList = new ArrayList<>();

        // this set contains all the users currently playing. Each user might be involved in multiple games.
        // THIS SET IS FOR TESTING PURPOSES
        Set<Object> playersSet = new HashSet<>();

        // loop through every game in the database
        gameRepository.findAll().forEach(currentGame -> {
            // create a map for each individual game
            Map<String, Object> gameMap = new LinkedHashMap<>();

            // create a gamePlayers list within each game
            List<Object> gamePlayersList = new ArrayList<>();

            // add the key value pairs to the individual game maps - first nesting within a game map
            gameMap.put("game_ID", currentGame.getId());
            gameMap.put("created", currentGame.getGameCreated());
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
        games.put("games", gamesList);

        // FOR TESTING - users currently playing
        // games.put("players", playersSet);

        return games;
    }

    // structuring the api route for information about a specific game from a specific game player's point of view
    @RequestMapping("/game_view/{gameID}")
    public Map<String, Object> getGameView(@PathVariable long gameID) {

        // the map for the actual game
        Map<String, Object> gameMap = new LinkedHashMap<>();

        // the gamePlayers list containing the gamePlayerMaps
        List<Object> gamePlayersList = new ArrayList<>();

        // check if the requested game's ID (currentGame) matches any of the  games in the gameRepository
        gameRepository.findAll().forEach(currentGame -> {
            if (currentGame.getId() == gameID) {

                gameMap.put("game_ID", currentGame.getId());
                gameMap.put("created", currentGame.getGameCreated());
                gameMap.put("gamePlayers", gamePlayersList);

                currentGame.getGamePlayers().forEach(currentGamePlayer -> {

                    // create a map for each individual gamePlayer
                    Map<String, Object> gamePlayerMap = new LinkedHashMap<>();

                    // map relevant information about the game into the newly created gamePlayerMap
                    gamePlayerMap.put("gamePlayer_ID", currentGamePlayer.getId());
                    // singleGamePlayerMap.put("ships", getShipsfromGamePlayer(gamePlayer));
                    // singleGamePlayerMap.put("salvoes", getSalvoesfromGamePlayer(gamePlayer));

                    // loop through the players in each game
                    if(currentGame.getGamePlayers().contains(currentGamePlayer)) {
                        playerRepository.findAll().forEach(currentPlayer -> {

                            // for each gamePlayer map create the player map
                            Map<String, Object> player = new LinkedHashMap<>();

                            if (currentGamePlayer.getPlayer().getUserName().equals(currentPlayer.getUserName())) {

                                // add the key - value pairs to the player map - second nesting within a game map
                                player.put("player_ID", currentPlayer.getId());
                                player.put("player_name", currentPlayer.getUserName());
                                player.put("player_email", currentPlayer.getEmail());

                                // add the player map to the gamePlayer map
                                gamePlayerMap.put("player", player);
                            }
                        });
                        // push the individual gamePlayer map in the gamePlayers list
                        gamePlayersList.add(gamePlayerMap);
                    }
                });
            }
        });
        return gameMap;
    }
}
