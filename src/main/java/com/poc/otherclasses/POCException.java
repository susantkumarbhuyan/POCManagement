package com.poc.otherclasses;

public class POCException extends Exception {

	private static final long serialVersionUID = -3965140316789517795L;

	public POCException() {

	}

	public POCException(String message) {
		super(message);
	}

	public POCException(Throwable e) {
		super(e);
	}

	public POCException(String message, Throwable e) {
		super(message, e);
	}

}