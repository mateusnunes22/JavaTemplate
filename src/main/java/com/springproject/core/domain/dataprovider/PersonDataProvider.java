package com.springproject.core.domain.dataprovider;

import java.util.List;

import com.springproject.core.domain.PersonDomain;

public interface PersonDataProvider {
    PersonDomain findByEmail(String email);

    List<PersonDomain> findAll();

    void save(PersonDomain personDomain);

    void delete(Long id);

    PersonDomain findById(Long id);

}
