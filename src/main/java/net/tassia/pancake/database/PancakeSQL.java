package net.tassia.pancake.database;

import net.tassia.pancake.Pancake;
import net.tassia.pancake.orm.Account;
import net.tassia.pancake.orm.Email;
import net.tassia.pancake.orm.Group;
import net.tassia.pancake.orm.Inbox;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.UUID;

public abstract class PancakeSQL extends PancakeDB {
	protected volatile Connection connection;

	/* Constructor */
	public PancakeSQL(Pancake pancake, String name) {
		super(pancake, name);
	}
	/* Constructor */





	/* Store Email */
	@Override
	public boolean storeEmail(Email email) throws SQLException {
		// TODO
		return false;
	}
	/* Store Email */





	/* Fetch Groups */
	@Override
	public Collection<Group> fetchGroups() throws SQLException {
		Collection<Group> groups = new ArrayList<>();
		ResultSet result = connection.prepareCall("SELECT * FROM pancake_groups;").executeQuery();

		while (result.next()) {
			Group group = new Group(UUID.fromString(result.getString(1)));
			group.setName(result.getString(2));
			group.setDescription(result.getString(3));
			groups.add(group);
		}

		return groups;
	}
	/* Fetch Groups */





	/* Fetch Accounts */
	@Override
	public Collection<Account> fetchAccounts() throws SQLException {
		Collection<Account> accounts = new ArrayList<>();
		ResultSet result = connection.prepareCall("SELECT * FROM pancake_accounts;").executeQuery();

		while (result.next()) {
			Account account = new Account(UUID.fromString(result.getString(1)));
			account.setName(result.getString(2));
			account.setPassword(result.getString(3));
			account.setGroup(pancake.getGroup(UUID.fromString(result.getString(4))));
			accounts.add(account);
		}

		return accounts;
	}
	/* Fetch Accounts */





	/* Fetch Emails */
	@Override
	public Collection<Email> fetchEmails(Inbox inbox, int pagination, int page) throws SQLException {
		// TODO
		return null;
	}
	/* Fetch Emails */





	/* Fetch Email */
	@Override
	public Email fetchEmail(UUID uuid) throws SQLException {
		// TODO
		return null;
	}
	/* Fetch Email */

}
