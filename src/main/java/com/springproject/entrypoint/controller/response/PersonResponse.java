package com.springproject.entrypoint.controller.response;

import com.springproject.core.type.ActiveEnum;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PersonResponse {
	private String name;

	private String email;

	private ActiveEnum isActive;
}
