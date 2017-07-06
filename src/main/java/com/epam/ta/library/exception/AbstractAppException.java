package com.epam.ta.library.exception;

/**
 * Abstract exception class provide behavior for all application exception
 * classes
 * 
 */
public abstract class AbstractAppException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1312792027988562008L;

	/**
	 * @param message
	 *            exception message
	 */
	public AbstractAppException(String message) {
		super(message);
	}

	/**
	 * @param message
	 *            exception message
	 * @param innerEx
	 *            Throwable inner exception instance, which is the cause of this
	 *            exception
	 */
	public AbstractAppException(String message, Throwable innerEx) {
		super(message, innerEx);
		/* Initializes the cause of this exception to the specified value */
		initCause(innerEx);
	}

	/**
	 * @param e
	 *            exception
	 */
	public AbstractAppException(Exception e) {
		super(e);
	}
}
