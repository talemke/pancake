package net.tassia.pancake.http.routes.admin;

import net.tassia.pancake.Pancake;
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
		return routes.generateConfigView(pancake, request, AdminRoutes.CONFIG_DATABASE, "Database Config", content.view(
		));
	}

}
