package com.springproject.entrypoint.controller;

import com.springproject.core.domain.UserDomain;
import com.springproject.core.usecase.UserUseCase;
import com.springproject.entrypoint.controller.request.AuthenticationRequest;
import com.springproject.entrypoint.controller.request.CreateUserRequest;
import com.springproject.entrypoint.controller.response.UserResponse;
import com.springproject.entrypoint.controller.response.service.UserResponseService;
import com.springproject.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping(path = "/v1/auth")
@RequiredArgsConstructor
@Slf4j
public class AuthController {

	private final AuthenticationManager authenticationManager;
	private final UserUseCase userUseCase;
	private final UserResponseService userResponseService;
	private final UserMapper mapper;

	@PostMapping(value = "/authenticate")
	public ResponseEntity<Object> authenticate(@RequestBody AuthenticationRequest request) {
		try {
			log.info("Controller: Starting the process to authenticate the user.");
			authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
			final String token = userUseCase.authenticate(request.getUsername());
			return new ResponseEntity<>(token, HttpStatus.OK);
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

	@PostMapping(value = "/create-user")
	public ResponseEntity<Object> createUser(@RequestBody CreateUserRequest request) {
		try {
			log.info("Controller: Starting the process to create a user.");
			UserDomain userDomain = userUseCase.createUser(mapper.map(request, UserDomain.class));
			UserResponse userResponse = userResponseService.createUser(userDomain);
			return new ResponseEntity<>(userResponse, HttpStatus.OK);
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

	@PostMapping(value = "invalidate-session")
	public ResponseEntity<Object> invalidateSession() {
		try {
			log.info("Controller: Starting the process to invalidate the session.");
			userUseCase.invalidateSession();
			return new ResponseEntity<>(null, HttpStatus.OK);
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}
}
