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
	public CommandLineRunner initData(PlayerRepository repository) {
		return (args) -> {
			// save some sample data
			repository.save(new Player("Rupert", "Kater", "rupert@email.com"));
			repository.save(new Player("Robin", "Mokamester", "robin@email.com"));
			repository.save(new Player("Zoltan", "Szokodi", "zoltan@email.com"));
			repository.save(new Player("Erzsi", "Schrammel", "erzsi@email.com"));
			repository.save(new Player("Fülöp", "Snake", "fulop@email.com"));
		};
	}
}
