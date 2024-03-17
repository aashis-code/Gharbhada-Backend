package com.SpringBoot.GharBhada.Exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResourceNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String fieldName;
	private String fieldValue;
	
	public ResourceNotFoundException(String fieldName, String fieldValue) {
		super(String.format("%s of id %s not found.", fieldName,fieldValue));
		this.fieldName = fieldName;
		this.fieldValue = fieldValue;
	}
	
	
}
