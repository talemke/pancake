package net.tassia.pancake.plugins.cli.listener

import net.tassia.event.EventListener
import net.tassia.pancake.plugins.cli.command.HelpCommand
import net.tassia.pancake.plugins.cli.command.QuitCommand
import net.tassia.pancake.plugins.cli.event.CliRegisterCommandsEvent

/**
 * Responsible for registering core CLI commands.
 *
 * @since Pancake 1.0
 * @author Tassilo
 */
object CoreCliRegisterCommandsListener : EventListener<CliRegisterCommandsEvent> {

	override fun onEvent(event: CliRegisterCommandsEvent) {
		event.cli.also {
			it.add(HelpCommand.Info, HelpCommand.Executor)
			it.add(QuitCommand.Info, QuitCommand.Executor)
		}
	}

}
