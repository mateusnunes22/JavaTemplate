package com.springproject.core.domain;

import java.util.ArrayList;
import java.util.List;

import com.springproject.core.type.YesNoEnum;

public class UserDomain extends BaseDomain {

	public UserDomain() {
	}

	public UserDomain(Long id, String name, YesNoEnum isActive, String username, String password,
			List<String> authorities, String currentToken) {
		setId(id);
		setName(name);
		setIsActive(isActive);
		this.username = username;
		this.password = password;
		this.authorities = authorities;
		this.currentToken = currentToken;
	}

	private String username;

	private String password;

	private List<String> authorities = new ArrayList<>();

	private String currentToken;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<String> getAuthorities() {
		return authorities;
	}

	public void setAuthorities(List<String> authorities) {
		this.authorities = authorities;
	}

	public String getCurrentToken() {
		return currentToken;
	}

	public void setCurrentToken(String currentToken) {
		this.currentToken = currentToken;
	}
}
