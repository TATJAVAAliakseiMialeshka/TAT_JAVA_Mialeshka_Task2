package com.epam.ta.library.service.exception;

import com.epam.ta.library.exception.AbstractAppException;

/**
 * <code>ServiceException</code> error class inherits
 * <code>AbstractAppException<code> class and used for exception handling on the
 * DAO level.
 * 
 */

public class ServiceException extends AbstractAppException {

	private static final long serialVersionUID = -2748076526818019721L;

	/**
	 * @param message
	 *            exception message
	 * @param innerEx
	 *            Throwable inner exception instance, which is the cause of this
	 *            exception
	 */
	public ServiceException(String message, Throwable innerEx) {
		super(message);
		initCause(innerEx);
	}

	/**
	 * @param message
	 *            exception message
	 */
	public ServiceException(String message) {
		super(message);
	}

	/**
	 * @param e
	 *            exception
	 */
	public ServiceException(Exception e) {
		super(e);
	}

}
