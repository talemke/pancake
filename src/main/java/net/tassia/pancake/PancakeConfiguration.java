package net.tassia.pancake;

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

}
