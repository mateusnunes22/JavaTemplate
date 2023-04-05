package com.springproject.core.domain;

import com.springproject.core.type.ActiveEnum;

public class BaseDomain {
	private Long id;

	private String name;

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

	public ActiveEnum getIsActive() {
		return isActive;
	}

	public void setIsActive(ActiveEnum isActive) {
		this.isActive = isActive;
	}
}
