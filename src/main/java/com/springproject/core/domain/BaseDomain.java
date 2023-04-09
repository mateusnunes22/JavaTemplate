package com.springproject.core.domain;

import com.springproject.core.type.YesNoEnum;

public class BaseDomain {
	private Long id;

	private String name;

	private YesNoEnum isActive;

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

	public YesNoEnum getIsActive() {
		return isActive;
	}

	public void setIsActive(YesNoEnum isActive) {
		this.isActive = isActive;
	}
}
