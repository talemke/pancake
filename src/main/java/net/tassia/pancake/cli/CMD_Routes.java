package net.tassia.pancake.cli;

import net.tassia.pancake.Pancake;
import net.tassia.pancake.orm.Account;
import net.tassia.pancake.orm.MailRoute;

import java.util.UUID;

public class CMD_Routes extends CLICommand {

	CMD_Routes(Pancake pancake) {
		super(pancake, "[<create/drop/info/hostname/username> <route id/account> <type> <value>]");
	}

	private String format(MailRoute.Type type, String value) {
		switch (type) {
			case EXACT:
				return "Must equal " + value;
			case REGEX:
				return "Must match " + value;
			case ANY:
				return "Any";
			default:
				return "Unknown";
		}
	}

	@Override
	public boolean onCommand(String[] args) throws Exception {

		// Show all routes
		if (args.length == 0) {
			StringBuilder msgBuilder = new StringBuilder("Active routes:");
			for (MailRoute route : pancake.getRoutes()) {
				msgBuilder.append("\n- ").append(route.toString()).append(" -> ").append(route.getAccount().getName())
					.append(" | ").append(route.getUUID().toString());
			}
			print(msgBuilder.toString());
			return true;
		}


		// Create
		if (args.length == 2 && args[0].equalsIgnoreCase("create")) {
			Account acc = pancake.getAccountByString(args[1]);
			if (acc == null) {
				print("This account was not found.");
			} else {
				MailRoute route = new MailRoute();
				route.setAccount(acc);
				route.setUsernameType(MailRoute.Type.ANY);
				route.setUsernameString("");
				route.setHostnameType(MailRoute.Type.ANY);
				route.setHostnameString("");
				pancake.getDatabase().storeRoute(route);
				pancake.getRoutes().add(route);
				print("Successfully created route " + route.getUUID() + ".");
			}
			return true;
		}


		// Find route
		if (args.length < 2) return false;
		UUID uuid;
		try {
			uuid = UUID.fromString(args[1]);
		} catch (IllegalArgumentException ex) {
			return false;
		}
		MailRoute route = pancake.getRoute(uuid);
		if (route == null) {
			print("This route was not found.");
			return true;
		}


		// Drop
		if (args.length == 2 && args[0].equalsIgnoreCase("drop")) {
			pancake.getDatabase().dropRoute(route.getUUID());
			pancake.getRoutes().remove(route);
			print("Successfully removed route " + route.getUUID() + ".");
			return true;
		}


		// Info
		if (args.length == 2 && args[0].equalsIgnoreCase("info")) {
			String msg = "Information about route " + route.getUUID() + ":\n";
			msg += "Route to: " + route.getAccount().getName() + "\n";
			msg += "Username: " + format(route.getUsernameType(), route.getUsernameString()) + "\n";
			msg += "Hostname: " + format(route.getHostnameType(), route.getHostnameString());
			print(msg);
			return true;
		}


		// Edit Username
		if (args.length == 3 && args[2].equalsIgnoreCase("any") && args[0].equalsIgnoreCase("username")) {
			route.setUsernameType(MailRoute.Type.ANY);
			route.setUsernameString("");
			print("Updated route " + route.getUUID() + ".");
			return true;
		} else if (args.length == 4 && args[0].equalsIgnoreCase("username")) {
			MailRoute.Type type;
			try {
				type = MailRoute.Type.valueOf(args[2].toUpperCase());
			} catch (IllegalArgumentException ex) {
				print("Illegal type: "+ args[2]);
				return true;
			}
			route.setUsernameType(type);
			route.setUsernameString(args[3]);
			print("Updated route " + route.getUUID() + ".");
			return true;
		}


		// Edit Hostname
		if (args.length == 3 && args[2].equalsIgnoreCase("any") && args[0].equalsIgnoreCase("hostname")) {
			route.setHostnameType(MailRoute.Type.ANY);
			route.setHostnameString("");
			print("Updated route " + route.getUUID() + ".");
			return true;
		} else if (args.length == 4 && args[0].equalsIgnoreCase("hostname")) {
			MailRoute.Type type;
			try {
				type = MailRoute.Type.valueOf(args[2].toUpperCase());
			} catch (IllegalArgumentException ex) {
				print("Illegal type: "+ args[2]);
				return true;
			}
			route.setHostnameType(type);
			route.setHostnameString(args[3]);
			print("Updated route " + route.getUUID() + ".");
			return true;
		}

		return false;
	}

}
