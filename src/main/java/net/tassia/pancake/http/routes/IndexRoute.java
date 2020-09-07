package net.tassia.pancake.http.routes;

import net.tassia.pancake.Pancake;
import net.tassia.pancake.http.HttpRequest;
import net.tassia.pancake.http.HttpViewRoute;

public class IndexRoute extends HttpViewRoute {

	public IndexRoute() {
		super("/views/index.html");
	}

	@Override
	public byte[] route(Pancake pancake, HttpRequest request, String[] matches) {
		// Check auth
		if (!request.checkAuth()) {
			request.redirect("/auth/login");
			return null;
		}

		return view();
	}

}
