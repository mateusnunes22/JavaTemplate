package com.springproject.core.type;

public enum ActiveEnum {
	YES("Yes"), NO("No");

	private final String active;

	private ActiveEnum(String active) {
		this.active = active;
	}

	public String getActive() {
		return this.active;
	}
}
