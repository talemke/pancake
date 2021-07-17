package net.tassia.pancake.database.entity

import net.tassia.pancake.database.table.AccountTable
import net.tassia.pancake.entity.Account
import org.jetbrains.exposed.dao.Entity
import org.jetbrains.exposed.dao.EntityClass
import org.jetbrains.exposed.dao.id.EntityID
import java.util.*

class AccountDB(id: EntityID<UUID>) : Entity<UUID>(id), Account {

	override val accountID: UUID
		get() = id.value

	override var username by AccountTable.username
	override var password by AccountTable.password

	override var primaryGroupID by AccountTable.primaryGroupID



	override fun reload() {
		this.refresh(false)
	}

	override fun update() {
		this.refresh(true)
	}



	companion object : EntityClass<UUID, AccountDB>(AccountTable)

}
