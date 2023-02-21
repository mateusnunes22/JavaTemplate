package com.springproject.mapper;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.springproject.core.domain.PersonDomain;

@Component
public class PersonMapper extends ModelMapper{

	public List<PersonDomain> toDomainList(List<?> fromList) {
		return fromList.stream().map(element -> map(element, PersonDomain.class)).toList();
	}
}
