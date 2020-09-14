package net.tassia.pancake.http.routes.admin;

import net.tassia.pancake.Pancake;
import net.tassia.pancake.http.HttpRequest;
import net.tassia.pancake.http.HttpRoute;
import net.tassia.pancake.http.HttpView;
import net.tassia.pancake.http.HttpViewRoute;
import net.tassia.pancake.http.views.MailNavView;
import net.tassia.pancake.http.views.SideNavView;
import net.tassia.pancake.orm.Group;

class GET_ConfigGeneral implements HttpRoute {
	private final AdminRoutes routes;
	private final HttpView content;

	public GET_ConfigGeneral(AdminRoutes routes) {
		this.routes = routes;
		this.content = new HttpView("/views/config/general.html");
	}

	@Override
	public byte[] route(Pancake pancake, HttpRequest request, String[] matches) {
		return routes.generateConfigView(pancake, request, AdminRoutes.CONFIG_GENERAL, "Configuration", content.view(
		));
	}

}
