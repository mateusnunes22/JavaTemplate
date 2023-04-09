package com.springproject.core.usecase;

import com.springproject.core.domain.UserDomain;

public interface UserUseCase {
	UserDomain findByUsername(String username);
	
	String authenticate(String username);
	
	void refreshToken(UserDomain userDomain);

	UserDomain createUser(UserDomain userDomain);
}
