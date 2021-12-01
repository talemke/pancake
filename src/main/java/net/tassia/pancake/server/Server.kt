package net.tassia.pancake.server

/**
 * The super-class for all servers (e.g. HTTP, SMTP, etc.)
 *
 * @since Jet 1.0
 */
abstract class Server {

	/**
	 * Attempts to start this server. This method may block while the server is loading,
	 * but should return as soon as possible.
	 */
	abstract fun start()

	/**
	 * Attempts to stop this server. This method may block until the server has shut down gracefully.
	 */
	abstract fun stop()



	/**
	 * Whether the server is currently running.
	 */
	abstract val isRunning: Boolean

}
