package net.tassia.pancake;

public class PancakeIOException extends PancakeException {

	public PancakeIOException() {
	}

	public PancakeIOException(String message) {
		super(message);
	}

	public PancakeIOException(Throwable cause) {
		super(cause);
	}

	public PancakeIOException(String message, Throwable cause) {
		super(message, cause);
	}

}
