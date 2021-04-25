package net.tassia.pancake.logging

/**
 * Defines the level, or importance, of a log message.
 *
 * @since Pancake 1.0
 * @author Tassilo
 */
enum class Level {

	/**
	 * The default log level. Used for logging informative stuff.
	 */
	INFO,

	/**
	 * This level is used for debugging, it logs stuff that is only informative
	 * when debugging. In production, this is usually disabled.
	 */
	DEBUG,

	/**
	 * The warning level. Something went wrong, but it does not cause the execution to stop.
	 */
	WARNING,

	/**
	 * Something went wrong and causes the current execution to stop,
	 * however it does not terminate the application.
	 */
	ERROR,

	/**
	 * Something went horribly wrong and the application needs to be terminated.
	 */
	SEVERE;

}
