
package com.jju.exception;

public class ServiceException extends Exception {

	private static final long serialVersionUID = 4683315515042642090L;

	public ServiceException(Exception e) {
		super(e);
	}

}
