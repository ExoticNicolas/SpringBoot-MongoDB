package com.SpringBootMongoDB.services.exceptions;

public class ObjectNotFoundException extends RuntimeException{
	private static final long serialVersionUID = 1L;
	
	public ObjectNotFoundException(Object id) {
		super("Object Not Found :" + id);
	}

}
