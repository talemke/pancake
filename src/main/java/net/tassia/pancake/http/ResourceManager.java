package net.tassia.pancake.http;

import net.tassia.pancake.Pancake;
import net.tassia.pancake.orm.*;

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

	public MailRoute findEmailRoute(HttpRequest request, String uuid) {
		UUID u;
		try {
			u = UUID.fromString(uuid);
		} catch (IllegalArgumentException ex) {
			request.setErrorPage(404);
			return null;
		}
		MailRoute route = pancake.getRoute(u);
		if (route == null) {
			request.setErrorPage(404);
			return null;
		}
		return route;
	}

	public Mail findEmail(HttpRequest request, Account account, String uuid) {
		UUID u;
		try {
			u = UUID.fromString(uuid);
		} catch (IllegalArgumentException ex) {
			request.setErrorPage(404);
			return null;
		}
		try {
			Mail mail = pancake.getDatabase().fetchEmail(u);
			if (mail == null || !mail.getAccount().getUUID().equals(account.getUUID())) {
				request.setErrorPage(404);
				return null;
			}
			return mail;
		} catch (SQLException ex) {
			ex.printStackTrace();
			request.setErrorPage(500);
			return null;
		}
	}

	public Collection<Mail> findEmails(HttpRequest request, Account account, Inbox inbox, int pagination, int page) {
		try {
			return pancake.getDatabase().fetchEmails(account, inbox, pagination, page);
		} catch (SQLException ex) {
			ex.printStackTrace();
			request.setErrorPage(500);
			return null;
		}
	}

	public Collection<Mail> findDraftEmails(HttpRequest request, Account account, int pagination, int page) {
		try {
			return pancake.getDatabase().fetchDraftEmails(account, pagination, page);
		} catch (SQLException ex) {
			ex.printStackTrace();
			request.setErrorPage(500);
			return null;
		}
	}

	public Collection<Mail> findSentEmails(HttpRequest request, Account account, int pagination, int page) {
		try {
			return pancake.getDatabase().fetchSentEmails(account, pagination, page);
		} catch (SQLException ex) {
			ex.printStackTrace();
			request.setErrorPage(500);
			return null;
		}
	}

	public Collection<Mail> findDeletedEmails(HttpRequest request, Account account, int pagination, int page) {
		try {
			return pancake.getDatabase().fetchDeletedEmails(account, pagination, page);
		} catch (SQLException ex) {
			ex.printStackTrace();
			request.setErrorPage(500);
			return null;
		}
	}

	public Collection<Mail> findSpamEmails(HttpRequest request, Account account, int pagination, int page) {
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
