package com.springproject.core.usecase;

import com.springproject.core.domain.PersonDomain;
import com.springproject.core.domain.dataprovider.PersonDataProvider;
import com.springproject.core.usecase.impl.PersonUseCaseImpl;
import com.springproject.entity.mock.PersonBase;
import org.hibernate.HibernateException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

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
		when(personDataProvider.findAll()).thenThrow(new HibernateException(""));
		assertThrows(HibernateException.class, () -> personUseCase.findAll());
	}

	@Test
	@DisplayName("Save CRUD: Object empty")
	void saveTest() {
		personUseCase.save(personDomain);
		verify(personDataProvider, times(1)).save(personDomain);
	}

//	@Test
//	@DisplayName("Save CRUD: Exception")
//	void saveCatchTest() {
//		when(personDataProvider.save(any())).thenThrow(new HibernateException(""));
//		assertThrows(HibernateException.class, () -> personUseCase.save(personDomain));
//	}

	@Test
	@DisplayName("Edit Entity")
	void editEntityTest() {
		personUseCase.save(personDomain);
		verify(personDataProvider, times(1)).save(personDomain);
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
		doThrow(new HibernateException("")).when(personDataProvider).delete(anyLong());
		assertThrows(HibernateException.class, () -> personUseCase.delete(1L));
	}
	
	@Test
	@DisplayName("Find by email")
	void findByEmailTest() {
		when(personDataProvider.findByEmail(anyString())).thenReturn(personDomain);
		PersonDomain result = personUseCase.findByEmail(personDomain.getEmail());
		assertNotNull(result);
	}

	@Test
	@DisplayName("Find by id")
	void findByIdTest() {
		PersonDomain result = personUseCase.findById(personDomain.getId());
		assertNull(result);
	}

}
