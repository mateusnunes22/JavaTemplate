package com.springproject.core.dataprovider;

import java.util.List;

import com.springproject.core.domain.PersonDomain;

public interface PersonDataProvider {
	List<PersonDomain> findByEmail(String email);

	List<PersonDomain> findAll();

	String save(PersonDomain personDomain);

	String delete(Long id);

	PersonDomain findById(Long id);

}
