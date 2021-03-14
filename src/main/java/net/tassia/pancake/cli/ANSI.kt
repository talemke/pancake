
package net.tassia.pancake.cli

object ANSI {

	private const val BLACK = "\u001b[30m"
	private const val RED = "\u001b[31m"
	private const val GREEN = "\u001b[32m"
	private const val YELLOW = "\u001b[33m"
	private const val BLUE = "\u001b[34m"
	private const val MAGENTA = "\u001b[35m"
	private const val CYAN = "\u001b[36m"
	private const val WHITE = "\u001b[37m"

	const val PRIMARY = ""
	const val SECONDARY = "\u001B[38;2;195;195;195m"

	const val SUCCESS = GREEN
	const val WARNING = YELLOW
	const val FAILURE = RED

	const val RESET = "\u001b[0m"

}
