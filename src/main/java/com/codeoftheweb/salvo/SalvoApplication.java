package com.codeoftheweb.salvo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;

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

			player1.setPassword("booze");
			player2.setPassword("police");
			player3.setPassword("giggity");

			playerRepository.save(player1);
			playerRepository.save(player2);
			playerRepository.save(player3);

			Game game1 = new Game();
			Game game2 = new Game();
			Game game3 = new Game();

			gameRepository.save(game1);
			gameRepository.save(game2);
			gameRepository.save(game3);

			GamePlayer gamePlayer1 = new GamePlayer(game1, player1);
			GamePlayer gamePlayer2 = new GamePlayer(game1, player2);
			GamePlayer gamePlayer3 = new GamePlayer(game2, player3);
			GamePlayer gamePlayer4 = new GamePlayer(game2, player1);
			GamePlayer gamePlayer5 = new GamePlayer(game3, player2);
			GamePlayer gamePlayer6 = new GamePlayer(game3, player3);

			gamePlayerRepository.save(gamePlayer1);
			gamePlayerRepository.save(gamePlayer2);
			gamePlayerRepository.save(gamePlayer3);
			gamePlayerRepository.save(gamePlayer4);
			gamePlayerRepository.save(gamePlayer5);
			gamePlayerRepository.save(gamePlayer6);


			Ship ship1 = new Ship("destroyer");
			ship1.setLocation((Arrays.asList("H3", "H2", "H1")));

			Ship ship2 = new Ship("aircraft");
			ship2.setLocation((Arrays.asList("B3", "B4", "B5")));

			Ship ship3 = new Ship("submarine");
			ship3.setLocation((Arrays.asList("A1", "B1", "C1")));

			Ship ship4 = new Ship("jet");
			ship4.setLocation((Arrays.asList("E3", "E4", "E5")));

			Ship ship5 = new Ship("helicopter");
			ship5.setLocation((Arrays.asList("E5", "F5", "G5")));

			Ship ship6 = new Ship("tank");
			ship6.setLocation((Arrays.asList("C5", "C6", "C7")));

			Ship ship7 = new Ship("nuke");
			ship7.setLocation((Arrays.asList("D4", "E4", "F4")));

			Ship ship8 = new Ship("submarine");
			ship8.setLocation((Arrays.asList("E5", "F5", "G5")));

			Ship ship9 = new Ship("aircraft");
			ship9.setLocation((Arrays.asList("D7", "E7", "F7")));

			Ship ship10 = new Ship("aircraft");
			ship10.setLocation((Arrays.asList("G4", "G5", "G6")));

			gamePlayer1.addShip(ship1);
			gamePlayer1.addShip(ship2);

			gamePlayer2.addShip(ship3);
			gamePlayer2.addShip(ship4);

			gamePlayer3.addShip(ship5);
			gamePlayer3.addShip(ship6);

			gamePlayer4.addShip(ship7);
			gamePlayer4.addShip(ship8);

			gamePlayer5.addShip(ship9);
			gamePlayer6.addShip(ship10);

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

			Salvo salvo1 = new Salvo(1);
			salvo1.setLocation((Arrays.asList("E4", "D7")));
			gamePlayer1.addSalvo(salvo1);

			Salvo salvo2 = new Salvo(1);
			salvo2.setLocation((Arrays.asList("H1", "E2")));
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
			salvo6.setLocation((Arrays.asList("D2", "D8")));
			gamePlayer2.addSalvo(salvo6);

			Salvo salvo7 = new Salvo(3);
			salvo7.setLocation((Arrays.asList("B1", "B8")));
			gamePlayer3.addSalvo(salvo7);

			salvoRepository.save(salvo1);
			salvoRepository.save(salvo2);
			salvoRepository.save(salvo3);
			salvoRepository.save(salvo4);
			salvoRepository.save(salvo5);
			salvoRepository.save(salvo6);
			salvoRepository.save(salvo7);


			Score score1 = new Score(player1, game1, 1.0);
			Score score2 = new Score(player2, game1, 0.0);
			Score score3 = new Score(player1, game2, .5);
			Score score4 = new Score(player3, game2, .5);

			scoreRepository.save(score1);
			scoreRepository.save(score2);
			scoreRepository.save(score3);
			scoreRepository.save(score4);
		};
	}
}
