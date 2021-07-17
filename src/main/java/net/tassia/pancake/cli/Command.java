package net.tassia.pancake.cli;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

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
	 * The aliases of this command.
	 */
	public final Set<String> aliases;

	/**
	 * The underlying base CLI.
	 */
	protected final CLI cli;



	/**
	 * Constructs a new {@link Command} with the given base CLI.
	 *
	 * @param cli the underlying base CLI
	 * @param name the command name
	 * @param aliases the command aliases
	 */
	protected Command(CLI cli, String name, String...aliases) {
		this.cli = cli;
		this.name = name;
		this.aliases = new HashSet<>();
		this.aliases.addAll(Arrays.asList(aliases));
	}



	/**
	 * Invoked when the command is executed.
	 *
	 * @param args the arguments
	 * @param flags the flags
	 */
	public abstract void onCommand(String[] args, Map<String, String> flags);

}
