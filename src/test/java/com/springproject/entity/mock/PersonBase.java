package com.springproject.entity.mock;

import java.time.LocalDateTime;

import com.springproject.core.domain.PersonDomain;
import com.springproject.core.type.ActiveEnum;
import com.springproject.dataprovider.repository.entity.PersonEntity;
import com.springproject.entrypoint.controller.request.PersonRequest;
import com.springproject.entrypoint.controller.response.PersonResponse;

public class PersonBase {

	public PersonRequest personRequest = new PersonRequest();

	public PersonResponse personResponse = new PersonResponse();

	public PersonEntity personEntity = new PersonEntity();

	public PersonEntity personEntityPostSave = new PersonEntity();

	public PersonDomain personDomain = new PersonDomain(1L, "Person Name", ActiveEnum.YES, "email@test.com");

	public PersonBase() {
		personRequest.setName("Person Name");
		personRequest.setEmail("email@test.com");
		personRequest.setIsActive(ActiveEnum.YES);

		personResponse.setName("Person Name");
		personResponse.setEmail("email@test.com");
		personResponse.setIsActive(ActiveEnum.YES);

		personEntity.setId(1L);
		personEntity.setEmail("email@test.com");
		personEntity.setName("Person Name");
		personEntity.setIsActive(ActiveEnum.YES);
		personEntity.setCreatedBy(0L);
		personEntity.setLastModifiedBy(0L);
		personEntity.setCreatedDate(LocalDateTime.now());
		personEntity.setLastModifiedDate(LocalDateTime.now());

		personEntityPostSave = new PersonEntity();
		personEntityPostSave = personEntity;
		personEntityPostSave.setId(1L);

	}

}
