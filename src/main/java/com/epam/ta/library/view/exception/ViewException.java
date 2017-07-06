package com.epam.ta.library.view.exception;

import com.epam.ta.library.exception.AbstractAppException;

/**
* <code>ViewException</code> error class inherits
* <code>AbstractAppException<code> class and used for exception handling on the
* View level.
* 
*/

public class ViewException extends AbstractAppException {

	private static final long serialVersionUID = 1825687671346780282L;

	/**
	 * @param message
	 *            exception message
	 * @param innerEx
	 *            Throwable inner exception instance, which is the cause of this
	 *            exception
	 */
	public ViewException(String message, Throwable innerEx) {
		super(message);
		initCause(innerEx);
	}

	/**
	 * @param message
	 *            exception message
	 */
	public ViewException(String message) {
		super(message);
	}

}
