package net.tassia.pancake.http.routes.admin;

import net.tassia.pancake.Pancake;
import net.tassia.pancake.http.GenericPancakeView;
import net.tassia.pancake.http.HttpRequest;
import net.tassia.pancake.http.HttpRoute;
import net.tassia.pancake.orm.Group;

class GET_Group implements HttpRoute {
	private final AdminRoutes routes;

	public GET_Group(AdminRoutes routes) {
		this.routes = routes;
	}

	@Override
	public byte[] route(Pancake pancake, HttpRequest request, String[] matches) {
		GenericPancakeView view = new GenericPancakeView(pancake, request);
		if (view.checkAccess()) return null;

		Group focus = view.findGroup(matches[0]);
		if (focus == null) return null;

		routes.addSideNav(view, AdminRoutes.SIDENAV_GROUPS);
		routes.addGroupsMailNav(pancake, view, focus);

		view.setContent(""); // TODO

		return view.view(focus.getName());
	}

}
