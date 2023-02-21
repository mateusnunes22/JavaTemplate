package com.springproject.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.springproject.dataprovider.repository.PersonRepository;
import com.springproject.dataprovider.repository.entity.PersonEntity;
import com.springproject.dto.PersonDTO;
import com.springproject.entity.mock.PersonBase;
import com.springproject.exception.InvalidGenericException;

@ExtendWith(MockitoExtension.class)
class PersonServiceTest extends PersonBase {

	@InjectMocks
	public PersonServiceImpl personService;

	@Mock
	public PersonRepository personRepository;

	@Test
	@DisplayName("Find all CRUD: List empty")
	void findAllTest() {
		when(personRepository.findAll()).thenReturn(new ArrayList<>());
		List<PersonDTO> result = personService.findAll();
		assertEquals(result, new ArrayList<>());
	}

	@Test
	@DisplayName("Find all CRUD: Exception")
	void findAllCatchTest() {
		when(personRepository.findAll()).thenThrow(new InvalidGenericException(""));
		assertThrows(InvalidGenericException.class, () -> personService.findAll());
	}

	@Test
	@DisplayName("Save CRUD: Object empty")
	void saveTest() {
		when(personRepository.save(any())).thenReturn(personEntityPostSave);
		PersonDTO result = personService.save(personDTO);
		assertNotNull(result);
	}

	@Test
	@DisplayName("Save CRUD: Exception")
	void saveCatchTest() {
		assertThrows(InvalidGenericException.class, () -> personService.save(personDTO));
	}

	@Test
	@DisplayName("Edit Entity")
	void editEntityTest() {
		PersonEntity personEdited = personEdited();
		when(personRepository.save(any())).thenReturn(personEdited);
		PersonDTO result = personService.save(personDTO);
		assertNotNull(result);
	}

	@Test
	@DisplayName("Delete CRUD: Void method")
	void deleteTest() {
		verify(personRepository, times(0)).delete(personEntityPostSave);
		personService.delete(1L);
	}

	@Test
	@DisplayName("Delete CRUD: Exception")
	void deleteCatchTest() {
		doThrow(new InvalidGenericException("")).when(personRepository).deleteById(any());
		assertThrows(InvalidGenericException.class, () -> personService.delete(1L));
	}

	public PersonEntity personEdited() {
		PersonEntity personEntityEdited = new PersonEntity();
		personEntityEdited.setId(personEntityPostSave.getId());
		personEntityEdited.setEmail(personEntityPostSave.getEmail());
		personEntityEdited.setName(personEntityPostSave.getName());
		personEntityEdited.setCreatedBy(personEntityPostSave.getCreatedBy());
		personEntityEdited.setLastModifiedBy(personEntityPostSave.getLastModifiedBy());
		personEntityEdited.setCreatedDate(personEntityPostSave.getCreatedDate());
		personEntityEdited.setLastModifiedDate(personEntityPostSave.getLastModifiedDate());
		return personEntityEdited;
	}

}
