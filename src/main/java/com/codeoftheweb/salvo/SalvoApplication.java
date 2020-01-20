package com.codeoftheweb.salvo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;
import java.util.Collections;

@SpringBootApplication
public class SalvoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SalvoApplication.class, args);
	}

	// Testing the API end points
	@Bean
	public CommandLineRunner initData(
			PlayerRepository playerRepository,
			GameRepository gameRepository,
			GamePlayerRepository gamePlayerRepository,
			ShipRepository shipRepository,
			SalvoRepository salvoRepository,
			ScoreRepository scoreRepository) {

		return (args) -> {

			// base level player data to use for production. Comment out data to start a server without any data.

			// Data includes creating players (w/ username and passwords), games, gameplayers (an instance of a player in a game),
			// ships, and shot locations.

			Player player1 = new Player("Peter", "peterGriffin@email.com");
			Player player2 = new Player("Glenn", "glennQuagmire@email.com");
			Player player3 = new Player("Joe", "joeSwanson@email.com");
			Player player4 = new Player("Cleveland", "clevelandBrown@email.com");

			player1.setPassword("booze");
			player2.setPassword("giggity");
			player3.setPassword("police");
			player4.setPassword("loretta");


			playerRepository.save(player1);
			playerRepository.save(player2);
			playerRepository.save(player3);
			playerRepository.save(player4);

			Game game1 = new Game();
			Game game2 = new Game();
			Game game3 = new Game();

			gameRepository.save(game1);
			gameRepository.save(game2);
			gameRepository.save(game3);

			GamePlayer gamePlayer1 = new GamePlayer(game1, player1);
			GamePlayer gamePlayer2 = new GamePlayer(game1, player2);
			GamePlayer gamePlayer3 = new GamePlayer(game2, player3);
			GamePlayer gamePlayer4 = new GamePlayer(game2, player4);
			GamePlayer gamePlayer5 = new GamePlayer(game3, player3);
			GamePlayer gamePlayer6 = new GamePlayer(game3, player4);

			gamePlayerRepository.save(gamePlayer1);
			gamePlayerRepository.save(gamePlayer2);
			gamePlayerRepository.save(gamePlayer3);
			gamePlayerRepository.save(gamePlayer4);
			gamePlayerRepository.save(gamePlayer5);
			gamePlayerRepository.save(gamePlayer6);


			Ship ship1 = new Ship("aircraft carrier");
			ship1.setLocation((Arrays.asList("H1", "H2", "H3", "H4", "H5", "H6")));

			Ship ship2 = new Ship("battleship");
			ship2.setLocation((Arrays.asList("B3", "B4", "B5", "B6", "B7")));

			Ship ship3 = new Ship("submarine");
			ship3.setLocation((Arrays.asList("A1", "B1", "C1", "D1")));

			Ship ship4 = new Ship("cruiser");
			ship4.setLocation((Arrays.asList("E3", "E4", "E5")));

			Ship ship5 = new Ship("destroyer");
			ship5.setLocation((Arrays.asList("F7", "G7")));

			Ship ship6 = new Ship("jet");
			ship6.setLocation((Collections.singletonList("J9")));


			Ship ship7 = new Ship("aircraft carrier");
			ship7.setLocation((Arrays.asList("E9", "F9", "G9", "H9", "I9", "J9")));

			Ship ship8 = new Ship("battleship");
			ship8.setLocation((Arrays.asList("D1", "E1", "F1", "G1", "H1")));

			Ship ship9 = new Ship("submarine");
			ship9.setLocation((Arrays.asList("E4", "E5", "E6", "E7")));

			Ship ship10 = new Ship("cruiser");
			ship10.setLocation((Arrays.asList("A4", "A5", "A6")));

			Ship ship11 = new Ship("cruiser");
			ship11.setLocation((Arrays.asList("G6", "H6")));

			Ship ship12 = new Ship("jet");
			ship12.setLocation((Collections.singletonList("C8")));


			gamePlayer1.addShip(ship1);
			gamePlayer1.addShip(ship2);
			gamePlayer1.addShip(ship3);
			gamePlayer1.addShip(ship4);
			gamePlayer1.addShip(ship5);
			gamePlayer1.addShip(ship6);

			gamePlayer2.addShip(ship7);
			gamePlayer2.addShip(ship8);
			gamePlayer2.addShip(ship9);
			gamePlayer2.addShip(ship10);
			gamePlayer2.addShip(ship11);
			gamePlayer2.addShip(ship12);

			shipRepository.save(ship1);
			shipRepository.save(ship2);
			shipRepository.save(ship3);
			shipRepository.save(ship4);
			shipRepository.save(ship5);
			shipRepository.save(ship6);
			shipRepository.save(ship7);
			shipRepository.save(ship8);
			shipRepository.save(ship9);
			shipRepository.save(ship10);
			shipRepository.save(ship11);
			shipRepository.save(ship12);

			Salvo salvo1 = new Salvo(1);
			salvo1.setLocation((Arrays.asList("E4", "D7")));
			gamePlayer1.addSalvo(salvo1);

			Salvo salvo2 = new Salvo(1);
			salvo2.setLocation((Arrays.asList("H2", "E2")));
			gamePlayer2.addSalvo(salvo2);

			Salvo salvo3 = new Salvo(2);
			salvo3.setLocation((Arrays.asList("H1", "D3")));
			gamePlayer1.addSalvo(salvo3);

			Salvo salvo4 = new Salvo(2);
			salvo4.setLocation((Arrays.asList("F7", "F8")));
			gamePlayer2.addSalvo(salvo4);

			Salvo salvo5 = new Salvo(3);
			salvo5.setLocation((Arrays.asList("A4", "E6")));
			gamePlayer1.addSalvo(salvo5);

			Salvo salvo6 = new Salvo(3);
			salvo6.setLocation((Arrays.asList("C1", "D8")));
			gamePlayer2.addSalvo(salvo6);

			salvoRepository.save(salvo1);
			salvoRepository.save(salvo2);
			salvoRepository.save(salvo3);
			salvoRepository.save(salvo4);
			salvoRepository.save(salvo5);
			salvoRepository.save(salvo6);


			Score score1 = new Score(player1, game1, .5);
			Score score2 = new Score(player2, game1, .5);

			Score score3 = new Score(player3, game2, 1.0);
			Score score4 = new Score(player4, game2, 0.0);

			Score score5 = new Score(player3, game3, 1.0);
			Score score6 = new Score(player4, game3, 0.0);

			scoreRepository.save(score1);
			scoreRepository.save(score2);
			scoreRepository.save(score3);
			scoreRepository.save(score4);
			scoreRepository.save(score5);
			scoreRepository.save(score6);
		};
	}
}
