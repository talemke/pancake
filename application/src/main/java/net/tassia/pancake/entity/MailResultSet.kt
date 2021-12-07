package net.tassia.pancake.entity

import net.tassia.pancake.database.Transaction
import java.sql.ResultSet
import java.util.*

internal class MailResultSet(result: ResultSet) : Mail {

	override val mailID: UUID = result.getUUID("MailID")
	override val transactionID: UUID = result.getUUID("TransactionID")
	override val renderedHTML: String? = result.getString("RenderedHTML")

	override fun reload(transaction: Transaction): Mail {
		TODO("Not yet implemented")
	}

	override fun delete(transaction: Transaction) {
		TODO("Not yet implemented")
	}

}
