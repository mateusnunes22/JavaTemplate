package com.springproject.entrypoint.controller.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.springproject.entrypoint.controller.dto.UserDto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties
public class UserResponse extends BaseResponse{
	private UserDto userDto;
}
