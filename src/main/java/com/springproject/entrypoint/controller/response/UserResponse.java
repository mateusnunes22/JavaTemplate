package com.springproject.entrypoint.controller.response;

import com.springproject.core.type.ActiveEnum;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserResponse {
	private String username;

	private ActiveEnum isActive;
}
