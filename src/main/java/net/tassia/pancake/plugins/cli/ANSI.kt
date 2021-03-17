
package net.tassia.pancake.plugins.cli

/**
 * Contains some nice ANSI color codes, allowing for nicer console outputs.
 *
 * @since Pancake 1.0
 * @author Tassilo
 */
object ANSI {

	private const val BLACK = "\u001b[30m"
	private const val RED = "\u001b[31m"
	private const val GREEN = "\u001b[32m"
	private const val YELLOW = "\u001b[33m"
	private const val BLUE = "\u001b[34m"
	private const val MAGENTA = "\u001b[35m"
	private const val CYAN = "\u001b[36m"
	private const val WHITE = "\u001b[37m"



	/**
	 * A primary color, used for `INFO` outputs.
	 */
	const val PRIMARY = ""

	/**
	 * A secondary color, used for `DEBUG` outputs.
	 */
	const val SECONDARY = "\u001B[38;2;195;195;195m"



	/**
	 * A green color, used to indicate successes.
	 */
	const val SUCCESS = GREEN

	/**
	 * A yellow color, used to indicate warnings.
	 */
	const val WARNING = YELLOW

	/**
	 * A red color, used to indicate failures.
	 */
	const val FAILURE = RED



	/**
	 * The `RESET` character.
	 */
	const val RESET = "\u001b[0m"

}
