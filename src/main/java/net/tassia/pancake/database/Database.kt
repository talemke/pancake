package net.tassia.pancake.database

import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.Transaction
import org.jetbrains.exposed.sql.transactions.transaction as NativeTransaction

/**
 * Utility class that is used to interact with the database.
 *
 * @since Pancake 1.0
 * @author Tassilo
 */
object Database {

	/**
	 * The current database. May be `null`.
	 */
	private var database: Database? = null



	/**
	 * Executes the given block in a transaction.
	 *
	 * @param block the transaction block
	 */
	fun <T> transaction(block: Transaction.() -> T): T {
		return database?.let {
			NativeTransaction(it, block)
		} ?: throw IllegalStateException("Not connected to a database.")
	}



	/**
	 * Checks whether we currently are connected to a database.
	 */
	val isConnected: Boolean
		get() = database != null

	/**
	 * Connects to the given database.
	 *
	 * This function will throw an error if another database is already connected to.
	 *
	 * @param database the database to connect to
	 *
	 * @see isConnected
	 */
	fun connect(database: Database) {
		// Check current state
		if (isConnected) {
			throw IllegalStateException("Already connected to a database.")
		}

		// Register database
		this.database = database
	}

	/**
	 * Disconnects from any current database.
	 *
	 * This function will do nothing, if not currently connected to a database.
	 *
	 * @see isConnected
	 */
	fun disconnect() {
		this.database = null
	}

}
