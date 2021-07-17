package net.tassia.pancake.cli;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.*;

/**
 * A simple command-line interface.
 *
 * @since Pancake 1.0
 * @author Tassilo
 */
public final class CLI implements Runnable {

	private final Collection<Command> commands = new ArrayList<>();
	private final Map<String, Command> register = new HashMap<>();
	private volatile boolean running = false;
	private final Scanner input;
	private final PrintStream output;

	/**
	 * Creates a new CLI with the given input and output.
	 *
	 * @param input the input stream
	 * @param output the output stream
	 */
	public CLI(InputStream input, OutputStream output) {
		this.input = new Scanner(input);
		this.output = new PrintStream(output);
	}



	@Override
	public synchronized void run() {
		// Start running & check state
		if (this.running) {
			throw new IllegalStateException("The CLI is already running.");
		}
		this.running = true;

		// Variables
		String cmd;
		String[] args;
		Command command;
		Map<String, String> flags = new HashMap<>();

		// Main loop
		while (this.running) {

			// Read next line
			String line;
			try {
				line = input.nextLine();
			} catch (IllegalStateException ex) {
				// Scanner has probably been closed
				break;
			}

			// Extract arguments and flags
			if (line.contains(" ")) {
				int index = line.indexOf(' ');
				cmd = line.substring(0, index);
				args = line.substring(index + 1).split(" ");
			} else {
				cmd = line;
				args = new String[0];
			}

			// Find command
			command = register.get(cmd.toLowerCase());
			if (command == null) {
				println("Unknown command. Type 'help' for a list of available commands.");
				continue;
			}

			// Execute command
			try {
				command.onCommand(args, flags);
			} catch (Throwable ex) {
				println("An unexpected error occurred while processing this command.");
				ex.printStackTrace(output);
			}

		}

		// Quit listening
		stop();
	}



	/**
	 * Prints a line to the output.
	 *
	 * @param line the line to print
	 */
	public void println(String line) {
		output.println(line);
	}

	/**
	 * Prints a string to the output.
	 *
	 * @param text the message to print
	 */
	public void print(String text) {
		output.print(text);
	}



	/**
	 * Stops the CLI from listening for further commands.
	 *
	 * <b>Note:</b> This does not immediately force the {@link #run()} method to return,
	 * but instead will prevent it from listening for further commands after the next command.
	 */
	public void stop() {
		this.running = false;
	}



	/**
	 * Registers a new command.
	 *
	 * @param cmd the command
	 */
	public void addCommand(Command cmd) {
		commands.add(cmd);
		rebuildRegister();
	}

	/**
	 * Removes a command.
	 *
	 * @param cmd the command
	 */
	public void removeCommand(Command cmd) {
		commands.remove(cmd);
		rebuildRegister();
	}

	/**
	 * Removes a command by it's name (non case-sensitive).
	 *
	 * @param name the name
	 */
	public void removeCommand(String name) {
		commands.removeIf((cmd) -> cmd.name.equalsIgnoreCase(name));
		rebuildRegister();
	}

	/**
	 * Rebuilds the internal command register.
	 */
	private void rebuildRegister() {
		register.clear();
		for (Command cmd : commands) {
			register.put(cmd.name.toLowerCase(), cmd);
		}
		for (Command cmd : commands) {
			for (String alias : cmd.aliases) {
				register.putIfAbsent(alias.toLowerCase(), cmd);
			}
		}
	}

}
