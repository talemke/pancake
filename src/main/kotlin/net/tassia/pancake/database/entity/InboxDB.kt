package net.tassia.pancake.database.entity

import net.tassia.pancake.database.table.InboxTable
import net.tassia.pancake.entity.Inbox
import org.jetbrains.exposed.dao.Entity
import org.jetbrains.exposed.dao.EntityClass
import org.jetbrains.exposed.dao.id.EntityID
import java.util.*

class InboxDB(id: EntityID<UUID>) : Entity<UUID>(id), Inbox {

	override val inboxID: UUID
		get() = id.value

	override var nameID by InboxTable.nameID
	override var name by InboxTable.name

	override var type by InboxTable.type
	override var sortingOrder by InboxTable.sortingOrder

	override var accountID by InboxTable.accountID



	override fun reload() {
		this.refresh(false)
	}

	override fun update() {
		this.refresh(true)
	}



	companion object : EntityClass<UUID, InboxDB>(InboxTable)

}
