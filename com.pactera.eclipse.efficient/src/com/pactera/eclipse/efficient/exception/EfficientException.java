package com.pactera.eclipse.efficient.exception;

public class EfficientException extends RuntimeException {

	private static final long serialVersionUID = -7230485536540275626L;

	public EfficientException() {
		super();
	}

	public EfficientException(String message, Throwable cause) {
		super(message, cause);
	}

	public EfficientException(String message) {
		super(message);
	}

	public EfficientException(Throwable cause) {
		super(cause);
	}

}
