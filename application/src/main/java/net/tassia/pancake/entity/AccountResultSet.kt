package net.tassia.pancake.entity

import net.tassia.pancake.database.Transaction
import java.sql.ResultSet
import java.util.*

internal class AccountResultSet(result: ResultSet) : Account {

	override val accountID: UUID = result.getUUID("AccountID")

	override val displayName: String = result.getString("DisplayName")

	override val inboxID: UUID = result.getUUID("InboxID")
	override val spamInboxID: UUID = result.getUUID("SpamInboxID")
	override val trashInboxID: UUID = result.getUUID("TrashInboxID")

	override fun reload(transaction: Transaction): Account {
		TODO("Not yet implemented")
	}

	override fun delete(transaction: Transaction) {
		TODO("Not yet implemented")
	}

}
