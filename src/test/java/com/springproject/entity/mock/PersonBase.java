package com.springproject.entity.mock;

import java.time.LocalDateTime;

import com.springproject.dataprovider.repository.entity.PersonEntity;
import com.springproject.dto.PersonDTO;

public class PersonBase {
	
	public PersonDTO personDTO = new PersonDTO();
	
	public PersonEntity personEntity = new PersonEntity();
	
	public PersonEntity personEntityPostSave = new PersonEntity();
	
	public PersonBase() {
		personDTO.setName("Person Name");
		personDTO.setEmail("email@test.com");
		
		personEntity.setEmail("email@test.com");
		personEntity.setName("Person Name");
		personEntity.setCreatedBy(0L);
		personEntity.setLastModifiedBy(0L);
		personEntity.setCreatedDate(LocalDateTime.now());
		personEntity.setLastModifiedDate(LocalDateTime.now());
		
		personEntityPostSave = new PersonEntity();
		personEntityPostSave = personEntity;
		personEntityPostSave.setId(1L);
		
	}
	
}
