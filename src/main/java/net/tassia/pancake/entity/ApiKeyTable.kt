package net.tassia.pancake.entity

import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IdTable
import org.jetbrains.exposed.sql.Column
import java.util.*

object ApiKeyTable : IdTable<UUID>("PANCAKE_API_KEY") {

	override val id: Column<EntityID<UUID>> = uuid("ApiKeyID").entityId()

}
