package net.tassia.pancake.bootstrap

/**
 * Contains the exit codes used by Pancake.
 *
 * @since Pancake 1.0
 */
enum class ExitCode(val code: Int) {

	/**
	 * The standard exit code. To be used when the application is exiting due to user-input, without any error.
	 */
	STANDARD(0),

	/**
	 * The standard error exit code. The application encountered an unexpected error
	 * and didn't know how to recover from it.
	 */
	UNKNOWN_ERROR(1);

}
