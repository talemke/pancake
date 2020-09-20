package net.tassia.pancake;

import java.io.*;
import java.util.Properties;

public class PancakeConfiguration {

	public String brandName = "Pancake";
	public StorageDriver storageDriver = StorageDriver.MySQL;

	public String mysqlHostname = "localhost";
	public int mysqlPort = 3306;
	public String mysqlDatabase = "database";
	public String mysqlUsername = "username";
	public String mysqlPassword = "password";

	public int httpPort = 8080;
	public int httpBacklog = 0;

	public int smtpPort = 25;
	public int smtpBacklog = 50;
	public boolean smtpEnableTLS = false;
	public boolean smtpHideTLS = false;
	public boolean smtpRequireTLS = false;
	public int smtpMaxConnections = 1000;
	public int smtpConnectionTimeout = 60000;
	public int smtpMaxRecipients = 1000;
	public int smtpMaxMessageSize = 0;



	public enum StorageDriver {
		MySQL, SQLite
    }



    public static void loadConfiguration(File file, PancakeConfiguration config) throws IOException {
		if (!file.exists()) return;
		Properties p = new Properties();

		FileInputStream fis = new FileInputStream(file);
		p.load(fis);
		fis.close();

		try {
			config.brandName = p.getProperty("BRAND_NAME");
			config.storageDriver = StorageDriver.valueOf(p.getProperty("STORAGE_DRIVER"));

			config.mysqlHostname = p.getProperty("MYSQL_HOSTNAME");
			config.mysqlPort = Integer.parseInt(p.getProperty("MYSQL_PORT"));
			config.mysqlDatabase = p.getProperty("MYSQL_DATABASE");
			config.mysqlUsername = p.getProperty("MYSQL_USERNAME");
			config.mysqlPassword = p.getProperty("MYSQL_PASSWORD");

			config.httpPort = Integer.parseInt(p.getProperty("HTTP_PORT"));
			config.httpBacklog = Integer.parseInt(p.getProperty("HTTP_BACKLOG"));

			config.smtpPort = Integer.parseInt(p.getProperty("SMTP_PORT"));
			config.smtpBacklog = Integer.parseInt(p.getProperty("SMTP_BACKLOG"));
			config.smtpEnableTLS = Boolean.parseBoolean(p.getProperty("SMTP_ENABLE_TLS"));
			config.smtpHideTLS = Boolean.parseBoolean(p.getProperty("SMTP_HIDE_TLS"));
			config.smtpRequireTLS = Boolean.parseBoolean(p.getProperty("SMTP_REQUIRE_TLS"));
			config.smtpMaxConnections = Integer.parseInt(p.getProperty("SMTP_MAX_CONNECTIONS"));
			config.smtpConnectionTimeout = Integer.parseInt(p.getProperty("SMTP_CONNECTION_TIMEOUT"));
			config.smtpMaxRecipients = Integer.parseInt(p.getProperty("SMTP_MAX_RECIPIENTS"));
			config.smtpMaxMessageSize = Integer.parseInt(p.getProperty("SMTP_MAX_MESSAGE_SIZE"));
		} catch (Throwable ex) {
			throw new IOException("Invalid configuration, try deleting the .env file.", ex);
		}
	}



	public static void saveConfiguration(File file, PancakeConfiguration config) throws IOException {
		if (!file.exists()) {
			file.getParentFile().mkdirs();
			file.createNewFile();
		}
		Properties p = new Properties();

		p.setProperty("STORAGE_DRIVER", config.storageDriver.name());

		p.setProperty("MYSQL_HOSTNAME", config.mysqlHostname);
		p.setProperty("MYSQL_PORT", Integer.toString(config.mysqlPort));
		p.setProperty("MYSQL_DATABASE", config.mysqlDatabase);
		p.setProperty("MYSQL_USERNAME", config.mysqlUsername);
		p.setProperty("MYSQL_PASSWORD", config.mysqlPassword);

		p.setProperty("HTTP_PORT", Integer.toString(config.httpPort));
		p.setProperty("HTTP_BACKLOG", Integer.toString(config.httpBacklog));

		p.setProperty("SMTP_PORT", Integer.toString(config.smtpPort));
		p.setProperty("SMTP_BACKLOG", Integer.toString(config.smtpBacklog));
		p.setProperty("SMTP_ENABLE_TLS", Boolean.toString(config.smtpEnableTLS));
		p.setProperty("SMTP_HIDE_TLS", Boolean.toString(config.smtpHideTLS));
		p.setProperty("SMTP_REQUIRE_TLS", Boolean.toString(config.smtpRequireTLS));
		p.setProperty("SMTP_MAX_CONNECTIONS", Integer.toString(config.smtpMaxConnections));
		p.setProperty("SMTP_CONNECTION_TIMEOUT", Integer.toString(config.smtpConnectionTimeout));
		p.setProperty("SMTP_MAX_RECIPIENTS", Integer.toString(config.smtpMaxRecipients));
		p.setProperty("SMTP_MAX_MESSAGE_SIZE", Integer.toString(config.smtpMaxMessageSize));

		FileOutputStream fos = new FileOutputStream(file);
		String version = String.format("%d.%d.%d", Pancake.VERSION_MAJOR, Pancake.VERSION_MINOR, Pancake.VERSION_BUILD);
		p.store(fos, "Pancake Configuration | v" + version);
		fos.flush();
		fos.close();
	}

}
