package org.ais.hr.login.util;

/**
 * Class is used to Handle Bbs Exceptions.
 * 
 * @author Murtaza Malik
 *
 * @version 1.0
 * @see RuntimeException
 */
public class JsTbmlException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	private String errorMessage;

	/**
	 * Getter Method of errorMessage
	 *
	 * @see JsTbmlException
	 */
	public String getErrorMessage() {
		return errorMessage;
	}

	/**
	 * Setter Method of errorMessage
	 *
	 * @see JsTbmlException
	 * @param errorMessage receives error message
	 */
	public JsTbmlException(String errorMessage) {
		super(errorMessage);
		this.errorMessage = errorMessage;
	}
	
	/**
	 * No Argument Constructor of Class
	 *
	 * @see JsTbmlException
	 */
	public JsTbmlException() {
		super();
	}
}
