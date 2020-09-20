package net.tassia.pancake.cli;

import net.tassia.pancake.Pancake;
import net.tassia.pancake.orm.Account;

import java.util.Map;

public class CMD_Sessions implements CLICommand {

	@Override
	public void onCommand(Pancake pancake, String[] args) {

		// Show all sessions
		if (args.length == 0) {
			StringBuilder msgBuilder = new StringBuilder("Active sessions:");
			for (Map.Entry<String, Account> e : pancake.getHTTP().getSessions().entrySet()) {
				msgBuilder.append("\n- ").append(e.getValue().getName()).append(" (ID: ").append(e.getKey()).append(")");
			}
			pancake.getLogger().info(msgBuilder.toString());
			return;
		}

		// Show root sessions
		// TODO

		// Show user sessions
		// TODO

		// Drop session
		if (args.length == 2 && args[0].equalsIgnoreCase("drop")) {
			boolean result = pancake.getHTTP().dropSession(args[1]);
			pancake.getHTTP().dropRootSession(args[1]);
			if (result) {
				pancake.getLogger().info("Session dropped.");
			} else {
				pancake.getLogger().info("Session not found.");
			}
			return;
		}

		pancake.getLogger().info("Invalid usage.");
	}

}
