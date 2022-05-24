package com.alkemy.ong.auth.utility;

public enum RoleEnum {
	USER, ADMIN;

	private static final String PREFIX = "ROLE_";
	public String getRoleName() {
		return PREFIX + this.name();
	}
}
