package com.springproject.entrypoint.controller.response.service.impl;

import java.util.List;

import org.springframework.stereotype.Component;

import com.springproject.core.domain.PersonDomain;
import com.springproject.entrypoint.controller.dto.PersonDto;
import com.springproject.entrypoint.controller.response.PersonResponse;
import com.springproject.entrypoint.controller.response.service.BaseResponseService;
import com.springproject.entrypoint.controller.response.service.PersonResponseService;
import com.springproject.mapper.PersonMapper;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class PersonResponseServiceImpl implements PersonResponseService {

	private final BaseResponseService baseResponseService;

	private final PersonMapper mapper;

	@Override
	public PersonResponse findAll(List<PersonDomain> personDomains) {
		PersonResponse personResponse = new PersonResponse();
		personResponse.setPersonDtoList(mapper.toDtoList(personDomains));
		baseResponseService.addBaseResponse(personResponse, null);
		return personResponse;
	}

	@Override
	public PersonResponse findById(PersonDomain personDomain) {
		PersonResponse personResponse = new PersonResponse();
		personResponse.setPersonDto(mapper.map(personDomain, PersonDto.class));
		baseResponseService.addBaseResponse(personResponse, null);
		return personResponse;
	}

}
