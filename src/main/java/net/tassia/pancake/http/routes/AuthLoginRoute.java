package net.tassia.pancake.http.routes;

import net.tassia.pancake.Pancake;
import net.tassia.pancake.http.HttpRequest;
import net.tassia.pancake.http.HttpViewRoute;

public class AuthLoginRoute extends HttpViewRoute {

	public AuthLoginRoute() {
		super("/views/auth/login.html");
	}

	@Override
	public byte[] route(Pancake pancake, HttpRequest request, String[] matches) {
		return view();
	}

}
