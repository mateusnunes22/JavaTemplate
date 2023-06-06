package com.springproject.core.usecase;

import java.util.List;

import com.springproject.core.domain.PersonDomain;

public interface PersonUseCase {
	PersonDomain findByEmail(String email);
	
	List<PersonDomain> findAll();
	
	void save(PersonDomain personDomain);
	
	void delete(Long id);

	PersonDomain findById(Long id);
}
