package net.tassia.pancake.bootstrap

import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import net.tassia.Namespace
import net.tassia.logging.formatter.AnsiFormatter
import net.tassia.logging.handler.PrintStreamHandler
import net.tassia.pancake.StandardPancake
import org.sqlite.SQLiteDataSource
import java.util.logging.Logger
import javax.sql.DataSource
import kotlin.system.exitProcess

/**
 * The JVM-entrypoint class of Pancake.
 *
 * @since Pancake 1.0
 */
@Namespace
object Entrypoint {

	/**
	 * The JVM-entrypoint method of Pancake.
	 *
	 * @param args command-line arguments
	 */
	@JvmStatic
	fun main(args: Array<String>) {

		// Parse command-line arguments
		// TODO

		try {

			// Run application
			launch()

			// Done! Without any error :)
			exitProcess(ExitCode.STANDARD.code)

		} catch (ex: Throwable) {
			// Something went wrong
			exit(ex)
		}

	}

	/**
	 * Exits the application due to an unexpected error.
	 *
	 * @param ex the error
	 */
	private fun exit(ex: Throwable) {
		// Print error
		ex.printStackTrace()

		// Exit with code
		exitProcess(ExitCode.UNKNOWN_ERROR.code)
	}





	private fun findEnv(key: String): String {
		return System.getenv(key) ?: throw IllegalStateException("Environment variable '$key' is not set.")
	}





	/**
	 * Launches the application and blocks, until the application should (and has) shut down gracefully.
	 */
	private fun launch() {
		// Create logger
		val logger = createLogger()

		// Create data-source
		val source = createDataSource()

		// Create & launch application
		val pancake = StandardPancake(logger, source)
		pancake.launch()
	}

	private fun createLogger(): Logger {
		return Logger.getLogger("Pancake").also {
			it.useParentHandlers = false
			it.addHandler(PrintStreamHandler(System.out, AnsiFormatter()))
		}
	}

	private fun createDataSource(): DataSource {
		val useSQLite = System.getenv("DB_USE_SQLITE")?.toBoolean() ?: false
		return if (useSQLite) createSQLiteDataSource() else createMySQLDataSource()
	}

	private fun createSQLiteDataSource(): DataSource {
		return SQLiteDataSource().also {
			it.url = "jdbc:sqlite:./storage.db"
		}
	}

	private fun createMySQLDataSource(): DataSource {
		return HikariDataSource(HikariConfig().also {

			val hostname = findEnv("DB_MYSQL_HOSTNAME")
			val database = findEnv("DB_MYSQL_DATABASE")

			it.jdbcUrl = "jdbc:mysql://$hostname/$database"
			it.driverClassName = "com.mysql.cj.jdbc.Driver"
			it.username = findEnv("DB_MYSQL_USERNAME")
			it.password = findEnv("DB_MYSQL_PASSWORD")

			it.maximumPoolSize = System.getenv("DB_POOL_SIZE")?.toInt() ?: 16

		})
	}

}
