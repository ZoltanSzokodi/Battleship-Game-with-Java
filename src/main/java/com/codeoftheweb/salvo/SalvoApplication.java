package com.codeoftheweb.salvo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;

@SpringBootApplication
public class SalvoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SalvoApplication.class, args);
	}

	// Testing the API end points

	@Bean
	public CommandLineRunner initData(PlayerRepository playerRepository, GameRepository gameRepository,
									  GamePlayerRepository gamePlayerRepository) {

		return (args) -> {
			Player player1 = new Player("example1@email.com");
			Player player2 = new Player("example2@email.com");
			Player player3 = new Player("example3@email.com");

			Game game1 = new Game(new Date());
			Game game2 = new Game(Date.from(new Date().toInstant().plusSeconds(3600)));
			Game game3 = new Game(Date.from(new Date().toInstant().plusSeconds(7200)));

			Date creationDate = new Date();

			GamePlayer gamePlayer1 = new GamePlayer(game1, player1, creationDate);
			GamePlayer gamePlayer2 = new GamePlayer(game2, player2, creationDate);
			GamePlayer gamePlayer3 = new GamePlayer(game3, player3, creationDate);

			playerRepository.save(player1);
			playerRepository.save(player2);
			playerRepository.save(player3);

			gameRepository.save(game1);
			gameRepository.save(game2);
			gameRepository.save(game3);

			gamePlayerRepository.save(gamePlayer1);
			gamePlayerRepository.save(gamePlayer2);
			gamePlayerRepository.save(gamePlayer3);
		};
	}
}
