package com.example.codechallenge.exceptions;

public class SecretConfigurationException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6265582570561159694L;

	public SecretConfigurationException(String message, Exception e) {
		super(message, e);
	}

}
