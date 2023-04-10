package com.springproject.core.usecase.impl;

import com.springproject.core.domain.UserDomain;
import com.springproject.core.domain.dataprovider.UserDataProvider;
import com.springproject.core.usecase.UserUseCase;

public class UserUseCaseImpl implements UserUseCase {

	private UserDataProvider userDataProvider;

	public UserUseCaseImpl(UserDataProvider userDataProvider) {
		this.userDataProvider = userDataProvider;
	}

	@Override
	public UserDomain findByUsername(String username) {
		return userDataProvider.findByUsername(username);
	}

	@Override
	public String authenticate(String username) {
		UserDomain userDomain = userDataProvider.authenticate(username);
		return userDomain.getCurrentToken();
	}

	@Override
	public UserDomain refreshToken(UserDomain userDomain) {
		return userDataProvider.refreshToken(userDomain);
	}

	@Override
	public UserDomain createUser(UserDomain userDomain) {
		return userDataProvider.createUser(userDomain);
	}

	@Override
	public String invalidateSession() {
		return userDataProvider.invalidateSession();
	}

}
