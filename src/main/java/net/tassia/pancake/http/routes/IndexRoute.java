package net.tassia.pancake.http.routes;

import net.tassia.pancake.Pancake;
import net.tassia.pancake.http.HttpRequest;
import net.tassia.pancake.http.HttpRoute;

public class IndexRoute implements HttpRoute {

	@Override
	public byte[] route(Pancake pancake, HttpRequest request, String[] matches) {
		if (!request.checkAuth()) {
			request.redirect("/auth/login");
		} else {
			request.redirect("/inbox");
		}
		return null;
	}

}
