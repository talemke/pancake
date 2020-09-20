package net.tassia.pancake.http.routes.inbox;

import net.tassia.pancake.Pancake;
import net.tassia.pancake.http.GenericPancakeView;
import net.tassia.pancake.http.HttpRequest;
import net.tassia.pancake.http.HttpRoute;
import net.tassia.pancake.http.HttpView;
import net.tassia.pancake.orm.Mail;

import java.util.Collection;

class GET_Spam implements HttpRoute {
	private final InboxRoutes routes;
	private final String noneView;

	public GET_Spam(InboxRoutes routes) {
		this.routes = routes;
		this.noneView = new HttpView("/views/email/none.html").view();
	}

	@Override
	public byte[] route(Pancake pancake, HttpRequest request, String[] matches) {
		GenericPancakeView view = new GenericPancakeView(pancake, request);
		if (view.checkAccess()) return null;

		Collection<Mail> mail = pancake.getHTTP().getResources().findSpamEmails(request, request.getAuth(), 0, 0);
		if (mail == null) return null;

		routes.addSideNav(view, InboxRoutes.INBOX_SPAM);
		routes.addMailNav(view, mail, null);

		view.setContent(noneView);

		return view.view("Spam");
	}

}
