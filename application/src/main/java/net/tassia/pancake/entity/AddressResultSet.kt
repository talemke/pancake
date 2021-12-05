package net.tassia.pancake.entity

import net.tassia.pancake.database.Transaction
import java.sql.ResultSet
import java.util.*

internal class AddressResultSet(result: ResultSet) : Address {

	override val address: String = result.getString("Address")
	override val accountID: UUID = result.getUUID("AccountID")

	override fun reload(transaction: Transaction): Address {
		TODO("Not yet implemented")
	}

	override fun delete(transaction: Transaction) {
		TODO("Not yet implemented")
	}

}
