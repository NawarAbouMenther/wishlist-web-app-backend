package com.example.demo;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Bean
	@Profile("!test")   // ❗ verhindert Fehler in Tests
	CommandLineRunner initCategories(WishCategoryRepository repo) {
		return args -> {
			if (repo.count() == 0) {
				WishCategory c1 = new WishCategory();
				c1.setKey("birthday");
				c1.setLabel("Geburtstag");

				WishCategory c2 = new WishCategory();
				c2.setKey("christmas");
				c2.setLabel("Weihnachten");

				WishCategory c3 = new WishCategory();
				c3.setKey("wedding");
				c3.setLabel("Hochzeit");

				WishCategory c4 = new WishCategory();
				c4.setKey("baby");
				c4.setLabel("Baby-Party");

				WishCategory c5 = new WishCategory();
				c5.setKey("wishlist");
				c5.setLabel("Wunschliste");

				WishCategory c6 = new WishCategory();
				c6.setKey("custom");
				c6.setLabel("Eigene Kategorie");

				repo.save(c1);
				repo.save(c2);
				repo.save(c3);
				repo.save(c4);
				repo.save(c5);
				repo.save(c6);
			}
		};
	}
}