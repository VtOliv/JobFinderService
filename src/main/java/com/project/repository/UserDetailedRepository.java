package com.project.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.project.domains.UserDetails;

public interface UserDetailedRepository extends MongoRepository<UserDetails, Long> {
}
