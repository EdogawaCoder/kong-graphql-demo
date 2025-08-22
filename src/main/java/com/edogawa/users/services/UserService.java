package com.edogawa.users.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.edogawa.users.entities.User;
import com.edogawa.users.exceptions.EmailAlreadyExistsException;
import com.edogawa.users.exceptions.UserNotFoundException;
import com.edogawa.users.repositories.UserRepository;


@Service
public class UserService {

	private static final String usersSequence = "user_seq";
	
	private final UserRepository repo;
	private final UserSequenceGeneratorService seq;
	
	public UserService(UserRepository repo, UserSequenceGeneratorService seq) {
		this.repo = repo;
		this.seq = seq;
	}
	
	// list all users
	public List<User> findAll() {
		return repo.findAll();
	}
	
	public User create(User user) {
		if (repo.existsByEmail(user.getEmail())) {
			throw new EmailAlreadyExistsException(user.getEmail());
		}
		
		user.setId(seq.nextValue(usersSequence));
		
		return repo.save(user);
	}
	
	public User findById(Long id) {
		return repo.findById(id)
				.orElseThrow(() -> new UserNotFoundException(id));
		}
	
	
	
}
