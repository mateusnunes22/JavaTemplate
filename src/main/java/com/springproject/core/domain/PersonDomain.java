package com.springproject.core.domain;

import com.springproject.core.type.YesNoEnum;

public class PersonDomain extends BaseDomain {
	public PersonDomain() {
	}

	public PersonDomain(Long id, String name, YesNoEnum isActive, String email) {
		setId(id);
		setName(name);
		setIsActive(isActive);
		this.email = email;
	}

	private String email;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
