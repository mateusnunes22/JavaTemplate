package com.springproject.core.usecase;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.springproject.core.dataprovider.PersonDataProvider;
import com.springproject.core.domain.PersonDomain;
import com.springproject.core.usecase.impl.PersonUseCaseImpl;
import com.springproject.dataprovider.repository.entity.PersonEntity;
import com.springproject.entity.mock.PersonBase;
import com.springproject.exception.InvalidGenericException;

@ExtendWith(MockitoExtension.class)
class PersonUseCaseTest extends PersonBase {

	@InjectMocks
	public PersonUseCaseImpl personUseCase;

	@Mock
	public PersonDataProvider personDataProvider;

	@Test
	@DisplayName("Find all CRUD: List empty")
	void findAllTest() {
		when(personDataProvider.findAll()).thenReturn(new ArrayList<>());
		List<PersonDomain> result = personUseCase.findAll();
		assertEquals(result, new ArrayList<>());
	}

	@Test
	@DisplayName("Find all CRUD: Exception")
	void findAllCatchTest() {
		when(personDataProvider.findAll()).thenThrow(new InvalidGenericException(""));
		assertThrows(InvalidGenericException.class, () -> personUseCase.findAll());
	}

	@Test
	@DisplayName("Save CRUD: Object empty")
	void saveTest() {
		when(personDataProvider.save(any())).thenReturn("");
		String result = personUseCase.save(personDomain);
		assertNotNull(result);
	}

	@Test
	@DisplayName("Save CRUD: Exception")
	void saveCatchTest() {
		when(personDataProvider.save(any())).thenThrow(new InvalidGenericException(""));
		assertThrows(InvalidGenericException.class, () -> personUseCase.save(personDomain));
	}

	@Test
	@DisplayName("Edit Entity")
	void editEntityTest() {
		when(personDataProvider.save(any())).thenReturn("");
		String result = personUseCase.save(personDomain);
		assertNotNull(result);
	}

	@Test
	@DisplayName("Delete CRUD: Void method")
	void deleteTest() {
		verify(personDataProvider, times(0)).delete(1L);
		personUseCase.delete(1L);
	}

	@Test
	@DisplayName("Delete CRUD: Exception")
	void deleteCatchTest() {
		doThrow(new InvalidGenericException("")).when(personDataProvider).delete(anyLong());
		assertThrows(InvalidGenericException.class, () -> personUseCase.delete(1L));
	}
	
	@Test
	@DisplayName("Find by email")
	void findByEmailTest() {
		List<PersonDomain> result = personUseCase.findByEmail(personDomain.getEmail());
		assertNotNull(result);
	}

	@Test
	@DisplayName("Find by id")
	void findByIdTest() {
		PersonDomain result = personUseCase.findById(personDomain.getId());
		assertNull(result);
	}

	public PersonEntity personEdited() {
		PersonEntity personEntityEdited = new PersonEntity();
		personEntityEdited.setId(personEntityPostSave.getId());
		personEntityEdited.setEmail(personEntityPostSave.getEmail());
		personEntityEdited.setName(personEntityPostSave.getName());
		personEntityEdited.setCreatedBy(personEntityPostSave.getCreatedBy());
		personEntityEdited.setLastModifiedBy(personEntityPostSave.getLastModifiedBy());
		personEntityEdited.setCreatedDate(personEntityPostSave.getCreatedDate());
		personEntityEdited.setLastModifiedDate(personEntityPostSave.getLastModifiedDate());
		return personEntityEdited;
	}

}
