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
				String token = pancake.getSecurity().md5(e.getKey(), "");
				msgBuilder.append("\n- ").append(e.getValue().getName()).append(" (ID: ").append(token).append(")");
			}
			pancake.getLogger().info(msgBuilder.toString());
			return;
		}

		// Show root sessions
		// TODO

		// Show user sessions
		// TODO

		// Drop session
		// TODO

		pancake.getLogger().info("Invalid usage.");
	}

}
