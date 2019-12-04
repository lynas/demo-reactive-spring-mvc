package com.lynas.demoreactivespringmvc;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class DemoReactiveSpringMvcApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoReactiveSpringMvcApplication.class, args);
	}

	@Bean
	public CommandLineRunner init(AuthorRepository authorRepository, BookRepository bookRepository){
		return args -> {
			authorRepository.deleteAll()
					.thenMany(bookRepository.deleteAll())
					.thenMany(bookRepository.save(new Book(null, "book1", "1")))
					.thenMany(bookRepository.save(new Book(null, "book2", "1")))
					.thenMany(bookRepository.save(new Book(null, "book3", "2")))
					.thenMany(authorRepository.save(new Author("1", "author1")))
					.thenMany(authorRepository.save(new Author("2", "author2")))
					.subscribe(System.out::println);
		};
	}

}
