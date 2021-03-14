package net.tassia.pancake.cli

import java.util.*

object CLI {

	private val output = System.out
	private val scanner = Scanner(System.`in`)



	fun print(message: String = "", newLine: Boolean = true) {
		if (newLine) {
			output.println(message + ANSI.RESET)
		} else {
			output.print(message + ANSI.RESET)
		}
	}

	fun success(message: String = "") = print(ANSI.SUCCESS + Icon.CHECK + " " + message)

	fun failure(message: String = "") = print(ANSI.FAILURE + Icon.CROSS + " " + message)



	fun readLine(): String = scanner.nextLine()

}
