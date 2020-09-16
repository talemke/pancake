package net.tassia.pancake.http.routes.admin;

import net.tassia.pancake.Pancake;
import net.tassia.pancake.http.GenericPancakeView;
import net.tassia.pancake.http.HttpRequest;
import net.tassia.pancake.http.HttpView;
import net.tassia.pancake.http.PancakeHttpServer;
import net.tassia.pancake.http.views.MailNavView;
import net.tassia.pancake.http.views.SideNavView;
import net.tassia.pancake.orm.Account;
import net.tassia.pancake.orm.Group;

public class AdminRoutes {
	protected static final int CONFIG_GENERAL = 0;
	protected static final int CONFIG_DATABASE = 1;
	protected static final int CONFIG_HTTP = 2;
	protected static final int CONFIG_SMTP = 3;
	private final HttpView indexView;
	private final SideNavView sideNavView;
	private final MailNavView mailNavView;
	private final HttpView alertRootUserView;

	/* Constructor */
	public AdminRoutes() {
		this.indexView = new HttpView("/views/index.html");
		this.sideNavView = new SideNavView();
		this.mailNavView = new MailNavView();
		this.alertRootUserView = new HttpView("/views/alerts/root_user.html");
	}
	/* Constructor */



	/* Generate View */
	protected byte[] generateConfigView(Pancake pancake, HttpRequest request, int config, String title, String content) {
		// Check auth
		if (!request.checkAuth()) {
			request.redirect("/auth/login");
			return null;
		}

		// Build response
		GenericPancakeView view = new GenericPancakeView(request);
		view.setContent(content);

		// Build sidenav
		view.addSideNav("/admin/config", "Configuration", "fas fa-cog", true);
		view.addSideNav("/admin/accounts", "Accounts", "fas fa-user", false);
		view.addSideNav("/admin/groups", "Groups", "fas fa-users", false);

		// Build mailnav
		view.addMailNav("/admin/config", "General", "General configuration.", config == CONFIG_GENERAL);
		view.addMailNav("/admin/config/database", "Database", "MySQL / SQLite configuration.", config == CONFIG_DATABASE);
		view.addMailNav("/admin/config/http", "HTTP Server", "Configure the HTTP server.", config == CONFIG_HTTP);
		view.addMailNav("/admin/config/smtp", "SMTP Server", "Configure the SMTP server.", config == CONFIG_SMTP);

		// Show view
		return view.view(title);
	}

	protected byte[] generateAccountsView(Pancake pancake, HttpRequest request, Account focus) {
		// Check auth
		if (!request.checkAuth()) {
			request.redirect("/auth/login");
			return null;
		}

		// Build response
		GenericPancakeView view = new GenericPancakeView(request);
		// TODO: Content
		String title = focus != null ? focus.getName() : "Accounts";

		// Build sidenav
		view.addSideNav("/admin/config", "Configuration", "fas fa-cog", false);
		view.addSideNav("/admin/accounts", "Accounts", "fas fa-user", true);
		view.addSideNav("/admin/groups", "Groups", "fas fa-users", false);

		// Build mailnav
		for (Account acc : pancake.getAccounts()) {
			boolean active = focus != null && focus.getUUID().equals(acc.getUUID());
			view.addMailNav("/admin/accounts/" + acc.getUUID().toString(), acc.getName(), acc.getUUID().toString(), active);
		}

		// Show view
		return view.view(title);
	}

	protected byte[] generateGroupsView(Pancake pancake, HttpRequest request, Group focus) {
		// Check auth
		if (!request.checkAuth()) {
			request.redirect("/auth/login");
			return null;
		}

		// Build response
		GenericPancakeView view = new GenericPancakeView(request);
		// TODO: Content
		String title = focus != null ? focus.getName() : "Groups";

		// Build sidenav
		view.addSideNav("/admin/config", "Configuration", "fas fa-cog", false);
		view.addSideNav("/admin/accounts", "Accounts", "fas fa-user", false);
		view.addSideNav("/admin/groups", "Groups", "fas fa-users", true);

		// Build mailnav
		for (Group g : pancake.getGroups()) {
			boolean active = focus != null && focus.getUUID().equals(g.getUUID());
			view.addMailNav("/admin/groups/" + g.getUUID().toString(), g.getName(), g.getUUID().toString(), active);
		}

		// Show view
		return view.view(title);
	}
	/* Generate View */



	/* Register Routes */
    public void registerRoutes(PancakeHttpServer server) {

		// Accounts
		server.GET("\\/admin\\/accounts", new GET_Accounts(this));
		server.GET("\\/admin\\/accounts\\/" + Pancake.UUID_REGEX, new GET_Account(this));

		// Configuration
		server.GET("\\/admin\\/config", new GET_ConfigGeneral(this));
		server.GET("\\/admin\\/config\\/database", new GET_ConfigDatabase(this));
		server.GET("\\/admin\\/config\\/http", new GET_ConfigHTTP(this));
		server.GET("\\/admin\\/config\\/smtp", new GET_ConfigSMTP(this));

        // Groups
		server.GET("\\/admin\\/groups", new GET_Groups(this));
		server.GET("\\/admin\\/groups\\/" + Pancake.UUID_REGEX, new GET_Group(this));

    }
    /* Register Routes */

}
