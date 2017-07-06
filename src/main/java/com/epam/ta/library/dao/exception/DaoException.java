package com.epam.ta.library.dao.exception;

import com.epam.ta.library.exception.AbstractAppException;

/**
 * <code>DaoException</code> error class inherits
 * <code>AbstractAppException<code> class and used for exception handling on the
 * DAO level.
 * 
 */

public class DaoException extends AbstractAppException {

	private static final long serialVersionUID = -375795020264735244L;

	/**
	 * @param message
	 *            exception message
	 * @param innerEx
	 *            Throwable inner exception instance, which is the cause of this
	 *            exception
	 */
	public DaoException(String message, Throwable innerEx) {
		super(message);
		initCause(innerEx);
	}

	/**
	 * @param message
	 *            exception message
	 */
	public DaoException(String message) {
		super(message);
	}

}
