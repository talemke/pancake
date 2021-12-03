package net.tassia.pancake.database

import java.sql.Connection
import java.sql.PreparedStatement

class StandardTransaction(private val connection: Connection) : Transaction() {

	override fun prepareStatement(query: String): PreparedStatement {
		return connection.prepareStatement(query)
	}



	internal suspend fun <T> executeBlock(block: suspend (Transaction) -> T): T {
		// Start savepoint
		val savepoint = connection.setSavepoint()

		try {
			// Execute block
			val result = block(this)

			// Commit & release savepoint
			connection.commit()
			connection.releaseSavepoint(savepoint)

			// Return result
			return result
		} finally {
			// Rollback
			connection.rollback(savepoint)
		}
	}

}
