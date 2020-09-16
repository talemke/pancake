package net.tassia.pancake.http.routes.admin;

import net.tassia.pancake.Pancake;
import net.tassia.pancake.http.HttpRequest;
import net.tassia.pancake.http.HttpRoute;
import net.tassia.pancake.orm.Account;

class GET_RootDrop implements HttpRoute {

	@Override
	public byte[] route(Pancake pancake, HttpRequest request, String[] matches) {
		// Check auth
		if (!request.checkAuth()) {
			request.setErrorPage(403);
			return null;
		}
		if (!request.getAuth().getUUID().equals(Account.ROOT.getUUID())) {
			request.setErrorPage(403);
			return null;
		}

		pancake.getHTTP().dropRootSession(request.getCookie("PancakeSessionID"));
		request.redirect("/admin/root");
		return null;
	}

}
