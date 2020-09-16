package net.tassia.pancake.http.routes.admin;

import net.tassia.pancake.Pancake;
import net.tassia.pancake.http.GenericPancakeView;
import net.tassia.pancake.http.HttpRequest;
import net.tassia.pancake.http.HttpRoute;
import net.tassia.pancake.orm.Account;

class GET_Account implements HttpRoute {
	private final AdminRoutes routes;

	public GET_Account(AdminRoutes routes) {
		this.routes = routes;
	}

	@Override
	public byte[] route(Pancake pancake, HttpRequest request, String[] matches) {
		GenericPancakeView view = new GenericPancakeView(pancake, request);
		if (view.checkAccess()) return null;

		Account focus = view.findAccount(matches[0]);
		if (focus == null) return null;

		routes.addSideNav(view, AdminRoutes.SIDENAV_ACCOUNTS);
		routes.addAccountsMailNav(pancake, view, focus);

		view.setContent(""); // TODO

		return view.view(focus.getName());
	}

}
