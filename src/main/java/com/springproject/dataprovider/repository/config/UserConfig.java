package com.springproject.dataprovider.repository.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.springproject.core.domain.dataprovider.UserDataProvider;
import com.springproject.core.usecase.impl.UserUseCaseImpl;

@Configuration
public class UserConfig {

	@Bean
	public UserUseCaseImpl userUseCaseImpl(UserDataProvider userDataProvider) {
		return new UserUseCaseImpl(userDataProvider);
	}
}
