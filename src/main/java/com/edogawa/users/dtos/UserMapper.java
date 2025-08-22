package com.edogawa.users.dtos;

import com.edogawa.users.entities.User;

public class UserMapper {

	private UserMapper() {
	}

	public static User toEntity(UserCreateDto dto) {
		return new User(dto.email(), dto.password());
	}

	public static UserResponseDto toResponse(User user) {
		return new UserResponseDto(user.getId(), user.getEmail());
	}

}
