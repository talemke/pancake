package net.tassia.pancake.http.routes.inbox;

import net.tassia.pancake.Pancake;
import net.tassia.pancake.http.HttpRequest;
import net.tassia.pancake.http.HttpRoute;
import net.tassia.pancake.orm.Email;

import java.sql.SQLException;
import java.util.UUID;

class MailPrintRoute implements HttpRoute {
	private final InboxRoutes core;

	public MailPrintRoute(InboxRoutes core) {
		this.core = core;
	}

	@Override
	public byte[] route(Pancake pancake, HttpRequest request, String[] matches) {
		// Check auth
		if (!request.checkAuth()) {
			request.redirect("/auth/login");
			return null;
		}


		// Parse UUID
		UUID uuid;
		try {
			uuid = UUID.fromString(matches[0]);
		} catch (IllegalArgumentException ex) {
			request.setErrorPage(404);
			return null;
		}


		// Fetch email
		Email email;
		try {
			email = pancake.getDatabase().fetchEmail(uuid);
		} catch (SQLException ex) {
			ex.printStackTrace();
			request.setErrorPage(500);
			return null;
		}
		if (email == null) {
			request.setErrorPage(404);
			return null;
		}


		// Echo email
		request.setContentType("text/plain");
		return email.getData();
	}

}
