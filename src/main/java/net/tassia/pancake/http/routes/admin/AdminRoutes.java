package net.tassia.pancake.http.routes.admin;

import net.tassia.pancake.Pancake;
import net.tassia.pancake.http.GenericPancakeView;
import net.tassia.pancake.http.HttpView;
import net.tassia.pancake.http.PancakeHttpServer;
import net.tassia.pancake.http.views.MailNavView;
import net.tassia.pancake.http.views.SideNavView;
import net.tassia.pancake.orm.Account;
import net.tassia.pancake.orm.MailRoute;
import net.tassia.pancake.orm.Group;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class AdminRoutes {
	protected static final int SIDENAV_CONFIG = 0;
	protected static final int SIDENAV_ACCOUNTS = 1;
	protected static final int SIDENAV_GROUPS = 2;
	protected static final int SIDENAV_ROOT = 3;
	protected static final int SIDENAV_LOGS = 4;
	protected static final int SIDENAV_ROUTES = 5;
	protected static final int CONFIG_GENERAL = 0;
	protected static final int CONFIG_DATABASE = 1;
	protected static final int CONFIG_HTTP = 2;
	protected static final int CONFIG_SMTP = 3;
	protected static final int ROOT_GENERAL = 0;
	protected static final int ROOT_LOGS = 1;
	protected final File rootLogsFile;
	private final HttpView indexView;
	private final SideNavView sideNavView;
	private final MailNavView mailNavView;
	private final HttpView alertRootUserView;

	/* Constructor */
	public AdminRoutes() {
		this.rootLogsFile = new File("logs", "__root.txt");
		if (!rootLogsFile.exists()) {
			try {
				rootLogsFile.getParentFile().mkdirs();
				rootLogsFile.createNewFile();
			} catch (IOException ex) {
				throw new Error("Failed to create root logs file.", ex);
			}
		}

		this.indexView = new HttpView("/views/index.html");
		this.sideNavView = new SideNavView();
		this.mailNavView = new MailNavView();
		this.alertRootUserView = new HttpView("/views/alerts/root_user.html");
	}
	/* Constructor */



	/* Generate View */
	protected void addSideNav(Account user, GenericPancakeView view, int current) {
		if (user.isRoot()) view.addSideNav("/admin/config", "Configuration", "fas fa-cog", current == SIDENAV_CONFIG);
		if (user.isRoot()) view.addSideNav("/admin/logs", "Log Files", "fas fa-list-ul", current == SIDENAV_LOGS);
		if (user.isRoot()) view.addSideNav("/admin/accounts", "Accounts", "fas fa-user", current == SIDENAV_ACCOUNTS);
		if (user.isRoot()) view.addSideNav("/admin/groups", "Groups", "fas fa-users", current == SIDENAV_GROUPS);
		if (user.getGroup().hasFlag(Pancake.FLAG_GROUP_ADMIN)) view.addSideNav("/admin/root", "Root User", "fas fa-user-tie", current == SIDENAV_ROOT);
		if (user.isRoot()) view.addSideNav("/admin/routes", "Routes", "fas fa-random", current == SIDENAV_ROUTES);
	}

	protected void addConfigMailNav(GenericPancakeView view, int current) {
		view.addMailNav("/admin/config", "General", "General configuration.", current == CONFIG_GENERAL);
		view.addMailNav("/admin/config/database", "Database", "MySQL / SQLite configuration.", current == CONFIG_DATABASE);
		view.addMailNav("/admin/config/http", "HTTP Server", "Configure the HTTP server.", current == CONFIG_HTTP);
		view.addMailNav("/admin/config/smtp", "SMTP Server", "Configure the SMTP server.", current == CONFIG_SMTP);
	}

	protected void addLogsMailNav(GenericPancakeView view, String current) {
		File dir = new File("logs");
		if (!dir.isDirectory()) return;
		String[] list = Objects.requireNonNull(dir.list());
		Arrays.sort(list);
		for (int i = list.length - 1; i >= 0; i--) {
			if (list[i].startsWith("__")) continue;
			String id = list[i].substring(0, list[i].length() - 4);
			long time = Long.parseLong(id, 16);
			String date = new SimpleDateFormat("dd-MM-yyyy HH:mm (z)").format(new Date(time));
			view.addMailNav("/admin/logs/" + id, date, "0x" + id, id.equals(current));
		}
	}

	protected void addAccountsMailNav(Pancake pancake, GenericPancakeView view, Account focus) {
		for (Account acc : pancake.getAccounts()) {
			boolean active = focus != null && focus.getUUID().equals(acc.getUUID());
			view.addMailNav("/admin/accounts/" + acc.getUUID().toString(), acc.getName(), acc.getUUID().toString(), active);
		}
	}

	protected void addGroupsMailNav(Pancake pancake, GenericPancakeView view, Group focus) {
		for (Group g : pancake.getGroups()) {
			boolean active = focus != null && focus.getUUID().equals(g.getUUID());
			view.addMailNav("/admin/groups/" + g.getUUID().toString(), g.getName(), g.getUUID().toString(), active);
		}
	}

	protected void addRootMailNav(GenericPancakeView view, int current) {
		view.addMailNav("/admin/root", "Root Access", "Gain or drop root access.", current == ROOT_GENERAL);
		view.addMailNav("/admin/root/logs", "Logs", "Logs about root sessions.", current == ROOT_LOGS);
	}

	protected void addRoutesMailNav(Pancake pancake, GenericPancakeView view, MailRoute focus) {
		for (MailRoute route : pancake.getRoutes()) {
			boolean active = focus != null && focus.getUUID().equals(route.getUUID());
			view.addMailNav("/admin/routes/" + route.getUUID().toString(), route.toString(), route.getUUID().toString(), active);
		}
	}
	/* Generate View */



	/* Logs */
	private final SimpleDateFormat logFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss.SSS");
	protected void addRootLog(String log) throws IOException {
		log = logFormat.format(new Date()) + " | " + log;
		List<String> lines = Files.readAllLines(rootLogsFile.toPath());
		lines.add(0, log);
		Files.write(rootLogsFile.toPath(), lines);
	}
	/* Logs */



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

		// Accounts
		server.GET("\\/admin\\/logs", new GET_Logs(this));
		server.GET("\\/admin\\/logs\\/([0-9a-f]+)", new GET_Log(this));

		// Root
		server.GET("\\/admin\\/root", new GET_RootInfo(this));
		server.GET("\\/admin\\/root\\/drop", new GET_RootDrop(this));
		server.GET("\\/admin\\/root\\/gain", new GET_RootGain(this));
		server.GET("\\/admin\\/root\\/logs", new GET_RootLogs(this));

		// Routes
		server.GET("\\/admin\\/routes", new GET_Routes(this));
		server.GET("\\/admin\\/routes\\/" + Pancake.UUID_REGEX, new GET_Route(this));

    }
    /* Register Routes */

}
