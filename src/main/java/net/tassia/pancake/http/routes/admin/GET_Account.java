package net.tassia.pancake.http.routes.admin;

import net.tassia.pancake.Pancake;
import net.tassia.pancake.http.HttpRequest;
import net.tassia.pancake.http.HttpViewRoute;
import net.tassia.pancake.http.views.MailNavView;
import net.tassia.pancake.http.views.SideNavView;
import net.tassia.pancake.orm.Account;

import java.util.UUID;

class GET_Account extends HttpViewRoute {
	private final SideNavView sideNavView;
	private final MailNavView mailNavView;

	public GET_Account() {
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


		// Find account
		UUID uuid;
		try {
			uuid = UUID.fromString(matches[0]);
		} catch (IllegalArgumentException ex) {
			request.setErrorPage(404);
			return null;
		}
		Account acc = pancake.getAccount(uuid);
		if (acc == null) {
			request.setErrorPage(404);
			return null;
		}


		// Build sidenav
		String sidenav = "";
		sidenav += sideNavView.getView("/admin/config", "Configuration", "fas fa-cog", false);
		sidenav += sideNavView.getView("/admin/accounts", "Accounts", "fas fa-user", true);
		sidenav += sideNavView.getView("/admin/groups", "Groups", "fas fa-users", false);


		// Build mailnav
		StringBuilder mailnav = new StringBuilder();
		for (Account acc2 : pancake.getAccounts()) {
			mailnav.append(mailNavView.getView("/admin/accounts/" + acc2.getUUID().toString(), acc2.getName(),
				acc2.getUUID().toString(), acc.getUUID().equals(acc2.getUUID())));
		}


		// Show view
		return view(
			new String[] { "window_title", acc.getName() + " | Pancake" },
			new String[] { "sidenav", sidenav },
			new String[] { "mailnav", mailnav.toString() }
		);
	}

}
