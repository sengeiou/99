package com.oty.server.exception;

public class LostConnectException extends CommunicationException {
 
	private static final long serialVersionUID = -5214762126290844189L;
 
    public LostConnectException() {
        super();
    }
 
    public LostConnectException(String message) {
        super(message);
    }
 
    public LostConnectException(String message, Throwable cause) {
        super(message, cause);
    }
 
    public LostConnectException(Throwable cause) {
        super(cause);
    }

}
