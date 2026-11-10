package org.ais.hr.fi.util;

/**
 * Class is used to Handle Bbs Exceptions.
 * 
 * @author Murtaza Malik
 *
 * @version 1.0
 * @see RuntimeException
 */
public class JsHrFiException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	private String errorMessage;

	/**
	 * Getter Method of errorMessage
	 *
	 * @see JsHrFiException
	 */
	public String getErrorMessage() {
		return errorMessage;
	}

	/**
	 * Setter Method of errorMessage
	 *
	 * @see JsHrFiException
	 * @param errorMessage receives error message
	 */
	public JsHrFiException(String errorMessage) {
		super(errorMessage);
		this.errorMessage = errorMessage;
	}
	
	/**
	 * No Argument Constructor of Class
	 *
	 * @see JsHrFiException
	 */
	public JsHrFiException() {
		super();
	}
}
