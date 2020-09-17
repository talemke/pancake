package net.tassia.pancake.http;

import net.tassia.pancake.Pancake;
import net.tassia.pancake.orm.Account;
import net.tassia.pancake.orm.Email;
import net.tassia.pancake.orm.Group;
import net.tassia.pancake.orm.Inbox;

import java.sql.SQLException;
import java.util.Collection;
import java.util.UUID;

public class ResourceManager {
	private final Pancake pancake;

	/* Constructor */
	public ResourceManager(Pancake pancake) {
		this.pancake = pancake;
	}
	/* Constructor */





	/* Resources */
	public Account findAccount(HttpRequest request, String uuid) {
		UUID u;
		try {
			u = UUID.fromString(uuid);
		} catch (IllegalArgumentException ex) {
			request.setErrorPage(404);
			return null;
		}
		Account acc = pancake.getAccount(u);
		if (acc == null) {
			request.setErrorPage(404);
			return null;
		}
		return acc;
	}

	public Group findGroup(HttpRequest request, String uuid) {
		UUID u;
		try {
			u = UUID.fromString(uuid);
		} catch (IllegalArgumentException ex) {
			request.setErrorPage(404);
			return null;
		}
		Group g = pancake.getGroup(u);
		if (g == null) {
			request.setErrorPage(404);
			return null;
		}
		return g;
	}

	public Email findEmail(HttpRequest request, Account account, String uuid) {
		UUID u;
		try {
			u = UUID.fromString(uuid);
		} catch (IllegalArgumentException ex) {
			request.setErrorPage(404);
			return null;
		}
		try {
			Email email = pancake.getDatabase().fetchEmail(u);
			if (email == null || !email.getAccount().getUUID().equals(account.getUUID())) {
				request.setErrorPage(404);
				return null;
			}
			return email;
		} catch (SQLException ex) {
			ex.printStackTrace();
			request.setErrorPage(500);
			return null;
		}
	}

	public Collection<Email> findEmails(HttpRequest request, Account account, Inbox inbox, int pagination, int page) {
		try {
			return pancake.getDatabase().fetchEmails(account, inbox, pagination, page);
		} catch (SQLException ex) {
			ex.printStackTrace();
			request.setErrorPage(500);
			return null;
		}
	}

	public Collection<Email> findDraftEmails(HttpRequest request, Account account, int pagination, int page) {
		try {
			return pancake.getDatabase().fetchDraftEmails(account, pagination, page);
		} catch (SQLException ex) {
			ex.printStackTrace();
			request.setErrorPage(500);
			return null;
		}
	}

	public Collection<Email> findSentEmails(HttpRequest request, Account account, int pagination, int page) {
		try {
			return pancake.getDatabase().fetchSentEmails(account, pagination, page);
		} catch (SQLException ex) {
			ex.printStackTrace();
			request.setErrorPage(500);
			return null;
		}
	}

	public Collection<Email> findDeletedEmails(HttpRequest request, Account account, int pagination, int page) {
		try {
			return pancake.getDatabase().fetchDeletedEmails(account, pagination, page);
		} catch (SQLException ex) {
			ex.printStackTrace();
			request.setErrorPage(500);
			return null;
		}
	}

	public Collection<Email> findSpamEmails(HttpRequest request, Account account, int pagination, int page) {
		try {
			return pancake.getDatabase().fetchSpamEmails(account, pagination, page);
		} catch (SQLException ex) {
			ex.printStackTrace();
			request.setErrorPage(500);
			return null;
		}
	}
	/* Resources */

}
