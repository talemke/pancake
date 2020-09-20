package net.tassia.pancake.http.routes.admin;

import net.tassia.pancake.Pancake;
import net.tassia.pancake.http.HttpRequest;
import net.tassia.pancake.http.HttpRoute;

import java.io.IOException;

class GET_RootGain implements HttpRoute {
	private final AdminRoutes routes;

	GET_RootGain(AdminRoutes routes) {
		this.routes = routes;
	}

	@Override
	public byte[] route(Pancake pancake, HttpRequest request, String[] matches) {
		// Check auth
		if (!request.checkAuth()) {
			request.setErrorPage(403);
			return null;
		}
		if (request.getAuth().isRoot()) {
			request.setErrorPage(403);
			return null;
		}
		if (!request.getAuth().isAdmin()) {
			request.setErrorPage(403);
			return null;
		}

		// Log
		try {
			String user = request.getAuth().getName() + " (" + request.getAuth().getUUID().toString() + ")";
			String session = pancake.getSecurity().md5(request.getCookie("PancakeSessionID"), "");
			routes.addRootLog(user + " gained root privileges. (Session: " + session + ")");
		} catch (IOException ex) {
			ex.printStackTrace();
			request.setErrorPage(500);
			return null;
		}

		pancake.getHTTP().addRootSession(request.getCookie("PancakeSessionID"));
		request.redirect("/admin/root");
		return null;
	}

}
