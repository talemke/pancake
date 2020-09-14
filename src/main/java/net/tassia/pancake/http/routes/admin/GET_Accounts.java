package net.tassia.pancake.http.routes.admin;

import net.tassia.pancake.Pancake;
import net.tassia.pancake.http.HttpRequest;
import net.tassia.pancake.http.HttpViewRoute;
import net.tassia.pancake.http.views.MailNavView;
import net.tassia.pancake.http.views.SideNavView;
import net.tassia.pancake.orm.Account;

class GET_Accounts extends HttpViewRoute {
	private final SideNavView sideNavView;
	private final MailNavView mailNavView;

	public GET_Accounts() {
		super("/views/index.html");
		this.sideNavView = new SideNavView();
		this.mailNavView = new MailNavView();
	}

	@Override
	public byte[] route(Pancake pancake, HttpRequest request, String[] matches) {
		// Check auth
		if (!request.checkAuth()) {
			request.redirect("/auth/login");
			return null;
		}


		// Build sidenav
		String sidenav = "";
		sidenav += sideNavView.getView("/admin/config", "Configuration", "fas fa-cog", false);
		sidenav += sideNavView.getView("/admin/accounts", "Accounts", "fas fa-user", true);
		sidenav += sideNavView.getView("/admin/groups", "Groups", "fas fa-users", false);


		// Build mailnav
		StringBuilder mailnav = new StringBuilder();
		for (Account acc : pancake.getAccounts()) {
			mailnav.append(mailNavView.getView("/admin/accounts/" + acc.getUUID().toString(), acc.getName(), acc.getUUID().toString(), false));
		}


		// Show view
		return view(
			new String[] { "window_title", "Accounts | Pancake" },
			new String[] { "sidenav", sidenav },
			new String[] { "mailnav", mailnav.toString() }
		);
	}

}
