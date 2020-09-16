package net.tassia.pancake;

import java.io.*;
import java.util.Properties;

public class PancakeConfiguration {

	public StorageDriver storageDriver = StorageDriver.MySQL;

	public String mysqlHostname = "localhost";
	public int mysqlPort = 3306;
	public String mysqlDatabase = "database";
	public String mysqlUsername = "username";
	public String mysqlPassword = "password";

	public int httpPort = 8080;
	public int httpBacklog = 0;



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
			config.storageDriver = StorageDriver.valueOf(p.getProperty("STORAGE_DRIVER"));
			config.mysqlHostname = p.getProperty("MYSQL_HOSTNAME");
			config.mysqlPort = Integer.parseInt(p.getProperty("MYSQL_PORT"));
			config.mysqlDatabase = p.getProperty("MYSQL_DATABASE");
			config.mysqlUsername = p.getProperty("MYSQL_USERNAME");
			config.mysqlPassword = p.getProperty("MYSQL_PASSWORD");
			config.httpPort = Integer.parseInt(p.getProperty("HTTP_PORT"));
			config.httpBacklog = Integer.parseInt(p.getProperty("HTTP_BACKLOG"));
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

		FileOutputStream fos = new FileOutputStream(file);
		String version = String.format("%d.%d.%d", Pancake.VERSION_MAJOR, Pancake.VERSION_MINOR, Pancake.VERSION_BUILD);
		p.store(fos, "Pancake Configuration | v" + version);
		fos.flush();
		fos.close();
	}

}
