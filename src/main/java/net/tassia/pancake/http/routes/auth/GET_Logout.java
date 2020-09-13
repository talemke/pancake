package net.tassia.pancake.http.routes.auth;

import net.tassia.pancake.Pancake;
import net.tassia.pancake.http.HttpRequest;
import net.tassia.pancake.http.HttpViewRoute;

class GET_Logout extends HttpViewRoute {

	public GET_Logout() {
		super("/views/auth/logout.html");
	}

	@Override
	public byte[] route(Pancake pancake, HttpRequest request, String[] matches) {
		// Check auth
		if (!request.checkAuth()) {
			request.redirect("/auth/login");
			return null;
		}


		// Drop session
		pancake.getHTTP().dropSession(request.getCookie("PancakeSessionID"));


		// Show view
		return view();
	}

}
