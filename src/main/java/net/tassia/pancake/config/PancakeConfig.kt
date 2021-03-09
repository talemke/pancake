package net.tassia.pancake.config

/**
 * The core configuration for Pancake. All settings that are non-crucial for the boot process
 * are stored in the database.
 *
 * @since Pancake 1.0
 * @author Tassilo
 */
data class PancakeConfig(

	/**
	 * Which database to use (SQLite, MySQL).
	 */
	@ConfigEntry("Database.Driver")
	var databaseDriver: DatabaseDriver = DatabaseDriver.SQLITE,

	/**
	 * The hostname of the MySQL Server.
	 */
	@ConfigEntry("Database.MySQL.Hostname")
	var mysqlHostname: String = "localhost",

	/**
	 * The port of the MySQL Server.
	 */
	@ConfigEntry("Database.MySQL.Port")
	var mysqlPort: Int = 3306,

	/**
	 * The name of the database to connect to.
	 */
	@ConfigEntry("Database.MySQL.Database")
	var mysqlDatabase: String = "database",

	/**
	 * The username to use when connecting to the MySQL Server.
	 */
	@ConfigEntry("Database.MySQL.Username")
	var mysqlUsername: String = "username",

	/**
	 * The password to use when connecting to the MySQL Server.
	 */
	@ConfigEntry("Database.MySQL.Password")
	var mysqlPassword: String = "password"

)
