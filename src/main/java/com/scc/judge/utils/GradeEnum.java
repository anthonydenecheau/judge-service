package com.scc.judge.utils;

import com.scc.judge.exceptions.EnumValidationException;

public enum GradeEnum {
	
	ELEVE("eleve"), JUGE("juge"), ALL("Tous");
	
	private String value;
	
	private GradeEnum(String value) {
		this.value = value;
	}
	
	public String getValue() {
      return value;
   }

   public static GradeEnum fromValue(String value) throws EnumValidationException {

        if(value == null) {
            throw new EnumValidationException(value, "GradeEnum");
        }
        
		for (GradeEnum category : values()) {
			if (category.value.equalsIgnoreCase(value)) {
				return category;
			}
		}
		throw new EnumValidationException(value, "GradeEnum");
	}

}
