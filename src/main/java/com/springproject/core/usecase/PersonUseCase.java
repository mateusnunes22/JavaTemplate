package com.springproject.core.usecase;

import java.util.List;

import com.springproject.core.domain.PersonDomain;

public interface PersonUseCase {
	List<PersonDomain> findByEmail(String email);
	
	List<PersonDomain> findAll();
	
	String save(PersonDomain personDomain);
	
	String delete(Long id);

	PersonDomain findById(Long id);
}
