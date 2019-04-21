package com.oty.server.exception;

public class DispatchException extends Exception {
 
	private static final long serialVersionUID = 1753818150236729137L;
 
    public DispatchException() {
        super();
    }
 
    public DispatchException(String message) {
        super(message);
    }
 
    public DispatchException(String message, Throwable cause) {
        super(message, cause);
    }
 
    public DispatchException(Throwable cause) {
        super(cause);
    }

}
