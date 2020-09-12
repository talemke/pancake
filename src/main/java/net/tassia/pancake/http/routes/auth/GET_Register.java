package net.tassia.pancake.http.routes.auth;

import net.tassia.pancake.Pancake;
import net.tassia.pancake.http.HttpRequest;
import net.tassia.pancake.http.HttpViewRoute;

class GET_Register extends HttpViewRoute {

	public GET_Register() {
		super("/views/auth/register.html");
	}

	@Override
	public byte[] route(Pancake pancake, HttpRequest request, String[] matches) {
		return view();
	}

}
