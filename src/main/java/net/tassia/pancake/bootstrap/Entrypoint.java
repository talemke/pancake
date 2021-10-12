package net.tassia.pancake.bootstrap;

import org.jetbrains.annotations.NotNull;

/**
 * The JVM-entrypoint class of Pancake.
 *
 * @since Pancake 1.0
 * @author Tassilo
 */
public final class Entrypoint {

	/**
	 * The startup parameters.
	 */
	public static final StartupParameters PARAMETERS = new StartupParameters();

	/**
	 * The timestamp when Pancake has been started.
	 */
	public static final long STARTUP = System.currentTimeMillis();



	/**
	 * The JVM-entrypoint method of Pancake.
	 *
	 * @param args command-line arguments
	 */
	public static void main(@NotNull String[] args) {

		// Populate startup parameters
		PARAMETERS.fromArguments(args);

		try {

			// Launch application
			// TODO

		} catch (Throwable ex) {
			// Oop, we encountered an error during runtime.
			exit(ex);
			return;
		}

		// Done, without failure!
		exit(ExitCode.STANDARD);

	}

	/**
	 * Exits the JVM with the given error.
	 *
	 * @param error the error
	 */
	private static void exit(@NotNull Throwable error) {
		// TODO: Is known error?
		exit(ExitCode.UNKNOWN_ERROR);
	}

	/**
	 * Exits the JVM with the given status code.
	 *
	 * @param code the status code
	 *
	 * @see ExitCode
	 */
	private static void exit(int code) {
		System.exit(code);
		throw new AssertionError("System::exit did not halt the JVM.");
	}



	/**
	 * Static class.
	 */
	private Entrypoint() {
	}

}
