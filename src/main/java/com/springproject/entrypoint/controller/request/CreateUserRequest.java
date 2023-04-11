package com.springproject.entrypoint.controller.request;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateUserRequest {
	private String username;

	private String password;

	public String getPassword() {
		if (this.password == null) {
			return null;
		} else {
			return new BCryptPasswordEncoder().encode(this.password);
		}
	}
}
