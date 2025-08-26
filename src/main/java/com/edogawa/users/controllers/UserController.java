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

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(value = "/api/v1/users", produces = "application/json")
@RequiredArgsConstructor
public class UserController {

	private final UserService service;
	private final UserMapper mapper;
	
	
	@PostMapping(consumes = "application/json")
	public ResponseEntity<UserResponseDto> createUser(@RequestBody UserCreateDto body) {

		User saved = service.create(mapper.toEntity(body));

		var dto = mapper.toResponse(saved);

		return ResponseEntity.created(URI.create("/api/v1/users/" + dto.id())).body(dto);
	}

	@GetMapping
	public ResponseEntity<List<UserResponseDto>> findAllUsers() {
		return ResponseEntity.ok(service.findAll()
				.stream()
				.map(mapper::toResponse)
				.toList());
	}

	@GetMapping("/{id}")
	public UserResponseDto findUserById(Long id) {
		User user = service.findById(id);
		if (user == null) {
			throw new UserNotFoundException(id);
		}
		return mapper.toResponse(user);
	}
}
