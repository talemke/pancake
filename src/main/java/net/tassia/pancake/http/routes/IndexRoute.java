package net.tassia.pancake.http.routes;

import net.tassia.pancake.Pancake;
import net.tassia.pancake.http.HttpRequest;
import net.tassia.pancake.http.HttpView;
import net.tassia.pancake.http.HttpViewRoute;

public class IndexRoute extends HttpViewRoute {
	private final HttpView mailNavView;
	private final HttpView emailView;

	public IndexRoute() {
		super("/views/inbox/index.html");
		this.mailNavView = new HttpView("/views/inbox/mail_nav_entry.html");
		this.emailView = new HttpView("/views/inbox/email.html");
	}

	@Override
	public byte[] route(Pancake pancake, HttpRequest request, String[] matches) {
		// Check auth
		if (!request.checkAuth()) {
			request.redirect("/auth/login");
			return null;
		}

		request.redirect("/inbox");
		return null;
	}

}
