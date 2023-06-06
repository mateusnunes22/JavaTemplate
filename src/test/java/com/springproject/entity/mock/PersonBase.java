package com.springproject.entity.mock;

import java.util.ArrayList;
import java.util.Date;

import com.springproject.core.domain.PersonDomain;
import com.springproject.core.type.YesNoEnum;
import com.springproject.dataprovider.repository.entity.PersonEntity;
import com.springproject.entrypoint.controller.consumer.recive.PersonMessageConsume;
import com.springproject.entrypoint.controller.dto.PersonDto;
import com.springproject.entrypoint.controller.producer.send.PersonMessageSend;
import com.springproject.entrypoint.controller.request.PersonRequest;
import com.springproject.entrypoint.controller.response.PersonResponse;

public class PersonBase {

	public PersonDomain personDomain = new PersonDomain(1L, "Person Name", YesNoEnum.YES, "email@test.com");

	public PersonRequest personRequest = new PersonRequest();

	public PersonResponse personResponse = new PersonResponse();

	public PersonEntity personEntity = new PersonEntity();

	public PersonEntity personEntityPostSave = new PersonEntity();

	public PersonMessageConsume personMessageConsume = new PersonMessageConsume(1L,"levva","a.levva@.com",YesNoEnum.YES);

	public PersonMessageSend personMessageSend = new PersonMessageSend(1L, "a@levva.com", "levva", YesNoEnum.YES);


	public PersonDto personDto = new PersonDto();
	public PersonBase() {
		personRequest.setName(personDomain.getName());
		personRequest.setEmail("email@test.com");
		personRequest.setIsActive(personDomain.getIsActive());

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

		personMessageConsume.setId(1L);
		personMessageConsume.setEmail("a@levva.com");
		personMessageConsume.setIsActive(YesNoEnum.YES);
		personMessageConsume.setName("levva");
		
		personMessageSend.setId(1L);
		personMessageSend.setEmail("a@levva.com");
		personMessageSend.setIsActive(YesNoEnum.YES);
		personMessageSend.setName("levva");

		personDto.setEmail("a@levva.com");
		personDto.setName("levva");
		personDto.setIsActive(YesNoEnum.YES);
	}

}
