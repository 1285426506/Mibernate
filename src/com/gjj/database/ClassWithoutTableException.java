package com.gjj.database;

public class ClassWithoutTableException extends Exception {
	private static final long serialVersionUID = 1682737322671750231L;

	public ClassWithoutTableException() {
	}

	public ClassWithoutTableException(String message) {
		super(message);
	}

	public ClassWithoutTableException(Throwable cause) {
		super(cause);
	}

	public ClassWithoutTableException(String message, Throwable cause) {
		super(message, cause);
	}

	public ClassWithoutTableException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
}
