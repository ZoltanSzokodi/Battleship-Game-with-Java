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
			ShipRepository shipRepository) {

		return (args) -> {
			Player player1 = new Player("Peter", "peterGriffin@email.com");
			Player player2 = new Player("Glenn", "glennQuagmire@email.com");
			Player player3 = new Player("Joe", "joeSwanson@email.com");

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
			ship7.setLocation((Arrays.asList("C4", "D4", "E4")));

			Ship ship8 = new Ship("submarine");
			ship8.setLocation((Arrays.asList("E5", "F5", "G5")));

			Game game1 = new Game();
			Game game2 = new Game();
			Game game3 = new Game();

			GamePlayer gamePlayer1 = new GamePlayer(game1, player1);
			GamePlayer gamePlayer2 = new GamePlayer(game2, player2);
			GamePlayer gamePlayer3 = new GamePlayer(game3, player3);
			GamePlayer gamePlayer4 = new GamePlayer(game3, player1);

			gamePlayer1.addShip(ship1);
			gamePlayer1.addShip(ship2);
			gamePlayer2.addShip(ship3);
			gamePlayer2.addShip(ship4);
			gamePlayer3.addShip(ship5);
			gamePlayer3.addShip(ship6);
			gamePlayer4.addShip(ship7);
			gamePlayer4.addShip(ship8);

			playerRepository.save(player1);
			playerRepository.save(player2);
			playerRepository.save(player3);

			gameRepository.save(game1);
			gameRepository.save(game2);
			gameRepository.save(game3);

			gamePlayerRepository.save(gamePlayer1);
			gamePlayerRepository.save(gamePlayer2);
			gamePlayerRepository.save(gamePlayer3);
			gamePlayerRepository.save(gamePlayer4);

			shipRepository.save(ship1);
			shipRepository.save(ship2);
			shipRepository.save(ship3);
			shipRepository.save(ship4);
			shipRepository.save(ship5);
			shipRepository.save(ship6);
			shipRepository.save(ship7);
			shipRepository.save(ship8);
		};
	}
}
