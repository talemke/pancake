package net.tassia.pancake.http.routes.admin;

import net.tassia.pancake.Pancake;
import net.tassia.pancake.http.GenericPancakeView;
import net.tassia.pancake.http.HttpRequest;
import net.tassia.pancake.http.HttpRoute;
import net.tassia.pancake.http.HttpView;
import net.tassia.pancake.orm.Account;
import net.tassia.pancake.orm.EmailRoute;

class GET_Route implements HttpRoute {
	private final AdminRoutes routes;
	private final HttpView routeView;

	public GET_Route(AdminRoutes routes) {
		this.routes = routes;
		this.routeView = new HttpView("/views/route/route.html");
	}

	private String format(EmailRoute.Type type, String value) {
		switch (type) {
			case EXACT:
				return "Must equal " + value;
			case REGEX:
				return "Must match " + value;
			case ANY:
				return "Any";
			default:
				return "Unknown";
		}
	}

	@Override
	public byte[] route(Pancake pancake, HttpRequest request, String[] matches) {
		GenericPancakeView view = new GenericPancakeView(pancake, request);
		if (view.checkAccess()) return null;

		EmailRoute focus = pancake.getHTTP().getResources().findEmailRoute(request, matches[0]);
		if (focus == null) return null;

		routes.addSideNav(view, AdminRoutes.SIDENAV_ROUTES);
		routes.addRoutesMailNav(pancake, view, focus);

		view.setContent(routeView.view(
			new String[] { "uuid", focus.getUUID().toString() },
			new String[] { "user_rule", format(focus.getUsernameType(), focus.getUsernameString()) },
			new String[] { "host_role", format(focus.getHostnameType(), focus.getHostnameString()) },
			new String[] { "target", focus.getAccount().getName() }
		));

		return view.view("Routes");
	}

}
