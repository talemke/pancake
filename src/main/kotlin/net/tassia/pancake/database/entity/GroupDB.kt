package net.tassia.pancake.database.entity

import net.tassia.pancake.database.table.GroupTable
import net.tassia.pancake.entity.Group
import org.jetbrains.exposed.dao.Entity
import org.jetbrains.exposed.dao.EntityClass
import org.jetbrains.exposed.dao.id.EntityID
import java.util.*

class GroupDB(id: EntityID<UUID>) : Entity<UUID>(id), Group {

	override val groupID: UUID
		get() = id.value

	override var name by GroupTable.name
	override var description by GroupTable.description



	override fun reload() {
		this.refresh(false)
	}

	override fun update() {
		this.refresh(true)
	}



	companion object : EntityClass<UUID, GroupDB>(GroupTable)

}
