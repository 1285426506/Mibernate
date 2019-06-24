package com.gjj.database;

public class PrimaryKeyNotAloneException extends Exception {
	private static final long serialVersionUID = -3630685604792572718L;

	public PrimaryKeyNotAloneException() {
	}

	public PrimaryKeyNotAloneException(String message) {
		super(message);
	}

	public PrimaryKeyNotAloneException(Throwable cause) {
		super(cause);
	}

	public PrimaryKeyNotAloneException(String message, Throwable cause) {
		super(message, cause);
	}

	public PrimaryKeyNotAloneException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
}
