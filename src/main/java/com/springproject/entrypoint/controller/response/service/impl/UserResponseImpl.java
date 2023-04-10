package com.springproject.entrypoint.controller.response.service.impl;

import org.springframework.stereotype.Component;

import com.springproject.core.domain.UserDomain;
import com.springproject.entrypoint.controller.dto.UserDto;
import com.springproject.entrypoint.controller.response.UserResponse;
import com.springproject.entrypoint.controller.response.service.UserResponseService;
import com.springproject.mapper.UserMapper;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class UserResponseImpl implements UserResponseService {

	private final UserMapper mapper;

	@Override
	public UserResponse createUser(UserDomain userDomain) {
		UserResponse userResponse = new UserResponse();
		userResponse.setUserDto(mapper.map(userDomain, UserDto.class));
		return userResponse;
	}

}
