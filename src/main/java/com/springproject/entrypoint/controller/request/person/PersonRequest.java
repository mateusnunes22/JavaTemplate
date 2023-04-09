package com.springproject.entrypoint.controller.request.person;

import com.springproject.core.type.YesNoEnum;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PersonRequest {
	private String name;

	private String email;

	private YesNoEnum isActive;
}
