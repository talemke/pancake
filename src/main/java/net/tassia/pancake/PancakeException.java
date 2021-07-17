package net.tassia.pancake;

public class PancakeException extends RuntimeException {

	public PancakeException() {
		super();
	}

	public PancakeException(String message) {
		super(message);
	}

	public PancakeException(Throwable cause) {
		super(cause);
	}

	public PancakeException(String message, Throwable cause) {
		super(message, cause);
	}

}
