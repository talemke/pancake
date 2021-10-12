package net.tassia.pancake.bootstrap;

/**
 * Contains the exit codes used by Pancake.
 *
 * @since Pancake 1.0
 * @author Tassilo
 */
public final class ExitCode {

	/**
	 * The standard exit code. To be used when the application is exiting due to user-input, without any error.
	 */
	public static final int STANDARD = 0;

	/**
	 * The standard error exit code. The application encountered an unexpected error
	 * and didn't know how to recover from it.
	 */
	public static final int UNKNOWN_ERROR = 1;



	/**
	 * Static class.
	 */
	private ExitCode() {
	}

}
