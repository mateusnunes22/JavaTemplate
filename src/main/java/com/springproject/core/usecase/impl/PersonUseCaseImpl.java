package com.springproject.core.usecase.impl;

import com.springproject.core.domain.PersonDomain;
import com.springproject.core.domain.dataprovider.PersonDataProvider;
import com.springproject.core.usecase.PersonUseCase;

import java.util.List;

public class PersonUseCaseImpl implements PersonUseCase {

    private PersonDataProvider personDataProvider;

    public PersonUseCaseImpl(PersonDataProvider personDataProvider) {
        this.personDataProvider = personDataProvider;
    }

    @Override
    public PersonDomain findByEmail(String email) {
        return personDataProvider.findByEmail(email);
    }

    @Override
    public List<PersonDomain> findAll() {
        return personDataProvider.findAll();
    }

    @Override
    public void save(PersonDomain personDomain) {
        personDataProvider.save(personDomain);
    }

    @Override
    public void delete(Long id) {
        personDataProvider.delete(id);
    }

    @Override
    public PersonDomain findById(Long id) {
        return personDataProvider.findById(id);
    }

}
