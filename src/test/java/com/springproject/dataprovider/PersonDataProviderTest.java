package com.springproject.dataprovider;

import com.springproject.core.domain.PersonDomain;
import com.springproject.dataprovider.impl.PersonDataProviderImpl;
import com.springproject.dataprovider.repository.PersonRepository;
import com.springproject.dataprovider.repository.entity.PersonEntity;
import com.springproject.entity.mock.PersonBase;
import com.springproject.mapper.PersonMapper;
import org.hibernate.HibernateException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

import static com.springproject.core.messages.PersonMsg.ERROR_PERSON_NOT_FOUND;
import static com.springproject.core.messages.PersonMsg.ERROR_SAVE_PERSON;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThrows;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PersonDataProviderTest extends PersonBase {

    @InjectMocks
    public PersonDataProviderImpl personDataProvider;

    @Mock
    public PersonRepository personRepository;

    @Mock
    public PersonMapper mapper;

    @Test
    @DisplayName(value = "Find Persons by email: Success")
    void findByEmailTest() {
        when(personRepository.findByEmail(anyString())).thenReturn(Optional.of(personEntity));
        when(mapper.map(personEntity, PersonDomain.class)).thenReturn(personDomain);
        PersonDomain result = personDataProvider.findByEmail("");
        assertNotNull(result);
    }

    @Test
    @DisplayName(value = "Find Persons by email: Exception")
    void findByEmailThrowTest() {
        assertThrows(EntityNotFoundException.class, () -> personDataProvider.findByEmail("a@levva.com"));
    }

    @Test
    @DisplayName(value = "Find all Persons: Success")
    void findAllSuccessTest() {
        when(mapper.toDomainList(anyList())).thenReturn(List.of(personDomain));
        List<PersonDomain> result = personDataProvider.findAll();
        assertNotNull(result);
    }

    @Test
    @DisplayName(value = "Find all Persons: Exception")
    void findAllThrowTest() {
        when(personRepository.findAll()).thenThrow(new HibernateException(ERROR_PERSON_NOT_FOUND));
        assertThrows(HibernateException.class, () -> personDataProvider.findAll());
    }

    @Test
    @DisplayName(value = "Save one Person: Success")
    void saveSuccessTest() {
        when(mapper.map(personDomain, PersonEntity.class)).thenReturn(personEntity);
        personDataProvider.save(personDomain);
        verify(personRepository, times(1)).save(personEntity);
    }

    @Test
    @DisplayName(value = "Save one Person: Exception")
    void saveThrowTest() {
        when(mapper.map(personDomain, PersonEntity.class)).thenThrow(new HibernateException(ERROR_SAVE_PERSON));
        assertThrows(HibernateException.class, () -> personDataProvider.save(personDomain));
    }

	@Test
	@DisplayName(value = "Delete one Person: Success")
	void deleteSuccessTest() {
		personDataProvider.delete(personEntity.getId());
		verify(personRepository).deleteById(personEntity.getId());
	}

    @Test
    @DisplayName(value = "Delete one Person: Exception")
    void deleteThrowTest() {
        doThrow(HibernateException.class).when(personRepository).deleteById(anyLong());
        Long personId = personEntity.getId();
        assertThrows(HibernateException.class, () -> personDataProvider.delete(personId));
    }

    @Test
    @DisplayName(value = "Find one Person by Id: Success")
    void findByIdSuccessTest() {
        Optional<PersonEntity> optEntity = Optional.of(personEntity);
        when(personRepository.findById(anyLong())).thenReturn(optEntity);
        when(mapper.map(personEntity, PersonDomain.class)).thenReturn(personDomain);
        PersonDomain result = personDataProvider.findById(personDomain.getId());
        assertNotNull(result);
    }

    @Test
    @DisplayName(value = "Find one Person by Id: Exception")
    void findByIdThrowTest() {
        doThrow(new EntityNotFoundException(ERROR_PERSON_NOT_FOUND)).when(personRepository).findById(anyLong());
        Long personId = personDomain.getId();
        assertThrows(HibernateException.class, () -> personDataProvider.findById(personId));
    }
}
