package net.tassia.pancake.http.routes.admin;

import net.tassia.pancake.Pancake;
import net.tassia.pancake.http.GenericPancakeView;
import net.tassia.pancake.http.HttpRequest;
import net.tassia.pancake.http.HttpRoute;
import net.tassia.pancake.http.HttpView;

class GET_Groups implements HttpRoute {
	private final AdminRoutes routes;
	private final String noneView;

	public GET_Groups(AdminRoutes routes) {
		this.routes = routes;
		this.noneView = new HttpView("/views/group/none.html").view();
	}

	@Override
	public byte[] route(Pancake pancake, HttpRequest request, String[] matches) {
		GenericPancakeView view = new GenericPancakeView(pancake, request);
		if (view.checkAccess()) return null;
		if (view.checkAccess(request.getAuth().isRoot())) return null;

		routes.addSideNav(request.getAuth(), view, AdminRoutes.SIDENAV_GROUPS);
		routes.addGroupsMailNav(pancake, view, null);

		view.setContent(noneView);

		return view.view("Groups");
	}

}
