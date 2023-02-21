package com.springproject.dataprovider.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.springproject.core.dataprovider.PersonDataProvider;
import com.springproject.core.domain.PersonDomain;
import com.springproject.dataprovider.repository.PersonRepository;
import com.springproject.dataprovider.repository.entity.PersonEntity;
import com.springproject.exception.InvalidGenericException;
import com.springproject.mapper.PersonMapper;

@Component
public class PersonDataProviderImpl implements PersonDataProvider {

	@Autowired
	private PersonRepository personRepository;

	@Autowired
	private PersonMapper mapper;

	@Override
	public List<PersonDomain> findByEmail(String email) {
		List<PersonEntity> personEntities = personRepository.findByEmail(email);
		return mapper.toDomainList(personEntities);
	}

	@Override
	public List<PersonDomain> findAll() {
		List<PersonEntity> personEntities = personRepository.findAll();
		return mapper.toDomainList(personEntities);
	}

	@Override
	public String save(PersonDomain personDomain) {
		personRepository.save(mapper.map(personDomain, PersonEntity.class));
		return "Person saved";
	}

	@Override
	public String delete(Long id) {
		personRepository.deleteById(id);
		return "Person deleted";
	}

	@Override
	public PersonDomain findById(Long id) {
		PersonEntity personEntity = personRepository.findById(id)
				.orElseThrow(() -> new InvalidGenericException("Person not found"));
		return mapper.map(personEntity, PersonDomain.class);
	}

}
