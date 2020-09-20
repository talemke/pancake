package net.tassia.pancake.http;

import net.tassia.pancake.Pancake;
import net.tassia.pancake.http.routes.admin.AdminRoutes;
import net.tassia.pancake.http.routes.auth.AuthRoutes;
import net.tassia.pancake.http.routes.IndexRoute;
import net.tassia.pancake.http.routes.api.ApiRoutes;
import net.tassia.pancake.http.routes.inbox.InboxRoutes;
import net.tassia.pancake.orm.Account;

import java.io.IOException;
import java.util.*;

public class PancakeHTTP {
	private final PancakeHttpServer server;
	private final ResourceManager resourceManager;
	private final Map<String, Account> sessions;
	private final Collection<String> rootSessions;

	/* Constructor */
	public PancakeHTTP(Pancake pancake) throws IOException {
		// Create variables
		this.sessions = new HashMap<>();
		this.rootSessions = new ArrayList<>();
		this.resourceManager = new ResourceManager(pancake);

		// Create HTTP server
		this.server = new PancakeHttpServer(pancake);

		// Register routes
		server.GET("", new IndexRoute());

		// Register assets
		registerStaticAssets();

		// Register Admin routes
		new AdminRoutes().registerRoutes(server);

		// Register API routes
		new ApiRoutes().registerRoutes(server);

		// Register Auth routes
		new AuthRoutes().registerRoutes(server);

		// Register Inbox routes
		new InboxRoutes().registerRoutes(server);
	}
	/* Constructor */



	/* Sessions */
	public Account getAccountBySessionID(String session) {
		return sessions.get(session);
	}

	public void addSession(String id, Account account) {
		sessions.put(id, account);
	}

	public boolean dropSession(String id) {
		return sessions.remove(id) != null;
	}

	public Map<String, Account> getSessions() {
		return sessions;
	}

	public void addRootSession(String id) {
		rootSessions.add(id);
	}

	public boolean dropRootSession(String id) {
		return rootSessions.remove(id);
	}

	public boolean isRootSession(String id) {
		return rootSessions.contains(id);
	}

	public Collection<String> getRootSessions() {
		return rootSessions;
	}

	public int dropUserSessions(Account acc) {
		int count = 0;
		for (Map.Entry<String, Account> e : sessions.entrySet()) {
			if (!e.getValue().equals(acc)) continue;

			// Drop normal session
			dropSession(e.getKey());
			count++;

			// Drop root session
			if (dropRootSession(e.getKey())) {
				count++;
			}
		}
		return count;
	}
	/* Sessions */



	/* Start */
	public void start() {
		server.start();
	}
	/* Start */



	/* Escape XSS */
	public static String escapeXSS(String str) {
		// TODO
		return str;
	}
	/* Escape XSS */



	/* Getters */
	public ResourceManager getResources() {
		return resourceManager;
	}
	/* Getters */



	/* Static Assets */
	private void registerStaticAssets() {
		server.serveStaticResource("/css/bootstrap.css", "/assets/css/bootstrap.css", "text/css", "utf-8");
		server.serveStaticResource("/css/global.css", "/assets/css/global.css", "text/css", "utf-8");
		server.serveStaticResource("/css/loading.css", "/assets/css/loading.css", "text/css", "utf-8");
		server.serveStaticResource("/css/simple.css", "/assets/css/simple.css", "text/css", "utf-8");
		server.serveStaticResource("/js/global.js", "/assets/js/global.js", "text/javascript", "utf-8");
		server.serveStaticResource("/js/login.js", "/assets/js/login.js", "text/javascript", "utf-8");
		server.serveStaticResource("/js/register.js", "/assets/js/register.js", "text/javascript", "utf-8");
	}
	/* Static Assets */

}
