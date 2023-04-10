package com.springproject.entrypoint.controller.response.service;

import com.springproject.core.domain.UserDomain;
import com.springproject.entrypoint.controller.response.UserResponse;

public interface UserResponseService {
	UserResponse createUser(UserDomain userDomain);
}
