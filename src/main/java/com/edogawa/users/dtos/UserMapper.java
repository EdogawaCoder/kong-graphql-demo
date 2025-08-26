package com.edogawa.users.dtos;

import org.springframework.stereotype.Component;

import com.edogawa.users.entities.User;

@Component
public class UserMapper {

	private UserMapper() {
	}

	public User toEntity(UserCreateDto dto) {
		return new User(dto.email(), dto.password());
	}

	public UserResponseDto toResponse(User user) {
		return new UserResponseDto(user.getId(), user.getEmail());
	}

}
