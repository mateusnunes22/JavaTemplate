package com.springproject.entrypoint.controller.request;

import com.springproject.core.type.ActiveEnum;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PersonRequest {
	private String name;

	private String email;

	private ActiveEnum isActive;
}
