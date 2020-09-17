package net.tassia.pancake.database;

import net.tassia.pancake.Pancake;
import net.tassia.pancake.orm.*;

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

	public abstract Collection<EmailRoute> fetchRoutes() throws SQLException;

	public abstract boolean storeAccount(Account account) throws SQLException;

	public abstract Collection<Email> fetchEmails(Account account, Inbox inbox, int pagination, int page) throws SQLException;
	public abstract Collection<Email> fetchDraftEmails(Account account, int pagination, int page) throws SQLException;
	public abstract Collection<Email> fetchSentEmails(Account account, int pagination, int page) throws SQLException;
	public abstract Collection<Email> fetchDeletedEmails(Account account, int pagination, int page) throws SQLException;
	public abstract Collection<Email> fetchSpamEmails(Account account, int pagination, int page) throws SQLException;

	public abstract Email fetchEmail(UUID uuid) throws SQLException;
	/* Abstract Methods */

}
