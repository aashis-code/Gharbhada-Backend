package com.SpringBoot.GharBhada.Exception;

public class NoSpecificHomeOwnership extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String personId;
	private String homeId;

	public NoSpecificHomeOwnership(String personId, String homeId) {
		super(String.format("Person with %s doesnot owns %s home.", personId, homeId));
		this.personId = personId;
		this.homeId = homeId;
	}

}
