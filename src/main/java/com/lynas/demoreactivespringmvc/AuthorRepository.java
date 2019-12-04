package com.lynas.demoreactivespringmvc;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

public interface AuthorRepository extends ReactiveCrudRepository<Author, String> {
    Mono<Author> findByName(String name);
}
