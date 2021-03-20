package net.tassia.pancake.util

import net.tassia.pancake.io.DatabaseDriver
import net.tassia.pancake.io.PancakeConfig
import net.tassia.pancake.logging.Logger
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.name

/**
 * Utility class used to instantiate database connections.
 *
 * @since Pancake 1.0
 * @author Tassilo
 */
object DatabaseConnector {

	/**
	 * Creates a new database connection using the given config.
	 *
	 * @param config the config
	 * @return the database connection
	 */
	fun connect(config: PancakeConfig): Database {
		Logger.info("Initializing database...")

		// Connect
		val db = when (config.databaseDriver) {
			DatabaseDriver.SQLITE -> connectToSQLite()
			DatabaseDriver.MYSQL -> connectToMySQL(config)
		}

		// Debug information
		Logger.debug("- Name: ${db.name}")
		Logger.debug("- Dialect: ${db.dialect.name}")
		Logger.debug("- Vendor: ${db.vendor}")
		Logger.debug("- Version: ${db.version}")

		return db
	}



	private const val SQLITE_URL = "jdbc:sqlite:./data/storage.db"
	private const val SQLITE_DRIVER = "org.sqlite.JDBC"

	/**
	 * Connects to an SQLite database.
	 */
	private fun connectToSQLite(): Database {
		Logger.debug("- Using SQLite database...")
		return Database.connect(SQLITE_URL, SQLITE_DRIVER)
	}



	private const val MYSQL_URL = "jdbc:mysql://%s:%d/%s"
	private const val MYSQL_DRIVER = "com.mysql.jdbc.Driver"

	/**
	 * Connects to a MySQL database.
	 */
	private fun connectToMySQL(config: PancakeConfig): Database {
		Logger.debug("- Using MySQL database...")
		val url = MYSQL_URL.format(config.mysqlHostname, config.mysqlPort, config.mysqlDatabase)
		return Database.connect(url, MYSQL_DRIVER, config.mysqlUsername, config.mysqlPassword)
	}

}
