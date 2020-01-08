package com.codeoftheweb.salvo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

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
			GamePlayerRepository gamePlayerRepository) {

		return (args) -> {
			Player player1 = new Player("Peter", "peterGriffin@email.com");
			Player player2 = new Player("Glenn", "glennQuagmire@email.com");
			Player player3 = new Player("Joe", "joeSwanson@email.com");

			Game game1 = new Game();
			Game game2 = new Game();
			Game game3 = new Game();

			GamePlayer gamePlayer1 = new GamePlayer(game1, player1);
			GamePlayer gamePlayer2 = new GamePlayer(game2, player2);
			GamePlayer gamePlayer3 = new GamePlayer(game3, player3);
			GamePlayer gamePlayer4 = new GamePlayer(game3, player1);

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
		};
	}
}
