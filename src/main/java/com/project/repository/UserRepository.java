package com.project.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.project.domains.User;

public interface UserRepository extends MongoRepository<User, Long> {
	User findByEmail(String email);
}
