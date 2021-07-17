package net.tassia.pancake.cli;

import java.util.Map;

/**
 * A basic command.
 *
 * @since Pancake 1.0
 * @author Tassilo
 */
public abstract class Command {

	/**
	 * The name of this command.
	 */
	public final String name;

	/**
	 * The underlying base CLI.
	 */
	protected final CLI cli;



	/**
	 * Constructs a new {@link Command} with the given base CLI.
	 *
	 * @param name the command name
	 * @param cli the underlying base CLI
	 */
	protected Command(String name, CLI cli) {
		this.name = name;
		this.cli = cli;
	}



	/**
	 * Invoked when the command is executed.
	 *
	 * @param args the arguments
	 * @param flags the flags
	 */
	public abstract void onCommand(String[] args, Map<String, String> flags);

}
