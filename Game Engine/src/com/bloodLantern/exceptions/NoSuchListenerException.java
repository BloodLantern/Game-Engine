package com.bloodLantern.exceptions;

/**
 * Exception thrown when trying to remove a Listener from the listeners list
 * while it is not saved in.
 *
 * @author BloodLantern
 */
public class NoSuchListenerException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2073285108726183950L;

	/**
	 * Constructs a new NoSuchListenerException.
	 */
	public NoSuchListenerException() {
	}

	/**
	 * Constructs a new NoSuchListenerException with a custom message.
	 *
	 * @param message A custom message to explain the Exception.
	 */
	public NoSuchListenerException(String message) {
		super(message);
	}

}
