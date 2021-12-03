package net.tassia.pancake.bootstrap

import net.tassia.Namespace
import net.tassia.pancake.StandardPancake
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
		TODO()
	}

	private fun createDataSource(): DataSource {
		TODO()
	}

}
