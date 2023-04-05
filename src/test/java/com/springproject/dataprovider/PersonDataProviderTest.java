package com.springproject.dataprovider;

import com.springproject.core.domain.PersonDomain;
import com.springproject.dataprovider.impl.PersonDataProviderImpl;
import com.springproject.dataprovider.repository.PersonRepository;
import com.springproject.dataprovider.repository.entity.PersonEntity;
import com.springproject.entity.mock.PersonBase;
import com.springproject.mapper.PersonMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PersonDataProviderTest extends PersonBase {

	@InjectMocks
	public PersonDataProviderImpl personDataProvider;

	@Mock
	public PersonRepository personRepository;

	@Mock
	public PersonMapper mapper;

	@Test
	@DisplayName(value = "Find Persons by email")
	void findByEmailTest() {
		when(personRepository.findByEmail(anyString())).thenReturn(List.of(personEntity));
		when(mapper.toDomainList(anyList())).thenReturn(List.of(personDomain));
		List<PersonDomain> result = personDataProvider.findByEmail(personResponse.getEmail());
		assertNotNull(result);
	}
	
	@Test
	@DisplayName(value = "Find all Persons")
	void findAllTest() {
		when(mapper.toDomainList(anyList())).thenReturn(List.of(personDomain));
		List<PersonDomain> result = personDataProvider.findAll();
		assertNotNull(result);
	}

	@Test
	@DisplayName(value = "Save one Person")
	void saveTest() {
		when(mapper.map(personDomain, PersonEntity.class)).thenReturn(personEntity);
		String result = personDataProvider.save(personDomain);
		assertNotNull(result);
	}
	
	@Test
	@DisplayName(value = "Delete one Person")
	void deleteTest() {
		String result = personDataProvider.delete(personEntity.getId());
		assertNotNull(result);
	}
	
	@Test
	@DisplayName(value = "Find one Person by Id")
	void findByIdTest() {
		Optional<PersonEntity> optEntity = Optional.of(personEntity);
		when(personRepository.findById(anyLong())).thenReturn(optEntity);
		when(mapper.map(personEntity, PersonDomain.class)).thenReturn(personDomain);
		PersonDomain result = personDataProvider.findById(personDomain.getId());
		assertNotNull(result);
	}
}
