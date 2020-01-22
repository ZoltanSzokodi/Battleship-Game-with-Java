package com.codeoftheweb.salvo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.*;

// The controller holds the methods to handle requests to and from the API.
@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:3000")
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

    @Autowired
    private PasswordEncoder passwordEncoder;

    // post method for created a new player with their desired login credentials.
    @RequestMapping(path = "/players", method = RequestMethod.POST)
    public ResponseEntity<Object> register(
            @RequestParam String userName,
            @RequestParam String email,
            @RequestParam String password) {

        if (userName.isEmpty() || email.isEmpty() || password.isEmpty()) {
            return new ResponseEntity<>("Missing credentials", HttpStatus.FORBIDDEN);
        }

        if(playerRepository.findByUserName(userName) != null) {
            return new ResponseEntity<>("name already in use", HttpStatus.FORBIDDEN);
        }

        if (playerRepository.findByEmail(email) !=  null) {
            return new ResponseEntity<>("email already in use", HttpStatus.FORBIDDEN);
        }
        // one-way-encryption
        playerRepository.save(new Player(userName, email, passwordEncoder.encode(password)));
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    // structuring the api route for all the games
    @RequestMapping("/games")
    public Map<String, Object> getGames() {

        Map<String, Object> games = new LinkedHashMap<>();

        List<Map> gamesList = new ArrayList<>();

        // loop through every game in the database
        gameRepository.findAll().forEach(currentGame -> {
            // create a map for each individual game
            Map<String, Object> gameMap = new LinkedHashMap<>();

            // create a gamePlayers list within each game
            List<Map> gamePlayersList = new ArrayList<>();

            // add the key value pairs to the individual game maps - first nesting within a game map
            gameMap.put("gameID", currentGame.getID());
            gameMap.put("gameCreated", currentGame.getGameCreated());
            gameMap.put("gamePlayers", gamePlayersList);
            gameMap.put("scores", currentGame.getScores());

            // loop through the gamePlayers in each game
            gamePlayerRepository.findAll().forEach(currentGamePlayer -> {

                // create a gamePlayer map for each game player within the gamePlayers list
                Map<String, Object> gamePlayerMap = new LinkedHashMap<>();

                // add the gamePlayer_ID key - value pair to the gamePlayer map
                gamePlayerMap.put("gamePlayerID", currentGamePlayer.getID());

                // loop through the players in each game
                if (currentGame.getGamePlayers().contains(currentGamePlayer)) {
                    playerRepository.findAll().forEach(currentPlayer -> {

                        // for each gamePlayer map create the player map
                        Map<String, Object> player = new LinkedHashMap<>();

                        if (currentGamePlayer.getPlayer().getUserName().equals(currentPlayer.getUserName())) {

                            // add the key - value pairs to the player map - second nesting within a game map
                            player.put("playerID", currentPlayer.getID());
                            player.put("playerName", currentPlayer.getUserName());
                            player.put("playerEmail", currentPlayer.getEmail());

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
        games.put("leaderboard", getLeaderboard());

        return games;
    }

    // API end point to see the relevant information about each game they are in, including
    // shots, turns, score, etc. This information is scoped with authentication to
    // prevent players from seeing opposing players information
    @RequestMapping("/game_view/{gamePlayerID}")
    public Map<String, Object> getGameViewForGamePlayer(@PathVariable long gamePlayerID) {

            Map<String, Object> gamePlayerMap = new LinkedHashMap<>();

            // make a map of a game players game information.
            gamePlayerMap.put("gamePlayerID", gamePlayerRepository.getOne(gamePlayerID).getID());
            gamePlayerMap.put("playerName", gamePlayerRepository.getOne(gamePlayerID).getPlayer().getUserName());
            gamePlayerMap.put("ships", getGamePlayerShips(gamePlayerRepository.getOne(gamePlayerID)));
            gamePlayerMap.put("salvos", getGamePlayerSalvos(gamePlayerRepository.getOne(gamePlayerID)));

            // opponent information used for development purposes
            gamePlayerMap.put("opponentInfo", getOpponentInfo(gamePlayerRepository.getOne(gamePlayerID)));

            return gamePlayerMap;
    }

    // method to return all of the ships from a specific gamePlayer. To use in other methods
    // request mapping API endpoints
    public List<Map> getGamePlayerShips(GamePlayer gamePlayer) {

        List<Map> shipsList = new ArrayList<>();

        gamePlayer.getShips().forEach(ship -> {

            Map<String, Object> shipMap = new LinkedHashMap<>();

            shipMap.put("shipID", ship.getID());
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

            shipMap.put("salvoID", salvo.getID());
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

        you.getGame().getGamePlayers().forEach(player -> {
            if (player.getID() != you.getID()){

                opponentMap.put("gamePlayerID", player.getID());
                opponentMap.put("opponentName", player.getPlayer().getUserName());
                opponentMap.put("opponentSalvos", getGamePlayerSalvos(player));
                opponentMap.put("opponentShips", getGamePlayerShips(player));
            }
        });
        return opponentMap;
    }

    public List<Object> getLeaderboard() {
        List<Object> scoreList = new ArrayList<>();

        playerRepository.findAll().forEach(player -> {

            Map<String, Object> playersScore = new LinkedHashMap<>();

            playersScore.put("playerName", player.getUserName());
            playersScore.put("totalPoints", player.getScores().stream().map(Score::getScoreValue).reduce((double) 0, Double::sum));
            playersScore.put("gamesWon", player.getScores().stream().filter(score -> score.getScoreValue() == 1).count());
            playersScore.put("gamesLost", player.getScores().stream().filter(score -> score.getScoreValue() == 0).count());
            playersScore.put("tiedGames", player.getScores().stream().filter(score -> score.getScoreValue() == 0.5).count());

            scoreList.add(playersScore);
        });
        return scoreList;
    }
}