package com.springproject.controller;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.springproject.dto.PersonDTO;
import com.springproject.entity.mock.PersonBase;
import com.springproject.service.PersonService;

@ExtendWith(MockitoExtension.class)
class PersonControllerTest extends PersonBase {

	@InjectMocks
	public PersonController personController;

	@Mock
	public PersonService personService;

	@Test
	@DisplayName("Save default CRUD: Status OK")
	void saveOkTest() {
		ResponseEntity<PersonDTO> reuslt = personController.save(personDTO);
		assertEquals(reuslt, new ResponseEntity<>(HttpStatus.OK));
	}

	@Test
	@DisplayName("Find all default CRUD: Status OK")
	void findAllOkTest() {
		ResponseEntity<List<PersonDTO>> reuslt = personController.findAll();
		assertEquals(reuslt, new ResponseEntity<>(new ArrayList<>(), HttpStatus.OK));
	}

	@Test
	@DisplayName("Delete default CRUD: Status OK")
	void deleteOkTest() {
		ResponseEntity<String> reuslt = personController.delete(personEntity.getId());
		assertEquals(reuslt, new ResponseEntity<>(null, HttpStatus.OK));
	}

}
