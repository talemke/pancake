package net.tassia.pancake.database.table

import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IdTable
import org.jetbrains.exposed.sql.Column
import java.util.*

object RouteTable : IdTable<UUID>("pancake_route") {

	override val id: Column<EntityID<UUID>> = uuid("RouteID").entityId()

	val matchUsername = varchar("MatchUsername", 63).index()
	val matchHostname = varchar("MatchHostname", 127).index()

	val inboxID = uuid("InboxID")

	init {
		uniqueIndex(matchUsername, matchHostname)
	}

}
