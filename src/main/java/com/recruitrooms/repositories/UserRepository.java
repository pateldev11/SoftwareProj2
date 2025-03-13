package com.recruitrooms.repositories;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import com.recruitrooms.models.User;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface UserRepository extends ReactiveMongoRepository<User, String> {
    Mono<User> findByUsername(String username); 
    Mono<User> findByPhoneNumber(String phoneNumber);
	Mono<Boolean> existsByUsername(String username);
    Mono<Boolean> existsByPhoneNumber(String phoneNumber);
	Flux<User> findByRole(String role);	
}
