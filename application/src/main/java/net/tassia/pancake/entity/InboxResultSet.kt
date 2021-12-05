package net.tassia.pancake.entity

import net.tassia.pancake.database.Transaction
import java.sql.ResultSet
import java.util.*

class InboxResultSet(result: ResultSet) : Inbox {

	override val inboxID: UUID = result.getUUID("InboxID")

	override val customName: String? = result.getString("CustomName")
	override val defaultName: String? = result.getString("DefaultName")

	override fun reload(transaction: Transaction): Inbox {
		TODO("Not yet implemented")
	}

	override fun delete(transaction: Transaction) {
		TODO("Not yet implemented")
	}

}
