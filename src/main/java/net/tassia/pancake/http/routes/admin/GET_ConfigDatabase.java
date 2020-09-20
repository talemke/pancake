package net.tassia.pancake.http.routes.admin;

import net.tassia.pancake.Pancake;
import net.tassia.pancake.http.GenericPancakeView;
import net.tassia.pancake.http.HttpRequest;
import net.tassia.pancake.http.HttpRoute;
import net.tassia.pancake.http.HttpView;

class GET_ConfigDatabase implements HttpRoute {
	private final AdminRoutes routes;
	private final HttpView content;

	public GET_ConfigDatabase(AdminRoutes routes) {
		this.routes = routes;
		this.content = new HttpView("/views/config/database.html");
	}

	@Override
	public byte[] route(Pancake pancake, HttpRequest request, String[] matches) {
		GenericPancakeView view = new GenericPancakeView(pancake, request);
		if (view.checkAccess()) return null;
		if (view.checkAccess(request.getAuth().isRoot())) return null;

		routes.addSideNav(request.getAuth(), view, AdminRoutes.SIDENAV_CONFIG);
		routes.addConfigMailNav(view, AdminRoutes.CONFIG_DATABASE);

		view.setContent(content.view());

		return view.view("Database Config");
	}

}
