package net.tassia.pancake.config

import net.tassia.pancake.logging.Level
import net.tassia.pancake.util.config.Configuration

/**
 * The core configuration for Pancake. All settings that are non-crucial for the boot process
 * are stored in the database.
 *
 * @since Pancake 1.0
 * @author Tassilo
 */
class PancakeConfig : Configuration() {

	val loggingLevel by enum<Level>("Logging.Level")

	val databaseDriver by enum<DatabaseDriver>("Database.Driver")
	val databaseMySQLHostname by string("Database.MySQL.Hostname")
	val databaseMySQLPort by int("Database.MySQL.Port")
	val databaseMySQLDatabase by string("Database.MySQL.Database")
	val databaseMySQLUsername by string("Database.MySQL.Username")
	val databaseMySQLPassword by string("Database.MySQL.Password")

}
