package com.springproject.entrypoint.controller.response.auth;

import com.springproject.core.type.YesNoEnum;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserResponse {
	private String username;

	private YesNoEnum isActive;
}
