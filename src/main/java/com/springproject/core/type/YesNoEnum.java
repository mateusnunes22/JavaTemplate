package com.springproject.core.type;

public enum YesNoEnum {
	YES("Yes"), NO("No");

	private final String yesNo;

	private YesNoEnum(String yesNo) {
		this.yesNo = yesNo;
	}

	public String getYesNo() {
		return this.yesNo;
	}
}
