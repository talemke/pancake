package net.tassia.pancake

import net.tassia.pancake.config.ConfigEntry
import java.util.logging.Level

/**
 * The core configuration for Pancake. All settings that are non-crucial for the boot process
 * are stored in the database.
 *
 * @since Pancake 1.0
 * @author Tassilo
 */
data class PancakeConfig(

	/**
	 * The level used to determine what events to log.
	 */
	@ConfigEntry("Logging.Level")
	var loggingLevel: Level = Level.INFO,



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
