package com.oty.server.exception;

public class CommunicationException extends RuntimeException {

	private static final long serialVersionUID = 8659563845098065708L;

	public CommunicationException() {
		super();
	}

	public CommunicationException(String message) {
		super(message);
	}

	public CommunicationException(String message, Throwable cause) {
		super(message, cause);
	}

	public CommunicationException(Throwable cause) {
		super(cause);
	}

}
