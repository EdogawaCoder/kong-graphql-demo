package com.edogawa.users.repositories;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.edogawa.users.entities.User;

@Repository
public interface UserRepository extends MongoRepository<User, Long> {

	Optional<User> findByEmail(String email);

	boolean existsByEmail(String email);

	void deleteByEmail(String email);

}
