package com.springproject.core.domain.dataprovider;

import com.springproject.core.domain.UserDomain;

public interface UserDataProvider {
	
	UserDomain findByUsername(String username);
	
	UserDomain authenticate(String username);
	
	void refreshToken(UserDomain userDomain);

	UserDomain createUser(UserDomain map);

}
