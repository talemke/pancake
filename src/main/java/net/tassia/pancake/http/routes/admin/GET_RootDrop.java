package net.tassia.pancake.http.routes.admin;

import net.tassia.pancake.Pancake;
import net.tassia.pancake.http.HttpRequest;
import net.tassia.pancake.http.HttpRoute;

import java.io.IOException;

class GET_RootDrop implements HttpRoute {
	private final AdminRoutes routes;

	GET_RootDrop(AdminRoutes routes) {
		this.routes = routes;
	}

	@Override
	public byte[] route(Pancake pancake, HttpRequest request, String[] matches) {
		// Check auth
		if (!request.checkAuth()) {
			request.setErrorPage(403);
			return null;
		}
		if (!request.getAuth().isRoot()) {
			request.setErrorPage(403);
			return null;
		}

		// Log
		try {
			String user = request.getAuth().getName() + " (" + request.getAuth().getUUID().toString() + ")";
			// We hash the session ID with MD5 to make it a bit harder to crack, as it's shown to admins,
			// and to shorten the string length
			String session = pancake.getSecurity().md5(request.getCookie("PancakeSessionID"));
			routes.addRootLog(user + " dropped root privileges. (Session: " + session + ")");
		} catch (IOException ex) {
			ex.printStackTrace();
			request.setErrorPage(500);
			return null;
		}

		String session = pancake.getSecurity().sha512(request.getCookie("PancakeSessionID"), request.getClientIP());
		pancake.getHTTP().dropRootSession(session);
		request.redirect("/admin/root");
		return null;
	}

}
