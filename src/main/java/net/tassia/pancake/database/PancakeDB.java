package net.tassia.pancake.database;

import net.tassia.pancake.Pancake;
import net.tassia.pancake.orm.Account;
import net.tassia.pancake.orm.Email;
import net.tassia.pancake.orm.Group;
import net.tassia.pancake.orm.Inbox;

import java.sql.SQLException;
import java.util.Collection;
import java.util.UUID;

public abstract class PancakeDB {
	private final String name;
	protected final Pancake pancake;

	/* Constructor */
	public PancakeDB(Pancake pancake, String name) {
		this.pancake = pancake;
		this.name = name;
	}
	/* Constructor */



	/* Generic */
	public String getName() {
		return name;
	}
	/* Generic */



	/* Abstract Methods */
	public abstract void connect() throws SQLException, ClassNotFoundException;

	public abstract boolean storeEmail(Email email) throws SQLException;

	public abstract Collection<Group> fetchGroups() throws SQLException;

	public abstract Collection<Account> fetchAccounts() throws SQLException;

	public abstract Collection<Email> fetchEmails(Inbox inbox, int pagination, int page) throws SQLException;

	public abstract Email fetchEmail(UUID uuid) throws SQLException;
	/* Abstract Methods */

}
