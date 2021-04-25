package net.tassia.pancake.util

/**
 * Contains utilities to interact with the System.
 *
 * @since Pancake 1.0
 * @author Tassilo
 */
expect object System {

	/**
	 * Returns the current timestamp in millis since 1st Jan 1970.
	 *
	 * @return timestamp
	 */
	fun timestamp(): Long

	/**
	 * Exits the application/process.
	 *
	 * @param status the exit code
	 */
	fun exit(status: Int = 0)

}
