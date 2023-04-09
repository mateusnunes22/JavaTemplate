package com.springproject.entrypoint.controller.response.person;

import com.springproject.core.type.YesNoEnum;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PersonResponse {
	private String name;

	private String email;

	private YesNoEnum isActive;
}
