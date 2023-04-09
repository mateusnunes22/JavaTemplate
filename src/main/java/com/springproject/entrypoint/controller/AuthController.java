package com.springproject.entrypoint.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springproject.core.domain.UserDomain;
import com.springproject.core.usecase.UserUseCase;
import com.springproject.entrypoint.controller.request.auth.AuthenticationRequest;
import com.springproject.entrypoint.controller.request.auth.CreateUserRequest;
import com.springproject.entrypoint.controller.response.auth.UserResponse;
import com.springproject.mapper.UserMapper;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(path = "/v1/auth")
@RequiredArgsConstructor
public class AuthController {

	private final AuthenticationManager authenticationManager;
	private final UserUseCase userUseCase;
	private final UserMapper mapper;

	@PostMapping(value = "/authenticate")
	public ResponseEntity<String> authenticate(@RequestBody AuthenticationRequest request) {
		authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
		final String token = userUseCase.authenticate(request.getUsername());
		if (token != null) {
			return ResponseEntity.ok(token);
		}
		return ResponseEntity.status(400).body("Some error has occurred!");
	}

	@PostMapping(value = "/create-user")
	public ResponseEntity<UserResponse> createUser(@RequestBody CreateUserRequest request) {
		UserDomain userDomain = userUseCase.createUser(mapper.map(request, UserDomain.class));
		return ResponseEntity.ok(mapper.map(userDomain, UserResponse.class));
	}
}
