package net.tassia.pancake.database

import javax.sql.DataSource

class StandardDatabase(private val source: DataSource) : Database() {

	suspend fun <T> createTransaction(block: suspend (Transaction) -> T): T {
		return source.connection!!.use { connection ->

			// Disable auto-commit
			connection.autoCommit = false

			// Create & run transaction
			val transaction = StandardTransaction(connection)
			return@use transaction.executeBlock(block)

		}
	}

}
