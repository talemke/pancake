package net.tassia.pancake.database;

import net.tassia.pancake.Pancake;
import net.tassia.pancake.orm.Account;
import net.tassia.pancake.orm.Email;
import net.tassia.pancake.orm.Group;
import net.tassia.pancake.orm.Inbox;

import java.nio.charset.StandardCharsets;
import java.sql.*;
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
		PreparedStatement stmt = connection.prepareStatement("INSERT INTO pancake_emails VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?);");

		stmt.setString(1, email.getUUID().toString());
		stmt.setLong(2, email.getTimestamp());
		stmt.setString(3, email.getSender());
		stmt.setString(4, email.getRecipient());
		stmt.setString(5, new String(email.getData(), StandardCharsets.UTF_8));
		stmt.setString(6, email.getHelo());
		stmt.setString(7, email.getRemoteAddress());
		stmt.setInt(8, email.getType());
		stmt.setString(9, email.getAccount().getUUID().toString());
		stmt.setString(10, email.getInbox() != null ? email.getInbox().getUUID().toString() : null);

		stmt.execute();
		return true;
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





	/* Store Account */
	@Override
	public boolean storeAccount(Account account) throws SQLException {
		PreparedStatement stmt = connection.prepareStatement("INSERT INTO pancake_accounts VALUES (?, ?, ?, ?);");

		stmt.setString(1, account.getUUID().toString());
		stmt.setString(2, account.getName());
		stmt.setString(3, account.getPassword());
		stmt.setString(4, account.getGroup().getUUID().toString());

		stmt.execute();
		return true;
	}
	/* Store Account */





	/* Fetch Emails */
	private Email toEmail(ResultSet result) throws SQLException {
		Email e = new Email(UUID.fromString(result.getString("email_id")));
		e.setTimestamp(result.getLong("timestamp"));
		e.setSender(result.getString("sender"));
		e.setRecipient(result.getString("recipient"));
		e.setData(result.getString("data").getBytes(StandardCharsets.UTF_8));
		e.setHelo(result.getString("helo"));
		e.setRemoteAddress(result.getString("remote_address"));
		e.setType(result.getInt("type"));

		if (result.getString("account_id") != null) {
			try {
				UUID account = UUID.fromString(result.getString("account_id"));
				e.setAccount(pancake.getAccount(account));
			} catch (IllegalArgumentException ignored) {
			}
		}

		// FIXME: Deadlock somewhere below
//		if (e.getAccount() != null) {
//			try {
//				UUID inbox = UUID.fromString(result.getString("inbox_id"));
//				e.setInbox(e.getAccount().getInbox(inbox));
//			} catch (IllegalArgumentException ignored) {
//			}
//		}

		return e;
	}

	protected Collection<Email> fetchEmails(PreparedStatement stmt) throws SQLException {
		ResultSet result = stmt.executeQuery();
		Collection<Email> emails = new ArrayList<>();
		while (result.next()) {
			emails.add(toEmail(result));
		}
		return emails;
	}

	@Override
	public Collection<Email> fetchEmails(Account account, Inbox inbox, int pagination, int page) throws SQLException {
		// TODO: Pagination
		PreparedStatement stmt;
		if (inbox != null) {
			stmt = connection.prepareStatement("SELECT * FROM pancake_emails WHERE account_id = ? AND inbox_id = ? AND type = ?;");
			stmt.setString(1, account.getUUID().toString());
			stmt.setString(2, inbox.getUUID().toString());
			stmt.setInt(3, Pancake.TYPE_DEFAULT);
		} else {
			stmt = connection.prepareStatement("SELECT * FROM pancake_emails WHERE account_id = ? AND inbox_id IS NULL AND type = ?;");
			stmt.setString(1, account.getUUID().toString());
			stmt.setInt(2, Pancake.TYPE_DEFAULT);
		}
		return fetchEmails(stmt);
	}

	@Override
	public Collection<Email> fetchDraftEmails(Account account, int pagination, int page) throws SQLException {
		// TODO: Pagination
		PreparedStatement stmt;
		stmt = connection.prepareStatement("SELECT * FROM pancake_emails WHERE account_id = ? AND type = ?;");
		stmt.setString(1, account.getUUID().toString());
		stmt.setInt(2, Pancake.TYPE_DRAFT);
		return fetchEmails(stmt);
	}

	@Override
	public Collection<Email> fetchSentEmails(Account account, int pagination, int page) throws SQLException {
		// TODO: Pagination
		PreparedStatement stmt;
		stmt = connection.prepareStatement("SELECT * FROM pancake_emails WHERE account_id = ? AND type = ?;");
		stmt.setString(1, account.getUUID().toString());
		stmt.setInt(2, Pancake.TYPE_SENT);
		return fetchEmails(stmt);
	}

	@Override
	public Collection<Email> fetchDeletedEmails(Account account, int pagination, int page) throws SQLException {
		// TODO: Pagination
		PreparedStatement stmt;
		stmt = connection.prepareStatement("SELECT * FROM pancake_emails WHERE account_id = ? AND type = ?;");
		stmt.setString(1, account.getUUID().toString());
		stmt.setInt(2, Pancake.TYPE_DELETED);
		return fetchEmails(stmt);
	}

	@Override
	public Collection<Email> fetchSpamEmails(Account account, int pagination, int page) throws SQLException {
		// TODO: Pagination
		PreparedStatement stmt;
		stmt = connection.prepareStatement("SELECT * FROM pancake_emails WHERE account_id = ? AND type = ?;");
		stmt.setString(1, account.getUUID().toString());
		stmt.setInt(2, Pancake.TYPE_SPAM);
		return fetchEmails(stmt);
	}
	/* Fetch Emails */





	/* Fetch Email */
	@Override
	public Email fetchEmail(UUID uuid) throws SQLException {
		PreparedStatement stmt = connection.prepareStatement("SELECT * FROM pancake_emails WHERE email_id = ?;");
		stmt.setString(1, uuid.toString());
		ResultSet result = stmt.executeQuery();
		if (!result.next()) return null;
		return toEmail(result);
	}
	/* Fetch Email */

}
