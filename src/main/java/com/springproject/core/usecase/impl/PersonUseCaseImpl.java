package com.springproject.core.usecase.impl;

import java.util.List;

import com.springproject.core.domain.dataprovider.PersonDataProvider;
import com.springproject.core.domain.PersonDomain;
import com.springproject.core.usecase.PersonUseCase;

public class PersonUseCaseImpl implements PersonUseCase {

	private PersonDataProvider personDataProvider;

	public PersonUseCaseImpl(PersonDataProvider personDataProvider) {
		this.personDataProvider = personDataProvider;
	}

	@Override
	public List<PersonDomain> findByEmail(String email) {
		return personDataProvider.findByEmail(email);
	}

	@Override
	public List<PersonDomain> findAll() {
		return personDataProvider.findAll();
	}

	@Override
	public String save(PersonDomain personDomain) {
		return personDataProvider.save(personDomain);
	}

	@Override
	public String delete(Long id) {
		return personDataProvider.delete(id);
	}

	@Override
	public PersonDomain findById(Long id) {
		return personDataProvider.findById(id);
	}

}
