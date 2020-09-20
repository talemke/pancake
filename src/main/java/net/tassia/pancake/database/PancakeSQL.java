package net.tassia.pancake.database;

import net.tassia.pancake.Pancake;
import net.tassia.pancake.orm.*;
import net.tassia.pancake.parser.ParsedMail;

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
	public boolean storeEmail(Mail mail) throws SQLException {
		PreparedStatement stmt = connection.prepareStatement("INSERT INTO pancake_emails VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?);");

		stmt.setString(1, mail.getUUID().toString());
		stmt.setLong(2, mail.getTimestamp());
		stmt.setString(3, mail.getSender());
		stmt.setString(4, mail.getRecipient());
		stmt.setString(5, new String(mail.getData(), StandardCharsets.UTF_8));
		stmt.setString(6, mail.getHelo());
		stmt.setString(7, mail.getRemoteAddress());
		stmt.setInt(8, mail.getType());
		stmt.setString(9, mail.getAccount().getUUID().toString());
		stmt.setString(10, mail.getInbox() != null ? mail.getInbox().getUUID().toString() : null);

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
			group.setFlags(result.getLong(4));
			groups.add(group);
		}

		return groups;
	}
	/* Fetch Groups */





	/* Accounts */
	@Override
	public Collection<Account> fetchAccounts() throws SQLException {
		Collection<Account> accounts = new ArrayList<>();
		ResultSet result = connection.prepareCall("SELECT * FROM pancake_accounts;").executeQuery();

		while (result.next()) {
			Account account = new Account(UUID.fromString(result.getString(1)));
			account.setName(result.getString(2));
			account.setPassword(result.getString(3));
			account.setGroup(pancake.getGroup(UUID.fromString(result.getString(4))));
			account.setFlags(result.getLong(5));
			accounts.add(account);
		}

		return accounts;
	}

	@Override
	public boolean storeAccount(Account account) throws SQLException {
		PreparedStatement stmt = connection.prepareStatement("INSERT INTO pancake_accounts VALUES (?, ?, ?, ?, ?);");

		stmt.setString(1, account.getUUID().toString());
		stmt.setString(2, account.getName());
		stmt.setString(3, account.getPassword());
		stmt.setString(4, account.getGroup().getUUID().toString());
		stmt.setLong(5, account.getFlags());

		stmt.execute();
		return true;
	}

	@Override
	public void updateAccount(Account account) throws SQLException {
		connection.setAutoCommit(false);
		dropAccount(account.getUUID());
		storeAccount(account);
		connection.commit();
		connection.setAutoCommit(true);
	}

	@Override
	public void dropAccount(UUID uuid) throws SQLException {
		PreparedStatement stmt = connection.prepareStatement("DELETE FROM pancake_accounts WHERE account_id = ?;");
		stmt.setString(1, uuid.toString());
		stmt.executeUpdate();
	}
	/* Accounts */





	/* Routes */
	@Override
	public Collection<MailRoute> fetchRoutes() throws SQLException {
		Collection<MailRoute> routes = new ArrayList<>();
		ResultSet result = connection.prepareCall("SELECT * FROM pancake_routes;").executeQuery();

		while (result.next()) {
			MailRoute route = new MailRoute(UUID.fromString(result.getString(1)));
			route.setAccount(pancake.getAccount(UUID.fromString(result.getString(2))));
			route.setUsernameString(result.getString(3));
			route.setUsernameType(MailRoute.Type.valueOf(result.getString(4)));
			route.setHostnameString(result.getString(5));
			route.setHostnameType(MailRoute.Type.valueOf(result.getString(6)));

			if (route.getAccount() == null) {
				pancake.getLogger().warning("Account for route " + route.getUUID() + " not found, dropping route...");
				pancake.getDatabase().dropRoute(route.getUUID());
				continue;
			}

			routes.add(route);
		}

		return routes;
	}

	@Override
	public void dropRoute(UUID uuid) throws SQLException {
		PreparedStatement stmt = connection.prepareStatement("DELETE FROM pancake_routes WHERE route_id = ?;");
		stmt.setString(1, uuid.toString());
		stmt.executeUpdate();
	}
	/* Routes */





	/* Fetch Emails */
	private Mail toEmail(ResultSet result) throws SQLException {
		Mail e = new Mail(UUID.fromString(result.getString("email_id")));
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

		ParsedMail parsed = pancake.getParser().parse(e.getData());
		if (parsed != null) {
			e.setParsed(parsed);
		} else {
			pancake.getLogger().warning("Failed to parse mail " + e.getUUID().toString());
		}
		return e;
	}

	protected Collection<Mail> fetchEmails(PreparedStatement stmt) throws SQLException {
		ResultSet result = stmt.executeQuery();
		Collection<Mail> mail = new ArrayList<>();
		while (result.next()) {
			mail.add(toEmail(result));
		}
		return mail;
	}

	@Override
	public Collection<Mail> fetchEmails(Account account, Inbox inbox, int pagination, int page) throws SQLException {
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
	public Collection<Mail> fetchDraftEmails(Account account, int pagination, int page) throws SQLException {
		// TODO: Pagination
		PreparedStatement stmt;
		stmt = connection.prepareStatement("SELECT * FROM pancake_emails WHERE account_id = ? AND type = ?;");
		stmt.setString(1, account.getUUID().toString());
		stmt.setInt(2, Pancake.TYPE_DRAFT);
		return fetchEmails(stmt);
	}

	@Override
	public Collection<Mail> fetchSentEmails(Account account, int pagination, int page) throws SQLException {
		// TODO: Pagination
		PreparedStatement stmt;
		stmt = connection.prepareStatement("SELECT * FROM pancake_emails WHERE account_id = ? AND type = ?;");
		stmt.setString(1, account.getUUID().toString());
		stmt.setInt(2, Pancake.TYPE_SENT);
		return fetchEmails(stmt);
	}

	@Override
	public Collection<Mail> fetchDeletedEmails(Account account, int pagination, int page) throws SQLException {
		// TODO: Pagination
		PreparedStatement stmt;
		stmt = connection.prepareStatement("SELECT * FROM pancake_emails WHERE account_id = ? AND type = ?;");
		stmt.setString(1, account.getUUID().toString());
		stmt.setInt(2, Pancake.TYPE_DELETED);
		return fetchEmails(stmt);
	}

	@Override
	public Collection<Mail> fetchSpamEmails(Account account, int pagination, int page) throws SQLException {
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
	public Mail fetchEmail(UUID uuid) throws SQLException {
		PreparedStatement stmt = connection.prepareStatement("SELECT * FROM pancake_emails WHERE email_id = ?;");
		stmt.setString(1, uuid.toString());
		ResultSet result = stmt.executeQuery();
		if (!result.next()) return null;
		return toEmail(result);
	}
	/* Fetch Email */

}
