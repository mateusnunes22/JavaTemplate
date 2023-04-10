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
import com.springproject.entrypoint.controller.request.AuthenticationRequest;
import com.springproject.entrypoint.controller.request.CreateUserRequest;
import com.springproject.entrypoint.controller.response.UserResponse;
import com.springproject.entrypoint.controller.response.service.UserResponseService;
import com.springproject.mapper.UserMapper;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(path = "/v1/auth")
@RequiredArgsConstructor
public class AuthController {

	private final AuthenticationManager authenticationManager;
	private final UserUseCase userUseCase;
	private final UserResponseService userResponseService;
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
		UserResponse userResponse = userResponseService.createUser(userDomain);
		return ResponseEntity.ok(userResponse);
	}

	@PostMapping(value = "invalidate-session")
	public ResponseEntity<String> invalidateSession() {
		return ResponseEntity.ok(userUseCase.invalidateSession());
	}
}
