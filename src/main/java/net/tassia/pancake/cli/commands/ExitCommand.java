package net.tassia.pancake.cli.commands;

import net.tassia.pancake.cli.CLI;
import net.tassia.pancake.cli.Command;

import java.util.Map;

public final class ExitCommand extends Command {

	public ExitCommand(CLI cli) {
		super(cli, "exit", "end", "quit");
	}

	@Override
	public void onCommand(String[] args, Map<String, String> flags) {
		cli.stop();
	}

}
