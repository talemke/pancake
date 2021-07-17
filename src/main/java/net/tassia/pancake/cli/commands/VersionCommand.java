package net.tassia.pancake.cli.commands;

import net.tassia.pancake.Pancake;
import net.tassia.pancake.cli.CLI;
import net.tassia.pancake.cli.Command;

import java.util.Map;

public final class VersionCommand extends Command {

	public VersionCommand(CLI cli) {
		super(cli, "version");
	}

	@Override
	public void onCommand(String[] args, Map<String, String> flags) {
		cli.println("Running Pancake v" + Pancake.VERSION);
	}

}
