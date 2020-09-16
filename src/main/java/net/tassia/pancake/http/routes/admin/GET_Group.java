package net.tassia.pancake.http.routes.admin;

import net.tassia.pancake.Pancake;
import net.tassia.pancake.http.GenericPancakeView;
import net.tassia.pancake.http.HttpRequest;
import net.tassia.pancake.http.HttpRoute;
import net.tassia.pancake.http.HttpView;
import net.tassia.pancake.orm.Group;

class GET_Group implements HttpRoute {
	private final AdminRoutes routes;
	private final HttpView groupView;

	public GET_Group(AdminRoutes routes) {
		this.routes = routes;
		this.groupView = new HttpView("/views/group/group.html");
	}

	@Override
	public byte[] route(Pancake pancake, HttpRequest request, String[] matches) {
		GenericPancakeView view = new GenericPancakeView(pancake, request);
		if (view.checkAccess()) return null;

		Group focus = view.findGroup(matches[0]);
		if (focus == null) return null;

		routes.addSideNav(view, AdminRoutes.SIDENAV_GROUPS);
		routes.addGroupsMailNav(pancake, view, focus);

		view.setContent(groupView.view(
			new String[] { "group_name", focus.getName() },
			new String[] { "uuid", focus.getUUID().toString() }
		));

		return view.view(focus.getName());
	}

}
