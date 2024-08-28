package com.SpringBootMongoDB.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.SpringBootMongoDB.domain.User;

public interface UserRepository extends MongoRepository<User, String> {

}
