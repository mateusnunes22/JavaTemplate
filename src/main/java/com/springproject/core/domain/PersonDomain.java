package com.springproject.core.domain;

import com.springproject.core.type.ActiveEnum;

public class PersonDomain {
	public PersonDomain() {
	}

	public PersonDomain(Long id, String name, String email) {
		this.id = id;
		this.name = name;
		this.email = email;
	}

	private Long id;

	private String name;

	private String email;

	private ActiveEnum isActive;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public ActiveEnum getIsActive() {
		return isActive;
	}

	public void setIsActive(ActiveEnum isActive) {
		this.isActive = isActive;
	}

}
