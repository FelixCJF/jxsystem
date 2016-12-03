package com.jju.exception;

public class DaoException extends Exception {

	private static final long serialVersionUID = 4683315515042642090L;

	public DaoException(Exception e) {
		super(e);
	}

}