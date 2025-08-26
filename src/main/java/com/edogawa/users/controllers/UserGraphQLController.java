package com.edogawa.users.controllers;

import java.util.List;

import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import com.edogawa.users.dtos.UserCreateDto;
import com.edogawa.users.dtos.UserMapper;
import com.edogawa.users.dtos.UserResponseDto;
import com.edogawa.users.services.UserService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class UserGraphQLController {

	private final UserService service;
	private final UserMapper mapper;

	
	@MutationMapping
	public UserResponseDto createUser(@Valid @Argument("input") UserCreateDto body) {
		return mapper.toResponse(
				service.create(mapper.toEntity(body))
				);

	}
		
	@QueryMapping
	public List<UserResponseDto> findAll() {
		return service.findAll()
				.stream()
				.map(mapper::toResponse)
				.toList();

	}

	@QueryMapping
	public UserResponseDto byId(@Argument Long id) {
		return mapper.toResponse(service.findById(id));
	}

	
	
					
	
	               	

}
