package net.tassia.pancake.entity

import net.tassia.pancake.database.Transaction
import java.sql.ResultSet
import java.util.*

internal class NodeResultSet(result: ResultSet) : Node {

	override val nodeID: UUID = result.getUUID("NodeID")

	override fun reload(transaction: Transaction): Node {
		TODO("Not yet implemented")
	}

	override fun delete(transaction: Transaction) {
		TODO("Not yet implemented")
	}

}
