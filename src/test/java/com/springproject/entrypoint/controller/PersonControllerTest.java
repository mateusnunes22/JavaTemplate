package com.springproject.entrypoint.controller;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

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
import com.springproject.entrypoint.controller.response.BaseResponse;
import com.springproject.entrypoint.controller.response.PersonResponse;
import com.springproject.entrypoint.controller.response.service.BaseResponseService;
import com.springproject.entrypoint.controller.response.service.PersonResponseService;
import com.springproject.mapper.PersonMapper;

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
	
	@Test
	@DisplayName("Save default CRUD: Status OK")
	 void savePersonSuccessTest() {
		String message = "Person saved successfully";
		BaseResponse response = new BaseResponse();
		response.setMessage(message);
		when(mapper.map(personRequest, PersonDomain.class)).thenReturn(personDomain);
		when(personUseCase.save(personDomain)).thenReturn(message);
		when(baseResponseService.addBaseResponse(message)).thenReturn(response);
		
		ResponseEntity<BaseResponse> result = personController.save(personRequest);
		
		assertEquals(HttpStatus.CREATED, result.getStatusCode());
		assertEquals(response, result.getBody());
	}

	@Test
	@DisplayName("Find all default CRUD: Status OK")
	void findAllCreatedTest() {
		when(personUseCase.findAll()).thenReturn(List.of(personDomain));
		when(personResponseService.findAll(List.of(personDomain))).thenReturn(personResponse);
		ResponseEntity<PersonResponse> result = personController.findAll();
		assertEquals(HttpStatus.OK, result.getStatusCode());
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
		ResponseEntity<BaseResponse> reuslt = personController.delete(personEntity.getId());
		assertEquals(reuslt, new ResponseEntity<>(null, HttpStatus.OK));
	}

}
