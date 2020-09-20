package net.tassia.pancake;

import com.fasterxml.jackson.databind.ObjectMapper;
import net.tassia.pancake.cli.PancakeCLI;
import net.tassia.pancake.database.PancakeMySQL;
import net.tassia.pancake.http.PancakeHTTP;
import net.tassia.pancake.logging.PancakeLogger;
import net.tassia.pancake.database.PancakeDB;
import net.tassia.pancake.database.PancakeSQLite;
import net.tassia.pancake.orm.Account;
import net.tassia.pancake.orm.MailRoute;
import net.tassia.pancake.orm.Group;
import net.tassia.pancake.parser.PancakeParser;
import net.tassia.pancake.security.PancakeSecurity;
import net.tassia.pancake.smtp.PancakeSMTP;
import net.tassia.pancake.spam.PancakeSpam;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.sql.SQLException;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Logger;

public class Pancake implements PancakeConstants {
	private final Logger logger;
	private final Collection<Account> accounts;
	private final Collection<Group> groups;
	private final Collection<MailRoute> routes;
	private final PancakeConfiguration config;
	private final ObjectMapper mapper;
	private final PancakeSecurity security;
	private final ExecutorService executorService;
	private final PancakeDB database;
	private final PancakeCLI cli;
	private final PancakeSpam spam;
	private final PancakeParser parser;
	private final PancakeHTTP http;
	private final PancakeSMTP smtp;

	/* Constructor */
	public Pancake() throws IOException {
		this.logger = setupLogger();
		logger.info("Initializing...");

		logger.info("- Initializing variables...");
		this.accounts = new ArrayList<>();
		this.groups = new ArrayList<>();
		this.routes = new ArrayList<>();

		logger.info("- Loading configuration...");
		File configFile = new File("./.env");
		this.config = new PancakeConfiguration();
		if (configFile.exists()) {
			PancakeConfiguration.loadConfiguration(configFile, config);
		} else {
			PancakeConfiguration.saveConfiguration(configFile, config);
		}

		logger.info("- Setting up object mapper...");
		this.mapper = new ObjectMapper();

		logger.info("- Setting up security...");
		this.security = new PancakeSecurity(this);

		logger.info("- Setting up executor service...");
		this.executorService = setupExecutorService();

		logger.info("- Setting up database...");
		this.database = setupDatabase();

		logger.info("- Setting up CLI...");
		this.cli = setupCLI();

		logger.info("- Setting up spam filter...");
		this.spam = setupSpamFilter();

		logger.info("- Setting up mail parser...");
		this.parser = setupMailParser();

		logger.info("- Setting up HTTP server...");
		this.http = setupHTTP();

		logger.info("- Setting up SMTP server..");
		this.smtp = setupSMTP();

		logger.info("Successfully initialized!");
	}
	/* Constructor */





	/* Objects */
	public Collection<Account> getAccounts() {
		return accounts;
	}

	public Account getAccount(UUID uuid) {
		for (Account account : accounts) {
			if (account.getUUID().equals(uuid)) {
				return account;
			}
		}
		return null;
	}

	public Account getAccountByUsername(String username) {
		for (Account account : accounts) {
			if (account.getName().equalsIgnoreCase(username)) {
				return account;
			}
		}
		return null;
	}

	public Account getAccountByEmailName(String recipient) {
		for (MailRoute route : routes) {
			Account acc = route.matches(recipient);
			if (acc != null) return acc;
		}
		return Account.ROOT;
	}

	public void createAccount(String username, String password) throws SQLException {
		// Create account
		Account acc = new Account();
		acc.setName(username);
		acc.setPassword(getSecurity().hashPassword(password));
		acc.setGroup(getDefaultGroup());

		// Store account
		getDatabase().storeAccount(acc);

		// Cache account
		accounts.add(acc);
	}

	public Collection<Group> getGroups() {
		return groups;
	}

	public Group getGroup(UUID uuid) {
		for (Group group : groups) {
			if (group.getUUID().equals(uuid)) {
				return group;
			}
		}
		return null;
	}

	public Group getDefaultGroup() {
		return Group.USER;
	}

	public Collection<MailRoute> getRoutes() {
		return routes;
	}

