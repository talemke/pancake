package net.tassia.pancake.http.routes.admin;

import net.tassia.pancake.Pancake;
import net.tassia.pancake.http.HttpRequest;
import net.tassia.pancake.http.HttpRoute;

class GET_ConfigDatabase implements HttpRoute {
	private final AdminRoutes routes;

	public GET_ConfigDatabase(AdminRoutes routes) {
		this.routes = routes;
	}

	@Override
	public byte[] route(Pancake pancake, HttpRequest request, String[] matches) {
		return routes.generateConfigView(pancake, request, AdminRoutes.CONFIG_DATABASE, "Database Config", "");
	}

}
