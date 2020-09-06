package net.tassia.pancake;

import net.tassia.pancake.database.PancakeMySQL;
import net.tassia.pancake.http.PancakeHTTP;
import net.tassia.pancake.logging.PancakeLogger;
import net.tassia.pancake.database.PancakeDB;
import net.tassia.pancake.database.PancakeSQLite;
import net.tassia.pancake.orm.Account;
import net.tassia.pancake.orm.Group;
import net.tassia.pancake.smtp.PancakeSMTP;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Logger;

public class Pancake {
	private final Logger logger;
	private final Collection<Account> accounts;
	private final Collection<Group> groups;
	private final PancakeConfiguration config;
	private final ExecutorService executorService;
	private final PancakeDB database;
	private final PancakeHTTP http;
	private final PancakeSMTP smtp;

	/* Constructor */
	public Pancake() {
		this.logger = setupLogger();
		logger.info("Initializing...");

		logger.fine("- Initializing variables...");
		this.accounts = new ArrayList<>();
		this.groups = new ArrayList<>();

		logger.fine("- Loading configuration...");
		this.config = new PancakeConfiguration();

		logger.fine("- Setting up executor service...");
		this.executorService = setupExecutorService();

		logger.fine("- Setting up database...");
		this.database = setupDatabase();

		logger.fine("- Setting up HTTP server...");
		this.http = setupHTTP();

		logger.fine("- Setting up SMTP server..");
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

	public Account getAccountByEmailName(String name) {
		for (Account account : accounts) {
			if (account.getName().equalsIgnoreCase(name)) {
				return account;
			}
		}
		return Account.ROOT;
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

		logger.fine("- Creating database connection...");
		database.connect();

		logger.fine("- Loading groups...");
		groups.clear();
		for (Group group : database.fetchGroups()) {
			groups.add(group);
		}
		groups.add(Group.ROOT);

		logger.fine("- Loading accounts...");
		accounts.clear();
		for (Account account : database.fetchAccounts()) {
			accounts.add(account);
		}
		accounts.add(Account.ROOT);

		logger.fine("- Starting HTTP server...");
		getHTTP().start();

		logger.fine("- Starting SMTP server...");
		// getSMTP().start();

		// logger.fine("- Starting IMAP server...");
		// TODO

		// logger.fine("- Starting POP3 server...");
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

	public ExecutorService getExecutorService() {
		return executorService;
	}

	public PancakeDB getDatabase() {
		return database;
	}

	public PancakeHTTP getHTTP() {
		return http;
	}

	public PancakeSMTP getSMTP() {
		return smtp;
	}
	/* Getters */

}
