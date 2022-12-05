package com.springproject.service;

import java.util.List;

import com.springproject.dto.PersonDTO;

public interface PersonService {
	List<PersonDTO> findAll();

	PersonDTO save(PersonDTO dto);

	String delete(Long id);
}
