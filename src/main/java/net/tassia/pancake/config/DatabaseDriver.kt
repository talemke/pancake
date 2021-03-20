package net.tassia.pancake.config

/**
 * Defines how to store the data.
 *
 * @since Pancake 1.0
 * @author Tassilo
 */
enum class DatabaseDriver {

	/**
	 * Use a local SQLite instance.
	 */
	SQLITE,

	/**
	 * Connect to a remote MySQL/MariaDB server.
	 */
	MYSQL;

}
