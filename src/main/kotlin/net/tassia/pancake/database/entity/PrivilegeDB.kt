package net.tassia.pancake.database.entity

import net.tassia.pancake.database.table.PrivilegeTable
import net.tassia.pancake.entity.Privilege
import org.jetbrains.exposed.dao.Entity
import org.jetbrains.exposed.dao.EntityClass
import org.jetbrains.exposed.dao.id.EntityID
import java.util.*

class PrivilegeDB(id: EntityID<UUID>) : Entity<UUID>(id), Privilege {

	override val privilegeID: UUID
		get() = id.value

	override var key by PrivilegeTable.key
	override var value by PrivilegeTable.value

	override var groupID by PrivilegeTable.groupID



	override fun reload() {
		this.refresh(false)
	}

	override fun update() {
		this.refresh(true)
	}



	companion object : EntityClass<UUID, PrivilegeDB>(PrivilegeTable)

}
