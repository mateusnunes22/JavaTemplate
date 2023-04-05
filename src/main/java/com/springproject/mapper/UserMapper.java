package com.springproject.mapper;

import com.springproject.core.domain.PersonDomain;
import com.springproject.core.domain.UserDomain;
import com.springproject.entrypoint.controller.response.PersonResponse;
import com.springproject.entrypoint.controller.response.UserResponse;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserMapper extends ModelMapper{

	public List<UserDomain> toDomainList(List<?> fromList) {
		return fromList.stream().map(element -> map(element, UserDomain.class)).toList();
	}
	public List<UserResponse> toResponseList(List<?> fromList) {
		return fromList.stream().map(element -> map(element, UserResponse.class)).toList();
	}
}
