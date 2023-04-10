package com.springproject.entrypoint.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springproject.core.domain.PersonDomain;
import com.springproject.core.usecase.PersonUseCase;
import com.springproject.entrypoint.controller.producer.SendPersonProducer;
import com.springproject.entrypoint.controller.producer.send.PersonMessageSend;
import com.springproject.entrypoint.controller.request.PersonRequest;
import com.springproject.entrypoint.controller.response.BaseResponse;
import com.springproject.entrypoint.controller.response.PersonResponse;
import com.springproject.entrypoint.controller.response.service.BaseResponseService;
import com.springproject.entrypoint.controller.response.service.PersonResponseService;
import com.springproject.mapper.PersonMapper;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(path = "/v1/person")
@RequiredArgsConstructor
public class PersonController {

	private final PersonUseCase personUseCase;
	private final PersonResponseService personResponseService;
	private final SendPersonProducer sendPersonProducer;
	private final PersonMapper mapper;
	private final BaseResponseService baseResponseService;

	@PostMapping
	public ResponseEntity<BaseResponse> save(@RequestBody PersonRequest personRequest) {
		PersonDomain personDomain = mapper.map(personRequest, PersonDomain.class);
		String message = personUseCase.save(personDomain);
		BaseResponse response = baseResponseService.addBaseResponse(message);
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}

	@GetMapping
	public ResponseEntity<PersonResponse> findAll() {
		List<PersonDomain> personDomains = personUseCase.findAll();
		PersonResponse response = personResponseService.findAll(personDomains);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<PersonResponse> findById(@PathVariable("id") Long id) {
		PersonDomain personDomain = personUseCase.findById(id);
		PersonResponse response = personResponseService.findById(personDomain);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<BaseResponse> delete(@PathVariable("id") Long id) {
		BaseResponse response = baseResponseService.addBaseResponse(personUseCase.delete(id));
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@PostMapping(value = "/kafka-producer")
	public ResponseEntity<BaseResponse> saveKafkaProducer(@RequestBody PersonRequest personRequest) {
		PersonMessageSend personMessageSend = mapper.map(personRequest, PersonMessageSend.class);
		sendPersonProducer.send(personMessageSend);
		BaseResponse response = baseResponseService.addBaseResponse("Added to queue");
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}
}
