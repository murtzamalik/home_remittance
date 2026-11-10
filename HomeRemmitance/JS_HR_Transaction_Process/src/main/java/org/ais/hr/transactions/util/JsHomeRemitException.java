package org.ais.hr.transactions.util;

/**
 * Class is used to Handle Bbs Exceptions.
 * 
 * @author Murtaza Malik
 *
 * @version 1.0
 * @see RuntimeException
 */
public class JsHomeRemitException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	private String errorMessage;

	/**
	 * Getter Method of errorMessage
	 *
	 * @see JsHomeRemitException
	 */
	public String getErrorMessage() {
		return errorMessage;
	}

	/**
	 * Setter Method of errorMessage
	 *
	 * @see JsHomeRemitException
	 * @param errorMessage receives error message
	 */
	public JsHomeRemitException(String errorMessage) {
		super(errorMessage);
		this.errorMessage = errorMessage;
	}
	
	/**
	 * No Argument Constructor of Class
	 *
	 * @see JsHomeRemitException
	 */
	public JsHomeRemitException() {
		super();
	}
}
