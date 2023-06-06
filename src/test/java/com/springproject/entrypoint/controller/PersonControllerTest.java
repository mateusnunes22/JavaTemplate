package com.springproject.entrypoint.controller;

import com.springproject.core.domain.PersonDomain;
import com.springproject.core.usecase.PersonUseCase;
import com.springproject.entity.mock.PersonBase;
import com.springproject.entrypoint.controller.producer.SendPersonProducer;
import com.springproject.entrypoint.controller.producer.send.PersonMessageSend;
import com.springproject.entrypoint.controller.response.BaseResponse;
import com.springproject.entrypoint.controller.response.service.BaseResponseService;
import com.springproject.entrypoint.controller.response.service.PersonResponseService;
import com.springproject.mapper.PersonMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PersonControllerTest extends PersonBase {

	@InjectMocks
	public PersonController personController;

	@Mock
	public PersonUseCase personUseCase;

	@Mock
	public PersonMapper mapper;
	
	@Mock
	public BaseResponseService baseResponseService;
	
	@Mock
	public PersonResponseService personResponseService;

	@Mock
	public SendPersonProducer sendPersonProducer;

	@Test
	@DisplayName("Save default CRUD: Status OK")
	 void savePersonSuccessTest() {
		BaseResponse response = new BaseResponse();
		when(mapper.map(personRequest, PersonDomain.class)).thenReturn(personDomain);
		when(baseResponseService.addBaseResponse(any())).thenReturn(response);
		
		ResponseEntity<Object> result = personController.save(personRequest);
		
		assertEquals(HttpStatus.CREATED, result.getStatusCode());
		assertEquals(response, result.getBody());
	}

	@Test
	@DisplayName("Save default CRUD: Exception")
	void savePersonThrowTest() {
		when(mapper.map(personRequest, PersonDomain.class)).thenThrow(new ResponseStatusException(HttpStatus.BAD_REQUEST, ""));
		assertThrows(ResponseStatusException.class, () -> personController.save(personRequest));
	}

	@Test
	@DisplayName("Find all default CRUD: Status OK")
	void findAllSuccessTest() {
		when(personUseCase.findAll()).thenReturn(List.of(personDomain));
		when(personResponseService.getAll(List.of(personDomain))).thenReturn(personResponse);
		ResponseEntity<Object> result = personController.findAll();
		assertEquals(HttpStatus.OK, result.getStatusCode());
	}

	@Test
	@DisplayName("Find all Exception test")
	void findAllThrowTest() {
		when(personUseCase.findAll()).thenThrow(new ResponseStatusException(HttpStatus.BAD_REQUEST, ""));
		assertThrows(ResponseStatusException.class, () -> personController.findAll());
	}

	@Test
	@DisplayName("Find by Id default CRUD: Status OK")
	void findByIdSuccessTest() {
		ResponseEntity<Object> result = personController.findById(0L);
		assertEquals(result, new ResponseEntity<>(null, HttpStatus.OK));
	}

	@Test
	@DisplayName("Find by Id default CRUD: Exception")
	void findByIdThrowTest() {
		when(personUseCase.findById(any())).thenThrow(new ResponseStatusException(HttpStatus.BAD_REQUEST, ""));
		assertThrows(ResponseStatusException.class, () -> personController.findById(0L));
	}

	@Test
	@DisplayName("Find by E-mail default CRUD: Status OK")
	void findByEmailSuccessTest() {
		ResponseEntity<Object> result = personController.findByEmail(personRequest.getEmail());
		assertEquals(result, new ResponseEntity<>(null, HttpStatus.OK));
	}

	@Test
	@DisplayName("Find by E-mail default CRUD: Exception")
	void findByEmailThrowTest() {
		when(personUseCase.findByEmail(anyString())).thenThrow(new ResponseStatusException(HttpStatus.BAD_REQUEST, ""));
		assertThrows(ResponseStatusException.class, () -> personController.findByEmail(personRequest.getEmail()));
	}

	@Test
	@DisplayName("Delete default CRUD: Status OK")
	void deleteSuccessTest() {
		ResponseEntity<Object> result = personController.delete(personEntity.getId());
		assertEquals(result, new ResponseEntity<>(null, HttpStatus.OK));
	}

	@Test
	@DisplayName("Delete default CRUD: Exception")
	void deleteThrowTest() {
		when(baseResponseService.addBaseResponse(any())).thenThrow(new ResponseStatusException(HttpStatus.BAD_REQUEST, ""));
		assertThrows(ResponseStatusException.class, () -> personController.delete(0L));
	}

	@Test
	@DisplayName("Save by kafka: Exception")
	void saveKafkaProducerSuccessTest() {
		when(mapper.map(personRequest, PersonMessageSend.class)).thenReturn(personMessageSend);
		when(baseResponseService.addBaseResponse("Added to queue")).thenReturn(new BaseResponse());
		assertNotNull(personController.saveKafkaProducer(personRequest));
	}

	@Test
	@DisplayName("Save by kafka: Exception")
	void saveKafkaProducerThrowTest() {
		when(mapper.map(personRequest, PersonMessageSend.class)).thenThrow(new ResponseStatusException(HttpStatus.BAD_REQUEST, ""));
		assertThrows(ResponseStatusException.class, () -> personController.saveKafkaProducer(personRequest));
	}

}
