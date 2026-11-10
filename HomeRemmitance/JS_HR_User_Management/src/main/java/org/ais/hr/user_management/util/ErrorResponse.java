package org.ais.hr.user_management.util;

/**
 * Created by IntelliJ IDEA.
 * Author: Murtaza Malik
 * Date: 11/1/2021
 * Time: 12:26 AM
 * Project : agentmate
 */
public class ErrorResponse {
	private int errorCode;
	private String message;
	private String detail;

	/**
	 * No Argument Constructor of Class
	 *
	 * @see ErrorResponse
	 */
	public ErrorResponse() {
	}

	/**
	 * 3 Argument Constructor of Class
	 *
	 * @see ErrorResponse
	 * @param errorCode
	 * @param message
	 * @param detail
	 */
	public ErrorResponse(final int errorCode, final String message, final String detail) {
		this.errorCode = errorCode;
		this.message = message;
		this.detail = detail;
	}

	/**
	 * Getter Method of errorCode
	 *
	 * @see ErrorResponse
	 */
	public int getErrorCode() {
		return errorCode;
	}

	/**
	 * Setter Method of errorCode
	 *
	 * @see ErrorResponse
	 * @param errorCode receives errorCode
	 */
	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}

	/**
	 * Getter Method of message
	 *
	 * @see ErrorResponse
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * Setter Method of message
	 *
	 * @see ErrorResponse
	 * @param message receives message
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * Getter Method of detail
	 *
	 * @see ErrorResponse
	 */
	public String getDetail() {
		return detail;
	}

	/**
	 * Setter Method of detail
	 *
	 * @see ErrorResponse
	 * @param detail receives detail
	 */
	public void setDetail(String detail) {
		this.detail = detail;
	}

}