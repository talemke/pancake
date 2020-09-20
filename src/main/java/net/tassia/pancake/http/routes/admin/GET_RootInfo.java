package net.tassia.pancake.http.routes.admin;

import net.tassia.pancake.Pancake;
import net.tassia.pancake.http.GenericPancakeView;
import net.tassia.pancake.http.HttpRequest;
import net.tassia.pancake.http.HttpRoute;
import net.tassia.pancake.http.HttpView;
import net.tassia.pancake.orm.Account;

class GET_RootInfo implements HttpRoute {
	private final AdminRoutes routes;
	private final HttpView dropView;
	private final HttpView gainView;

	public GET_RootInfo(AdminRoutes routes) {
		this.routes = routes;
		this.dropView = new HttpView("/views/root/drop.html");
		this.gainView = new HttpView("/views/root/gain.html");
	}

	@Override
	public byte[] route(Pancake pancake, HttpRequest request, String[] matches) {
		GenericPancakeView view = new GenericPancakeView(pancake, request);
		if (view.checkAccess()) return null;
		if (view.checkAccess(request.getAuth().isAdmin())) return null;

		routes.addSideNav(request.getAuth(), view, AdminRoutes.SIDENAV_ROOT);
		routes.addRootMailNav(view, AdminRoutes.ROOT_GENERAL);

		if (request.getAuth().getUUID().equals(Account.ROOT.getUUID())) {
			view.setContent(dropView.view());
		} else {
			view.setContent(gainView.view());
		}

		return view.view("Root");
	}

}
