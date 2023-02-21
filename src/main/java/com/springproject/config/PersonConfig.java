package com.springproject.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.springproject.core.dataprovider.PersonDataProvider;
import com.springproject.core.usecase.impl.PersonUseCaseImpl;

@Configuration
public class PersonConfig {

	@Bean
	public PersonUseCaseImpl personUseCaseImpl(PersonDataProvider personDataProvider) {
		return new PersonUseCaseImpl(personDataProvider);
	}
}
