package net.tassia.pancake.plugin.cli

import net.tassia.pancake.util.ANSI
import net.tassia.pancake.util.Icon
import java.util.*

/**
 * Adds utility to the CLI.
 *
 * @since Pancake 1.0
 * @author Tassilo
 */
object CLI {

	/**
	 * The CLI output stream.
	 */
	private val output = System.out

	/**
	 * The CLI input stream scanner.
	 */
	private val scanner = Scanner(System.`in`)



	/**
	 * Writes a line to the output.
	 *
	 * @param message the line to write
	 * @param newLine whether to insert a `LF` character ad the end
	 */
	fun print(message: String = "", newLine: Boolean = true) {
		if (newLine) {
			output.println(message + ANSI.RESET)
		} else {
			output.print(message + ANSI.RESET)
		}
	}

	/**
	 * Writes a success message to the output.
	 *
	 * @param message the message
	 */
	fun success(message: String = "") = print(ANSI.SUCCESS + Icon.CHECK + " " + message)

	/**
	 * Writes a warning message to the output.
	 *
	 * @param message the message
	 */
	fun warning(message: String = "") = print(ANSI.WARNING + message)

	/**
	 * Writes a failure message to the output.
	 *
	 * @param message the message
	 */
	fun failure(message: String = "") = print(ANSI.FAILURE + Icon.CROSS + " " + message)



	/**
	 * Reads the next line from the input.
	 */
	fun readLine(): String = scanner.nextLine()

}
