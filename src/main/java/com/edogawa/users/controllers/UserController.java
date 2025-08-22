package com.edogawa.users.controllers;

import java.net.URI;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.edogawa.users.dtos.UserCreateDto;
import com.edogawa.users.dtos.UserMapper;
import com.edogawa.users.dtos.UserResponseDto;
import com.edogawa.users.entities.User;
import com.edogawa.users.exceptions.UserNotFoundException;
import com.edogawa.users.services.UserService;

@RestController
@RequestMapping(value = "/api/v1/users", produces = "application/json")
public class UserController {

	private final UserService service;
	
	public UserController(UserService service) {
		this.service = service;
	}
	
	@PostMapping(consumes = "application/json")
	public ResponseEntity<UserResponseDto> createUser(@RequestBody UserCreateDto body){
		
		User saved = service.create(UserMapper.toEntity(body));
		
		var dto =UserMapper.toResponse(saved);
		
		return ResponseEntity.
				created(URI.create("/api/v1/users/" + dto.id()))
				.body(dto);
		}
	
	@GetMapping
	public List<UserResponseDto> findAllUsers() {
		return service.findAll()
				.stream()
				.map(UserMapper::toResponse)
				.toList();
	}
	
	
	@GetMapping("/{id}")
	public UserResponseDto findUserById(Long id) {
		User user = service.findById(id);
		if (user == null) {
			throw new UserNotFoundException(id);
		}
		return UserMapper.toResponse(user);
	}
}
