package net.tassia.pancake.database.table

import net.tassia.pancake.entity.InboxType
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IdTable
import org.jetbrains.exposed.sql.Column
import java.util.*

object InboxTable : IdTable<UUID>("pancake_inbox") {

	override val id: Column<EntityID<UUID>> = uuid("InboxID").entityId()

	val nameID = varchar("InboxNameID", 63)
	val name = varchar("InboxName", 63)

	val type = enumeration("InboxType", InboxType::class)
	val sortingOrder = integer("SortingOrder")

	val accountID = uuid("AccountID")

	init {
		uniqueIndex(accountID, nameID)
	}

}
