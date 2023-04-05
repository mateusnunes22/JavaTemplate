package com.springproject.mapper;

import com.springproject.core.domain.PersonDomain;
import com.springproject.entrypoint.controller.response.PersonResponse;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PersonMapper extends ModelMapper{

	public List<PersonDomain> toDomainList(List<?> fromList) {
		return fromList.stream().map(element -> map(element, PersonDomain.class)).toList();
	}
	public List<PersonResponse> toResponseList(List<?> fromList) {
		return fromList.stream().map(element -> map(element, PersonResponse.class)).toList();
	}
}
