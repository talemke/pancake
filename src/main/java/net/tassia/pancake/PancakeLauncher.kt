package net.tassia.pancake

/**
 * This object is used to launch a new [Pancake] instance.
 *
 * @since Pancake 1.0
 * @author Tassilo
 */
object PancakeLauncher {

	const val VERSION: String = "1.0.0-PRE-1"

	/**
	 * Launches a new [Pancake] instance.
	 *
	 * @param args command-line arguments
	 * @return the instance
	 */
	fun new(args: Array<String>): Pancake {
		// TODO
		return Pancake()
	}

}
