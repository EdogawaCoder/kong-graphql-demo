package com.edogawa.users.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Document(collection = "users")
@Data
@AllArgsConstructor
@RequiredArgsConstructor
@NoArgsConstructor
public class User {

	@Id
	private Long id;

	@Indexed(unique = true)
	@NonNull
	private String email;

	@NonNull
	private String password;

}
