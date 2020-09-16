package net.tassia.pancake.http.routes.admin;

import net.tassia.pancake.Pancake;
import net.tassia.pancake.http.HttpRequest;
import net.tassia.pancake.http.HttpView;
import net.tassia.pancake.http.PancakeHttpServer;
import net.tassia.pancake.http.views.MailNavView;
import net.tassia.pancake.http.views.SideNavView;

public class AdminRoutes {
	protected static final int CONFIG_GENERAL = 0;
	protected static final int CONFIG_DATABASE = 1;
	protected static final int CONFIG_HTTP = 2;
	protected static final int CONFIG_SMTP = 3;
	private final HttpView indexView;
	private final SideNavView sideNavView;
	private final MailNavView mailNavView;

	/* Constructor */
	public AdminRoutes() {
		this.indexView = new HttpView("/views/index.html");
		this.sideNavView = new SideNavView();
		this.mailNavView = new MailNavView();
	}
	/* Constructor */



	/* Generate View */
	protected byte[] generateConfigView(Pancake pancake, HttpRequest request, int config, String title, String content) {
		// Check auth
		if (!request.checkAuth()) {
			request.redirect("/auth/login");
			return null;
		}


		// Build sidenav
		String sidenav = "";
		sidenav += sideNavView.getView("/admin/config", "Configuration", "fas fa-cog", true);
		sidenav += sideNavView.getView("/admin/accounts", "Accounts", "fas fa-user", false);
		sidenav += sideNavView.getView("/admin/groups", "Groups", "fas fa-users", false);


		// Build mailnav
		String mailnav = "";
		mailnav += mailNavView.getView("/admin/config", "General", "General configuration.", config == CONFIG_GENERAL);
		mailnav += mailNavView.getView("/admin/config/database", "Database", "MySQL / SQLite configuration.", config == CONFIG_DATABASE);
		mailnav += mailNavView.getView("/admin/config/http", "HTTP Server", "Configure the HTTP server.", config == CONFIG_HTTP);
		mailnav += mailNavView.getView("/admin/config/smtp", "SMTP Server", "Configure the SMTP server.", config == CONFIG_SMTP);


		// Show view
		return indexView.viewData(
			new String[] { "window_title", title + " | Pancake" },
			new String[] { "sidenav", sidenav },
			new String[] { "mailnav", mailnav },
			new String[] { "content", content },
			new String[] { "username", request.getAuth().getName() }
		);
	}
	/* Generate View */



	/* Register Routes */
    public void registerRoutes(PancakeHttpServer server) {

		// Accounts
		server.GET("\\/admin\\/accounts", new GET_Accounts());
		server.GET("\\/admin\\/accounts\\/" + Pancake.UUID_REGEX, new GET_Account());

		// Configuration
		server.GET("\\/admin\\/config", new GET_ConfigGeneral(this));
		server.GET("\\/admin\\/config\\/database", new GET_ConfigDatabase(this));
		server.GET("\\/admin\\/config\\/http", new GET_ConfigHTTP(this));
		server.GET("\\/admin\\/config\\/smtp", new GET_ConfigSMTP(this));

        // Groups
		server.GET("\\/admin\\/groups", new GET_Groups());
		server.GET("\\/admin\\/groups\\/" + Pancake.UUID_REGEX, new GET_Group());

    }
    /* Register Routes */

}
