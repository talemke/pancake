package net.tassia.pancake.cli;

import net.tassia.pancake.Pancake;

import java.util.HashMap;
import java.util.Scanner;

class CLIReader implements Runnable {
	private final Pancake pancake;
	protected final HashMap<String, CLICommand> commands;
	protected final Scanner scanner;

	public CLIReader(Pancake pancake) {
		this.pancake = pancake;
		this.commands = new HashMap<>();
		this.scanner = new Scanner(System.in);
	}

	@Override
	public void run() {
		while (true) {
			try {
				String line = scanner.nextLine();

				String cmd;
				String[] args;
				if (line.contains(" ")) {
					cmd = line.split(" ", 2)[0];
					args = line.substring(cmd.length()).trim().split(" ");
				} else {
					cmd = line;
					args = new String[0];
				}

				CLICommand command = commands.get(cmd.toLowerCase());
				if (command != null) {
					try {
						if (!command.onCommand(args)) {
							pancake.getLogger().info("Correct usage: " + cmd.toLowerCase() + " " + command.syntax);
						}
					} catch (Exception ex) {
						ex.printStackTrace();
					}
				} else {
					pancake.getLogger().info("Unknown command: " + cmd);
				}
			} catch (IllegalStateException ex) {
				return;
			}
		}
	}

}
