package com.scc.judge.utils;

import com.scc.judge.exceptions.EnumValidationException;

public enum ShowEnum {
	
	ESIN("ESIN"), ESNA("ESNA"), ESRE("ESRE"), RANA("RANA"), ALL("ALL");
	
	private String value;
	
	private ShowEnum(String value) {
		this.value = value;
	}
	
	public static ShowEnum fromValue(String value) throws EnumValidationException {

        if(value == null) {
            throw new EnumValidationException(value, "ShowEnum");
        }
        
		for (ShowEnum category : values()) {
			if (category.value.equalsIgnoreCase(value)) {
				return category;
			}
		}
		throw new EnumValidationException(value, "ShowEnum");
	}

}
