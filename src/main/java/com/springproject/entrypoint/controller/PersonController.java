package com.springproject.entrypoint.controller;

import com.springproject.core.domain.PersonDomain;
import com.springproject.core.usecase.PersonUseCase;
import com.springproject.entrypoint.controller.producer.SendPersonProducer;
import com.springproject.entrypoint.controller.producer.send.PersonMessageSend;
import com.springproject.entrypoint.controller.request.PersonRequest;
import com.springproject.entrypoint.controller.response.PersonResponse;
import com.springproject.mapper.PersonMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/v1/person")
public class PersonController {

	@Autowired
	private PersonUseCase personUseCase;

	@Autowired
	private SendPersonProducer sendPersonProducer;

	@Autowired
	private PersonMapper mapper;

	@PostMapping
	public ResponseEntity<String> save(@RequestBody PersonRequest personRequest) {
		PersonDomain personDomain = mapper.map(personRequest, PersonDomain.class);
		String response = personUseCase.save(personDomain);
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}

	@PostMapping(value = "/kafka-producer")
	public ResponseEntity<String> saveKafkaProducer(@RequestBody PersonRequest personRequest) {
		PersonMessageSend personMessageSend = mapper.map(personRequest, PersonMessageSend.class);
		sendPersonProducer.send(personMessageSend);
		return new ResponseEntity<>("Added to queue", HttpStatus.CREATED);
	}

	@GetMapping
	public ResponseEntity<List<PersonResponse>> findAll() {
		List<PersonDomain> personDomains = personUseCase.findAll();
		List<PersonResponse> response = mapper.toResponseList(personDomains);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<PersonResponse> findById(@PathVariable("id") Long id) {
		PersonDomain personDomain = personUseCase.findById(id);
		return new ResponseEntity<>(mapper.map(personDomain, PersonResponse.class), HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> delete(@PathVariable("id") Long id) {
		return new ResponseEntity<>(personUseCase.delete(id), HttpStatus.OK);
	}
}
