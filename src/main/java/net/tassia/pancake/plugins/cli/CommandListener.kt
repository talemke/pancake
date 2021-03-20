package net.tassia.pancake.plugins.cli

import net.tassia.pancake.Pancake
import net.tassia.pancake.util.Readers.readUntilOrEOF
import java.io.StringReader
import java.util.*

class CommandListener(private val pancake: Pancake, private val getter: (String) -> Command?) : Runnable {

	private val scanner: Scanner = Scanner(System.`in`)
	var listening: Boolean = true

	override fun run() {
		while (listening) {
			try {
				// Read next line
				val cmdString = awaitCommand()
				val reader = StringReader(cmdString)

				// Read command name
				val cmdName = reader.readUntilOrEOF({ it.isWhitespace() })
				val cmd = getter(cmdName.toString())

				// Command exists?
				if (cmd == null) {
					CLI.failure("Unknown command. Type 'help' for help.")
					continue
				}

				// Read arguments/flags
				val args = mutableListOf<String>()
				val toggles = mutableMapOf<String, Boolean>()
				val flags = mutableMapOf<String, String>()

				while (true) {
					val nextInt = reader.read()
					if (nextInt == -1) break
					val next = nextInt.toChar()
					if (next.isWhitespace()) continue

					val builder = StringBuilder()
					val nextArg: String = if (next == '"') {
						reader.readUntilOrEOF({ it == '"' }, builder).toString()
					} else {
						builder.append(next)
						reader.readUntilOrEOF({ it.isWhitespace() }, builder).toString()
					}

					if (nextArg.startsWith("--")) {
						val flagFull = nextArg.substring(2)
						if (flagFull.contains('=')) {
							val flagName = flagFull.split('=')[0]
							when (val flagValue = flagFull.substring(flagName.length + 1)) {
								"true" -> toggles[flagName.toLowerCase()] = true
								"false" -> toggles[flagName.toLowerCase()] = false
								else -> flags[flagName.toLowerCase()] = flagValue.toLowerCase()
							}
						} else {
							toggles[flagFull.toLowerCase()] = true
						}
					} else {
						args.add(nextArg)
					}
				}

				cmd.onCommand(pancake, args, toggles, flags)

			} catch (_: InterruptedException) {
				listening = false
				break

			} catch (ex: Throwable) {
				CLI.failure("An unexpected error has occurred while executing this command.")
				CLI.failure(ex::class.simpleName + ": " + ex.message)

			}
		}
	}

	private fun awaitCommand(): String {
		// Print new line (pretty)
		CLI.print()

		// Print shell information
		CLI.print("Pancake> ", false)

		// Read the next command
		val command = scanner.nextLine()

		// Print new line (pretty)
		CLI.print()

		// Return the read command
		return command
	}

}
