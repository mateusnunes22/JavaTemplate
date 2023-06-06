package com.springproject.core.usecase;

import com.springproject.core.domain.UserDomain;

public interface UserUseCase {
	UserDomain findByUsername(String username);
	
	String authenticate(String username);
	
	UserDomain refreshToken(UserDomain userDomain);
	
	void invalidateSession();

	UserDomain createUser(UserDomain userDomain);
}
