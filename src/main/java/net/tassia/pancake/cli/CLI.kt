package net.tassia.pancake.cli

object CLI {

	fun print(message: String = "") {
		println(message + ANSI.RESET)
	}

	fun success(message: String = "") {
		print(ANSI.SUCCESS + Icon.CHECK + " " + message)
	}

	fun failure(message: String = "") {
		print(ANSI.FAILURE + Icon.CROSS + " " + message)
	}

}
