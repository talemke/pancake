package net.tassia.pancake.http.routes.inbox;

import net.tassia.pancake.Pancake;
import net.tassia.pancake.http.HttpRequest;
import net.tassia.pancake.http.HttpRoute;
import net.tassia.pancake.http.HttpView;
import net.tassia.pancake.http.HttpViewRoute;
import net.tassia.pancake.orm.Email;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

class InboxRoute implements HttpRoute {
	private final InboxRoutes core;

	public InboxRoute(InboxRoutes core) {
		this.core = core;
	}

	@Override
	public byte[] route(Pancake pancake, HttpRequest request, String[] matches) {
		// Check auth
		if (!request.checkAuth()) {
			request.redirect("/auth/login");
			return null;
		}


		// Fetch emails
		Collection<Email> emails = null;
		try {
			emails = pancake.getDatabase().fetchEmails(request.getAuth(), null, 0, 0);
		} catch (SQLException ex) {
			ex.printStackTrace();
			request.setErrorPage(500);
			return null;
		}


		// Echo view
		return core.generateView(pancake, request, emails, null);
	}

}
