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

	/* @Bean
	public CommandLineRunner initData(PlayerRepository repository) {
		return (args) -> {
			// save some sample data
			repository.save(new Player("rupu@email.com"));
			repository.save(new Player("robin@email.com"));
			repository.save(new Player("phil@email.com"));
		};
	} */

	@Bean
	public CommandLineRunner initData(GameRepository repository) {
		return (args) -> {
			// save some sample data
			repository.save(new Game(new Date()));
			repository.save(new Game(Date.from(new Date().toInstant().plusSeconds(3600))));
			repository.save(new Game(Date.from(new Date().toInstant().plusSeconds(7200))));
		};
	}
}
