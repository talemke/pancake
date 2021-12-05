package net.tassia.pancake.entity

import net.tassia.pancake.database.Transaction
import java.sql.ResultSet
import java.util.*

class MailTransactionResultSet(result: ResultSet) : MailTransaction {

	override val transactionID: UUID = result.getUUID("TransactionID")

	override fun reload(transaction: Transaction): MailTransaction {
		TODO("Not yet implemented")
	}

	override fun delete(transaction: Transaction) {
		TODO("Not yet implemented")
	}

}
