package com.springproject.core.type;

public enum RoleEnum {
	ADMIN(1L, "ADMIN"), NOOB(2L, "NOOB");

	private Long id;

	private String role;

	RoleEnum(Long id, String role) {
		this.id = id;
		this.role = role;
	}

	public Long getId() {
		return id;
	}

	public String getRole() {
		return role;
	}
}
