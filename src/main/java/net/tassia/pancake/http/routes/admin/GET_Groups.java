package net.tassia.pancake.http.routes.admin;

import net.tassia.pancake.Pancake;
import net.tassia.pancake.http.HttpRequest;
import net.tassia.pancake.http.HttpViewRoute;
import net.tassia.pancake.http.views.MailNavView;
import net.tassia.pancake.http.views.SideNavView;
import net.tassia.pancake.orm.Account;
import net.tassia.pancake.orm.Group;

class GET_Groups extends HttpViewRoute {
	private final SideNavView sideNavView;
	private final MailNavView mailNavView;

	public GET_Groups() {
		super("/views/index.html");
		this.sideNavView = new SideNavView();
		this.mailNavView = new MailNavView();
	}

	@Override
	public byte[] route(Pancake pancake, HttpRequest request, String[] matches) {
		// Check auth
		if (request.checkAuth()) {
			request.redirect("/");
			return null;
		}


		// Build sidenav
		String sidenav = "";
		sidenav += sideNavView.getView("/admin/config", "Configuration", "fas fa-cog", false);
		sidenav += sideNavView.getView("/admin/accounts", "Accounts", "fas fa-user", false);
		sidenav += sideNavView.getView("/admin/groups", "Groups", "fas fa-users", true);


		// Build mailnav
		StringBuilder mailnav = new StringBuilder();
		for (Group g : pancake.getGroups()) {
			mailnav.append(mailNavView.getView("/admin/groups/" + g.getUUID().toString(), g.getName(), g.getUUID().toString(), false));
		}


		// Show view
		return view(
			new String[] { "window_title", "Groups | Pancake" },
			new String[] { "sidenav", sidenav },
			new String[] { "mailnav", mailnav.toString() }
		);
	}

}
