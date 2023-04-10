package com.springproject.entity.mock;

import java.util.ArrayList;
import java.util.Date;

import com.springproject.core.domain.PersonDomain;
import com.springproject.core.type.YesNoEnum;
import com.springproject.dataprovider.repository.entity.PersonEntity;
import com.springproject.entrypoint.controller.request.PersonRequest;
import com.springproject.entrypoint.controller.response.PersonResponse;

public class PersonBase {

	public PersonRequest personRequest = new PersonRequest();

	public PersonResponse personResponse = new PersonResponse();

	public PersonEntity personEntity = new PersonEntity();

	public PersonEntity personEntityPostSave = new PersonEntity();

	public PersonDomain personDomain = new PersonDomain(1L, "Person Name", YesNoEnum.YES, "email@test.com");

	public PersonBase() {
		personRequest.setName("Person Name");
		personRequest.setEmail("email@test.com");
		personRequest.setIsActive(YesNoEnum.YES);

		personResponse.setPersonDtoList(new ArrayList<>());

		personEntity.setId(1L);
		personEntity.setEmail("email@test.com");
		personEntity.setName("Person Name");
		personEntity.setIsActive(YesNoEnum.YES);
		personEntity.setCreatedBy(1L);
		personEntity.setLastModifiedBy(1L);
		personEntity.setCreatedDate(new Date());
		personEntity.setLastModifiedDate(new Date());

		personEntityPostSave = new PersonEntity();
		personEntityPostSave = personEntity;
		personEntityPostSave.setId(1L);

	}

}
