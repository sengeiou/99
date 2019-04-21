package pub.types;

import java.io.Serializable;
 
public class ValidationError extends RuntimeException implements Serializable {
 
	private static final long serialVersionUID = -4045992137295881855L;

	public ValidationError(String message) {
		super(message);
	}


}
