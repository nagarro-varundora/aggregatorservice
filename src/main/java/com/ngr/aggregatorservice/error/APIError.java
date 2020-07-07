package com.ngr.aggregatorservice.error;

public class APIError extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int httpCode;

	public APIError(int httpCode) {
		this.httpCode = httpCode;
	}

	public int getHttpCode() {
		return httpCode;
	}

	public void setHttpCode(int httpCode) {
		this.httpCode = httpCode;
	}
}
