package com.SpringBoot.GharBhada.Exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserAlreadyExist extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String email;
	
	public UserAlreadyExist(String email) {
		super(String.format("%s already exist.", email));
		this.email = email;
	}
	
	
}
