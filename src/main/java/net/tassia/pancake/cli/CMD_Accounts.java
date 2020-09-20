package net.tassia.pancake.cli;

import net.tassia.pancake.Pancake;
import net.tassia.pancake.orm.Account;
import net.tassia.pancake.orm.Group;

import java.sql.SQLException;
import java.util.Map;
import java.util.UUID;

public class CMD_Accounts extends CLICommand {

	CMD_Accounts(Pancake pancake) {
		super(pancake);
	}

	@Override
	public boolean onCommand(String[] args) throws Exception {

		// Show all accounts
		if (args.length == 0) {
			StringBuilder msgBuilder = new StringBuilder("Registered accounts:");
			for (Account acc : pancake.getAccounts()) {
				msgBuilder.append("\n- ").append(acc.getName()).append(" (UUID: ").append(acc.getUUID()).append(")");
			}
			print(msgBuilder.toString());
			return true;
		}



		// Find account
		if (args.length < 2) return false;
		Account acc = pancake.getAccountByString(args[1]);
		if (acc == null) {
			print("This account was not found.");
			return true;
		}



		// Suspend
		if (args.length == 2 && args[0].equalsIgnoreCase("suspend")) {
			if (acc.hasFlag(Pancake.FLAG_ACCOUNT_SUSPENDED)) {
				print("This account is already suspended.");
			} else {
				print("Suspending account...");

				fine("- Updating account...");
				acc.addFlag(Pancake.FLAG_ACCOUNT_SUSPENDED);
				pancake.getDatabase().updateAccount(acc);

				fine("- Dropping sessions...");
				pancake.getHTTP().dropUserSessions(acc);

				print(acc.getName() + " has been successfully suspended.");
			}
			return true;
		}

		// Unsuspend
		if (args.length == 2 && args[0].equalsIgnoreCase("unsuspend")) {
			if (!acc.hasFlag(Pancake.FLAG_ACCOUNT_SUSPENDED)) {
				print("This account is not suspended.");
			} else {
				acc.removeFlag(Pancake.FLAG_ACCOUNT_SUSPENDED);
				pancake.getDatabase().updateAccount(acc);
				print("Suspension for " + acc.getName() + " has been successfully removed.");
			}
			return true;
		}

		// Rename
		if (args.length == 3 && args[0].equalsIgnoreCase("rename")) {
			if (!args[2].matches(Pancake.USERNAME_REGEX)) {
				print("Usernames must be alphanumeric.");
			} else {
				String old = acc.getName();
				acc.setName(args[2]);
				pancake.getDatabase().updateAccount(acc);
				print("Renamed " + old + "s account to '" + acc.getName() + "'.");
			}
			return true;
		}

		// Get Group
		if (args.length == 2 && args[0].equalsIgnoreCase("group")) {
			print(acc.getName() + " is a member of group '" + acc.getGroup().getName() + "'.");
			return true;
		}

		// Set Group
		if (args.length == 3 && args[0].equalsIgnoreCase("group")) {
			Group g = pancake.getGroupByString(args[2]);
			if (g != null) {
				acc.setGroup(g);
				pancake.getDatabase().updateAccount(acc);
				print("Set " + acc.getName() + "s group to '" + g.getName() + "'.");
			} else {
				print("This group was not found.");
			}
			return true;
		}

		// Logout
		if (args.length == 2 && args[0].equalsIgnoreCase("logout")) {
			int count = pancake.getHTTP().dropUserSessions(acc);
			if (count == 0) {
				print(acc.getName() + " has no active session.");
			} else {
				print("Dropped all " + count + " sessions from " + acc.getName() + ".");
			}
			return true;
		}

		return false;
	}

}
