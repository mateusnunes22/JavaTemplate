package com.springproject.entrypoint.controller.response.service;

import java.util.List;

import com.springproject.core.domain.PersonDomain;
import com.springproject.entrypoint.controller.response.PersonResponse;

public interface PersonResponseService {
	PersonResponse findAll(List<PersonDomain> personDomains);

	PersonResponse findById(PersonDomain personDomain);
}
