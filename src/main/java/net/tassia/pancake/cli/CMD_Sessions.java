package net.tassia.pancake.cli;

import net.tassia.pancake.Pancake;
import net.tassia.pancake.orm.Account;

import java.util.Map;

public class CMD_Sessions extends CLICommand {

	CMD_Sessions(Pancake pancake) {
		super(pancake, "[<drop> <session>]");
	}

	@Override
	public boolean onCommand(String[] args) {

		// Show all sessions
		if (args.length == 0) {
			StringBuilder msgBuilder = new StringBuilder("Active sessions:");
			for (Map.Entry<String, Account> e : pancake.getHTTP().getSessions().entrySet()) {
				msgBuilder.append("\n- ").append(e.getValue().getName()).append(" (ID: ").append(e.getKey()).append(")");
			}
			print(msgBuilder.toString());
			return true;
		}

		// Drop session
		if (args.length == 2 && args[0].equalsIgnoreCase("drop")) {
			boolean result = pancake.getHTTP().dropSession(args[1]);
			pancake.getHTTP().dropRootSession(args[1]);
			if (result) {
				print("Session dropped.");
			} else {
				print("Session not found.");
			}
			return true;
		}

		return false;
	}

}
