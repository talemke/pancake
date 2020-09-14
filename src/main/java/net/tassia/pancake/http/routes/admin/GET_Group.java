package net.tassia.pancake.http.routes.admin;

import net.tassia.pancake.Pancake;
import net.tassia.pancake.http.HttpRequest;
import net.tassia.pancake.http.HttpViewRoute;
import net.tassia.pancake.http.views.MailNavView;
import net.tassia.pancake.http.views.SideNavView;
import net.tassia.pancake.orm.Account;
import net.tassia.pancake.orm.Group;

import java.util.UUID;

class GET_Group extends HttpViewRoute {
	private final SideNavView sideNavView;
	private final MailNavView mailNavView;

	public GET_Group() {
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


		// Find group
		UUID uuid;
		try {
			uuid = UUID.fromString(matches[0]);
		} catch (IllegalArgumentException ex) {
			request.setErrorPage(404);
			return null;
		}
		Group g = pancake.getGroup(uuid);
		if (g == null) {
			request.setErrorPage(404);
			return null;
		}


		// Build sidenav
		String sidenav = "";
		sidenav += sideNavView.getView("/admin/config", "Configuration", "fas fa-cog", false);
		sidenav += sideNavView.getView("/admin/accounts", "Accounts", "fas fa-user", false);
		sidenav += sideNavView.getView("/admin/groups", "Groups", "fas fa-users", true);


		// Build mailnav
		StringBuilder mailnav = new StringBuilder();
		for (Group g2 : pancake.getGroups()) {
			mailnav.append(mailNavView.getView("/admin/groups/" + g2.getUUID().toString(), g2.getName(),
				g2.getUUID().toString(),g.getUUID().equals(g2.getUUID())));
		}


		// Show view
		return view(
			new String[] { "window_title", g.getName() + " | Pancake" },
			new String[] { "sidenav", sidenav },
			new String[] { "mailnav", mailnav.toString() }
		);
	}

}
