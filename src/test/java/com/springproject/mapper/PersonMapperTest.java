package com.springproject.mapper;

import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.springproject.core.domain.PersonDomain;
import com.springproject.dataprovider.repository.PersonRepository;
import com.springproject.entity.mock.PersonBase;

@ExtendWith(MockitoExtension.class)
class PersonMapperTest extends PersonBase {

	@InjectMocks
	public PersonMapper mapper;

	@Mock
	public PersonRepository personRepository;

	@Test
	@DisplayName(value = "Find Persons by email")
	void findByEmailTest() {
		List<PersonDomain> personDomains = mapper.toDomainList(List.of(personEntity));
		assertNotNull(personDomains);
	}
	
}
