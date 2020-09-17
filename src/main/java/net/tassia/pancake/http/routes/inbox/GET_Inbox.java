package net.tassia.pancake.http.routes.inbox;

import net.tassia.pancake.Pancake;
import net.tassia.pancake.http.*;
import net.tassia.pancake.http.routes.admin.AdminRoutes;
import net.tassia.pancake.orm.Account;
import net.tassia.pancake.orm.Email;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

class GET_Inbox implements HttpRoute {
	private final InboxRoutes routes;
	private final String noneView;

	public GET_Inbox(InboxRoutes routes) {
		this.routes = routes;
		this.noneView = new HttpView("/views/email/none.html").view();
	}

	@Override
	public byte[] route(Pancake pancake, HttpRequest request, String[] matches) {
		GenericPancakeView view = new GenericPancakeView(pancake, request);
		if (view.checkAccess()) return null;

		Collection<Email> emails = pancake.getHTTP().getResources().findEmails(request, request.getAuth(), null, 0, 0);
		if (emails == null) return null;

		routes.addSideNav(view, InboxRoutes.INBOX_DEFAULT);
		routes.addMailNav(view, emails, null);

		view.setContent(noneView);

		return view.view("Inbox");
	}

}
