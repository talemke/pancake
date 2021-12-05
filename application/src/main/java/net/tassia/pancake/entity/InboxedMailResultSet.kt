package net.tassia.pancake.entity

import net.tassia.pancake.database.Transaction
import java.sql.ResultSet
import java.util.*

internal class InboxedMailResultSet(result: ResultSet) : InboxedMail {

	override val mailID: UUID = result.getUUID("MailID")
	override val inboxID: UUID = result.getUUID("InboxID")

	override val isArchived: Boolean = result.getBoolean("IsArchived")
	override val isImportant: Boolean = result.getBoolean("IsImportant")
	override val isMarked: Boolean = result.getBoolean("IsMarked")
	override val isSpam: Boolean = result.getBoolean("IsSpam")

	override val deletionDate: Long = result.getLong("DeletionDate")

	override fun reload(transaction: Transaction): InboxedMail {
		TODO("Not yet implemented")
	}

	override fun delete(transaction: Transaction) {
		TODO("Not yet implemented")
	}

}
