package com.springproject.entrypoint.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
import com.springproject.entrypoint.controller.request.PersonRequest;
import com.springproject.entrypoint.controller.response.PersonResponse;
import com.springproject.mapper.PersonMapper;

@RestController
@RequestMapping(path = "/api/v1/person")
public class PersonController {

	@Autowired
	private PersonUseCase personUseCase;

	@Autowired
	private PersonMapper mapper;

	@PostMapping
	public ResponseEntity<String> save(@RequestBody PersonRequest personRequest) {
		PersonDomain personDomain = mapper.map(personRequest, PersonDomain.class);
		String response = personUseCase.save(personDomain);
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}

	@GetMapping
	public ResponseEntity<List<PersonResponse>> findAll() {
		List<PersonResponse> response = personUseCase.findAll().stream()
				.map(element -> mapper.map(element, PersonResponse.class)).toList();
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
