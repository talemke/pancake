package net.tassia.pancake.cli;

import net.tassia.pancake.Pancake;

public interface CLICommand {

	void onCommand(Pancake pancake, String[] args);

}
