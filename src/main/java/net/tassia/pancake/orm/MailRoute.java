package net.tassia.pancake.orm;

import java.util.UUID;

public class MailRoute {
	private final UUID uuid;
	private Account account;
	private String usernameString;
	private Type usernameType;
	private String hostnameString;
	private Type hostnameType;

	/* Constructor */
	public MailRoute() {
		this(UUID.randomUUID());
	}

	public MailRoute(UUID uuid) {
		this.uuid = uuid;
	}
	/* Constructor */





	/* Getters & Setters */
	public UUID getUUID() {
		return uuid;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public String getUsernameString() {
		return usernameString;
	}

	public void setUsernameString(String usernameString) {
		this.usernameString = usernameString;
	}

	public Type getUsernameType() {
		return usernameType;
	}

	public void setUsernameType(Type usernameType) {
		this.usernameType = usernameType;
	}

	public String getHostnameString() {
		return hostnameString;
	}

	public void setHostnameString(String hostnameString) {
		this.hostnameString = hostnameString;
	}

	public Type getHostnameType() {
		return hostnameType;
	}

	public void setHostnameType(Type hostnameType) {
		this.hostnameType = hostnameType;
	}
	/* Getters & Setters */





	/* Matches */
	protected boolean matches(Type type, String pattern, String test) {
		switch (type) {
			case EXACT: return test.equalsIgnoreCase(pattern);
			case REGEX: return test.matches(pattern);
			case ANY: return true;
			default: return false;
		}
	}

	public Account matches(String recipient) {
		String username = recipient.split("@", 2)[0];
		String hostname = recipient.substring(username.length() + 1);

		if (!matches(usernameType, usernameString, username)) return null;
		if (!matches(hostnameType, hostnameString, hostname)) return null;

		return account;
	}
	/* Matches */





	/* To String */
	@Override
	public String toString() {
		String user = usernameType != Type.ANY ? usernameString : "?";
		String host = hostnameType != Type.ANY ? hostnameString : "?";
		return user + "@" + host;
	}
	/* To String */





	/* Type Enum */
	public enum Type {
		EXACT, REGEX, ANY
	}
	/* Type Enum */

}
