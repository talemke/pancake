package net.tassia.pancake.http.routes.auth;

import net.tassia.pancake.Pancake;
import net.tassia.pancake.http.HttpRequest;
import net.tassia.pancake.http.HttpViewRoute;

class GET_Login extends HttpViewRoute {

	public GET_Login() {
		super("/views/auth/login.html");
	}

	@Override
	public byte[] route(Pancake pancake, HttpRequest request, String[] matches) {
		return view();
	}

}
