package com.springproject.dataprovider.impl;

import com.springproject.core.domain.PersonDomain;
import com.springproject.core.domain.dataprovider.PersonDataProvider;
import com.springproject.dataprovider.repository.PersonRepository;
import com.springproject.dataprovider.repository.entity.PersonEntity;
import com.springproject.exception.GlobalExceptionHandler;
import com.springproject.mapper.PersonMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.List;

import static com.springproject.core.messages.PersonMsg.*;

@Component
@Transactional
@Slf4j
public class PersonDataProviderImpl implements PersonDataProvider {

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private PersonMapper mapper;

    @Override
    public PersonDomain findByEmail(String email) {
        try {
            log.info("Data provider: Starting to find a person by an e-mail");
            PersonEntity personEntity = personRepository.findByEmail(email).orElseThrow(
                    () -> new EntityNotFoundException(ERROR_PERSON_NOT_FOUND_BY_EMAIL)
            );
            return mapper.map(personEntity, PersonDomain.class);
        } catch (Exception e) {
            return GlobalExceptionHandler.entityExceptionAndThrow(e, ERROR_PERSON_NOT_FOUND_BY_EMAIL, email);
        }

    }

    @Override
    public List<PersonDomain> findAll() {
        try {
            log.info("Data provider: Starting find all persons");
            List<PersonEntity> personEntities = personRepository.findAll();
            return mapper.toDomainList(personEntities);
        } catch (Exception e) {
            return GlobalExceptionHandler.hibernateExceptionAndThrow(e, ERROR_PERSON_NOT_FOUND, null);
        }
    }

    @Override
    public void save(PersonDomain personDomain) {
        try {
            log.info("Data provider: Starting to save a person on repository by domain");
            personRepository.save(mapper.map(personDomain, PersonEntity.class));
        } catch (Exception e) {
            GlobalExceptionHandler.hibernateExceptionAndThrow(e, ERROR_SAVE_PERSON, personDomain);
        }
    }

    @Override
    public void delete(Long id) {
        try {
            log.info("Data provider: Starting to delete a person on repository by id");
            personRepository.deleteById(id);
        } catch (Exception e) {
            GlobalExceptionHandler.hibernateExceptionAndThrow(e, ERROR_DELETE_PERSON, id);
        }
    }

    @Override
    public PersonDomain findById(Long id) {
        try {
            log.info("Data provider: Starting to find a person on repository by id");
            PersonEntity personEntity = personRepository.findById(id)
                    .orElseThrow(() -> new EntityNotFoundException(ERROR_PERSON_NOT_FOUND));
            return mapper.map(personEntity, PersonDomain.class);
        } catch (Exception e) {
            return GlobalExceptionHandler.hibernateExceptionAndThrow(e, ERROR_PERSON_NOT_FOUND, id);
        }
    }


}