	public MailRoute getRoute(UUID uuid) {
		for (MailRoute route : routes) {
			if (route.getUUID().equals(uuid)) {
				return route;
			}
		}
		return null;
	}
	/* Objects */





	/* Setup */
	protected Logger setupLogger() {
		return PancakeLogger.createLogger();
	}

	protected ExecutorService setupExecutorService() {
		return Executors.newCachedThreadPool();
	}

	protected PancakeDB setupDatabase() {
		switch (getConfig().storageDriver) {
			case MySQL:
				return new PancakeMySQL(this);

			case SQLite:
				return new PancakeSQLite(this);

			default:
				throw new Error("Unknown storage driver.");
		}
	}

	protected PancakeCLI setupCLI() {
		return new PancakeCLI(this);
	}

	protected PancakeSpam setupSpamFilter() {
		return new PancakeSpam(this);
	}

	protected PancakeParser setupMailParser() {
		return new PancakeParser(this);
	}

	protected PancakeHTTP setupHTTP() {
		try {
			return new PancakeHTTP(this);
		} catch (IOException ex) {
			throw new Error(ex);
		}
	}

	protected PancakeSMTP setupSMTP() {
		return new PancakeSMTP(this);
	}
	/* Setup */





	/* Start */
	public void start() throws Exception {
		logger.info("Starting...");

		logger.info("- Creating database connection...");
		database.connect();

		logger.info("- Loading groups...");
		groups.clear();
        groups.addAll(database.fetchGroups());
		groups.add(Group.ROOT);
		groups.add(Group.USER);

		logger.info("- Loading accounts...");
		accounts.clear();
		accounts.addAll(database.fetchAccounts());
		accounts.add(Account.ROOT);

		logger.info("- Loading email routes...");
		routes.clear();
		routes.addAll(database.fetchRoutes());

		logger.info("- Starting CLI...");
		cli.start();

		logger.info("- Starting HTTP server...");
		getHTTP().start();

		logger.info("- Starting SMTP server...");
		getSMTP().start();

		// logger.info("- Starting IMAP server...");
		// TODO

		// logger.info("- Starting POP3 server...");
		// TODO

		logger.info("Successfully started all systems!");
	}
	/* Start */





	/* Getters */
	public Logger getLogger() {
		return logger;
	}

	public PancakeConfiguration getConfig() {
		return config;
	}

	public ObjectMapper getMapper() {
		return mapper;
	}

	public PancakeSecurity getSecurity() {
		return security;
	}

	public ExecutorService getExecutorService() {
		return executorService;
	}

	public PancakeDB getDatabase() {
		return database;
	}

	public PancakeCLI getCLI() {
		return cli;
	}

	public PancakeSpam getSpamFilter() {
		return spam;
	}

	public PancakeParser getParser() {
		return parser;
	}

	public PancakeHTTP getHTTP() {
		return http;
	}

	public PancakeSMTP getSMTP() {
		return smtp;
	}
	/* Getters */





	/* Utility */
	public static String formatSize(int bytes) {
		if (bytes > 1000) {
			double b = (double) bytes / 1000D;
			return ((double) Math.round(b * 100) / 100) + " kB";
		}
		return bytes + " bytes";
	}

	public static String serializeStringMap(Map<String, String> map) {
		if (map == null) return "";
		StringBuilder str = new StringBuilder();
		for (Map.Entry<String, String> e : map.entrySet()) {
			String k = Base64.getEncoder().encodeToString(e.getKey().getBytes(StandardCharsets.UTF_8));
			String v = Base64.getEncoder().encodeToString(e.getValue().getBytes(StandardCharsets.UTF_8));
			str.append(";").append(k).append(",").append(v);
		}
		return str.length() == 0 ? str.toString() : str.substring(1);
	}

	public static Map<String, String> deserializeStringMap(String str) {
		if (!str.contains(";")) return new HashMap<>();
		Map<String, String> map = new HashMap<>();
		for (String s : str.split(";")) {
			String[] s2 = s.split(",");
			if (s2.length != 2) throw new IllegalArgumentException();
			String k = new String(Base64.getDecoder().decode(s2[0]), StandardCharsets.UTF_8);
			String v = new String(Base64.getDecoder().decode(s2[1]), StandardCharsets.UTF_8);
			map.put(k, v);
		}
		return map;
	}
	/* Utility */

}
