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

	@Bean
	public CommandLineRunner initData (PlayerRepository repository) {
		return (args) -> {
			repository.save(new Player("Rupert", "Kater"));
			repository.save(new Player("Robin", "Mokamester"));
			repository.save(new Player("Zoltan", "Szokodi"));
			repository.save(new Player("Erzsi", "Schrammel"));
			repository.save(new Player("Fülöp", "Snake"));
		};
	}
}
