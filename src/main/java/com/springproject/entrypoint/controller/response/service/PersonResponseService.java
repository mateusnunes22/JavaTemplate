package com.springproject.entrypoint.controller.response.service;

import java.util.List;

import com.springproject.core.domain.PersonDomain;
import com.springproject.entrypoint.controller.response.PersonResponse;

public interface PersonResponseService {
	PersonResponse getAll(List<PersonDomain> personDomains);

	PersonResponse getOne(PersonDomain personDomain);
}
