package net.tassia.pancake.http.routes.inbox;

import net.tassia.pancake.Pancake;
import net.tassia.pancake.http.*;
import net.tassia.pancake.http.routes.admin.AdminRoutes;
import net.tassia.pancake.orm.Account;
import net.tassia.pancake.orm.Email;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

class InboxRoute implements HttpRoute {
	private final InboxRoutes routes;

	public InboxRoute(InboxRoutes routes) {
		this.routes = routes;
	}

	@Override
	public byte[] route(Pancake pancake, HttpRequest request, String[] matches) {
		GenericPancakeView view = new GenericPancakeView(pancake, request);
		if (view.checkAccess()) return null;

		Collection<Email> emails = view.findEmails(request.getAuth(), null, 0, 0);
		if (emails == null) return null;

		routes.addSideNav(view, InboxRoutes.INBOX_DEFAULT);
		routes.addMailNav(view, emails, null);

		view.setContent(""); // TODO

		return view.view("Inbox");
	}

}
