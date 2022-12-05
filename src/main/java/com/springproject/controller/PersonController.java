package com.springproject.controller;

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

import com.springproject.dto.PersonDTO;
import com.springproject.service.PersonService;

@RestController
@RequestMapping(path = "/person")
public class PersonController {

	@Autowired
	private PersonService personService;

	@PostMapping
	public ResponseEntity<PersonDTO> save(@RequestBody PersonDTO dto) {
		return new ResponseEntity<>(personService.save(dto), HttpStatus.OK);
	}

	@GetMapping
	public ResponseEntity<List<PersonDTO>> findAll() {
		return new ResponseEntity<>(personService.findAll(), HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> delete(@PathVariable("id") Long id) {
		return new ResponseEntity<>(personService.delete(id), HttpStatus.OK);
	}
}
