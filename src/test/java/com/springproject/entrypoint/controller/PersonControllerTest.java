package com.springproject.entrypoint.controller;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.springproject.core.domain.PersonDomain;
import com.springproject.core.usecase.PersonUseCase;
import com.springproject.entity.mock.PersonBase;
import com.springproject.entrypoint.controller.response.person.PersonResponse;
import com.springproject.mapper.PersonMapper;

@ExtendWith(MockitoExtension.class)
class PersonControllerTest extends PersonBase {

	@InjectMocks
	public PersonController personController;

	@Mock
	public PersonUseCase personUseCase;

	@Mock
	public PersonMapper mapper;

	@Test
	@DisplayName("Save default CRUD: Status OK")
	void saveOkTest() {
		when(mapper.map(personRequest, PersonDomain.class)).thenReturn(personDomain);
		ResponseEntity<String> result = personController.save(personRequest);
		assertEquals(result, new ResponseEntity<>(HttpStatus.CREATED));
	}

	@Test
	@DisplayName("Find all default CRUD: Status OK")
	void findAllCreatedTest() {
		when(personUseCase.findAll()).thenReturn(List.of(personDomain));
		ResponseEntity<List<PersonResponse>> reuslt = personController.findAll();
		assertEquals(reuslt, new ResponseEntity<>(Collections.emptyList(), HttpStatus.OK));
	}

	@Test
	@DisplayName("Find by Id default CRUD: Status OK")
	void findByIdOkTest() {
		ResponseEntity<PersonResponse> reuslt = personController.findById(0L);
		assertEquals(reuslt, new ResponseEntity<>(null, HttpStatus.OK));
	}

	@Test
	@DisplayName("Delete default CRUD: Status OK")
	void deleteOkTest() {
		ResponseEntity<String> reuslt = personController.delete(personEntity.getId());
		assertEquals(reuslt, new ResponseEntity<>(null, HttpStatus.OK));
	}

}
