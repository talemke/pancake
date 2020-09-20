package net.tassia.pancake.cli;

import net.tassia.pancake.Pancake;

public abstract class CLICommand {
	protected final Pancake pancake;
	protected final String syntax;

	public CLICommand(Pancake pancake, String syntax) {
		this.pancake = pancake;
		this.syntax = syntax;
	}

	public void print(String msg) {
		pancake.getLogger().info(msg);
	}

	public void fine(String msg) {
		pancake.getLogger().fine(msg);
	}

	public abstract boolean onCommand(String[] args) throws Exception;

}
