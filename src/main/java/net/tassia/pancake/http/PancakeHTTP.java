package net.tassia.pancake.http;

import net.tassia.pancake.Pancake;
import net.tassia.pancake.http.routes.AuthLoginRoute;
import net.tassia.pancake.http.routes.IndexRoute;
import net.tassia.pancake.orm.Account;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class PancakeHTTP {
	private final PancakeHttpServer server;
	private final Map<String, Account> sessions;

	/* Constructor */
	public PancakeHTTP(Pancake pancake) throws IOException {
		// Create variables
		this.sessions = new HashMap<>();

		// Create HTTP server
		this.server = new PancakeHttpServer(pancake);

		// Register routes
		server.GET("", new IndexRoute());
		server.GET("\\/auth\\/login", new AuthLoginRoute());
	}
	/* Constructor */



	/* Sessions */
	public Account getAccountBySessionID(String session) {
		return sessions.get(session);
	}

	public void addSession(String id, Account account) {
		sessions.put(id, account);
	}

	public void dropSession(String id) {
		sessions.remove(id);
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

}
