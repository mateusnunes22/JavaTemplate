package com.springproject.entrypoint.controller;

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
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping(path = "/v1/person")
@RequiredArgsConstructor
@Slf4j
public class PersonController {

	private final PersonUseCase personUseCase;
	private final PersonResponseService personResponseService;
	private final SendPersonProducer sendPersonProducer;
	private final PersonMapper mapper;
	private final BaseResponseService baseResponseService;

	@PostMapping
	public ResponseEntity<Object> save(@RequestBody PersonRequest personRequest) {
		try {
			log.info("Controller: Starting the process to save a new person");
			personUseCase.save(mapper.map(personRequest, PersonDomain.class));
			BaseResponse response = baseResponseService.addBaseResponse(null);
			return new ResponseEntity<>(response, HttpStatus.CREATED);
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

	@GetMapping
	public ResponseEntity<Object> findAll() {
		try {
			log.info("Controller: Starting the process get all persons");
			List<PersonDomain> personDomains = personUseCase.findAll();
			PersonResponse response = personResponseService.getAll(personDomains);
			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
		}
	}

	@GetMapping(value = "/id/{id}")
	public ResponseEntity<Object> findById(@PathVariable("id") Long id) {
		try {
			log.info("Controller: Starting the process to find a person by id");
			PersonDomain personDomain = personUseCase.findById(id);
			PersonResponse response = personResponseService.getOne(personDomain);
			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
		}
	}
	@GetMapping(value = "/email/{email}")
	public ResponseEntity<Object> findByEmail(@PathVariable("email") String email) {
		try {
			log.info("Controller: Starting the process to find a person by e-mail");
			PersonDomain personDomain = personUseCase.findByEmail(email);
			PersonResponse response = personResponseService.getOne(personDomain);
			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Object> delete(@PathVariable("id") Long id) {
		try {
			log.info("Controller: Starting the process to delete a person by id");
			personUseCase.delete(id);
			BaseResponse response = baseResponseService.addBaseResponse(null);
			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

	@PostMapping(value = "/kafka-producer")
	public ResponseEntity<Object> saveKafkaProducer(@RequestBody PersonRequest personRequest) {
		try {
			log.info("Controller: Starting the process to send an object to kafka queue to create a new user");
			PersonMessageSend personMessageSend = mapper.map(personRequest, PersonMessageSend.class);
			sendPersonProducer.send(personMessageSend);
			BaseResponse response = baseResponseService.addBaseResponse("Added to queue");
			return new ResponseEntity<>(response, HttpStatus.CREATED);
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}
}
