package net.tassia.pancake.cli

import net.tassia.pancake.Pancake

class PancakeCLI(pancake: Pancake) {

	private val commands: MutableMap<CommandInfo, Command> = mutableMapOf()
	private val listener: CommandListener = CommandListener(pancake) { search ->
		commands.entries.find {
			it.key.name.equals(search, true)
		}?.value
	}
	private val thread: Thread = Thread(listener, "PancakeCLI").also { it.start() }



	fun stop() {
		listener.listening = false
		if (thread.isAlive) {
			thread.interrupt()
		}
	}



	fun commands(): Set<CommandInfo> = commands.keys

	fun add(info: CommandInfo, command: Command) = commands.set(info, command)

	fun remove(info: CommandInfo) = commands.remove(info)

}
