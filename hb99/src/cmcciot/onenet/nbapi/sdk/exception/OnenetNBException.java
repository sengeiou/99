package cmcciot.onenet.nbapi.sdk.exception;

public class OnenetNBException extends RuntimeException {

	private static final long serialVersionUID = 7026558039484424852L;
	private NBStatus status;
	private String message = null;

	public OnenetNBException(NBStatus status) {
		this.status = status;
	}

	public OnenetNBException(NBStatus status, String message) {
		this.status = status;
		this.message = message;
	}

	public int getErrorNo() {
		return status.getErrorNo();
	}

	public String getError() {
		if (message != null) {
			return status.getError() + ": " + message;
		} else {
			return status.getError();
		}
	}
}
