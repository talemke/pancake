package net.tassia.pancake.database.entity.table

import org.jetbrains.exposed.dao.id.IdTable
import java.util.*

/**
 * The database table for [GroupPrivilege]s.
 *
 * @since Pancake 1.0
 * @author Tassilo
 */
object GroupPrivilegeTable : IdTable<UUID>("pancake_group_privilege") {

	override val id = entityId("PrivilegeID", GroupPrivilegeTable)
	override val primaryKey = PrimaryKey(id)

	val groupID = uuid("GroupID").references(GroupTable.id)
	val name = varchar("PrivilegeName", 127)
	val value = varchar("PrivilegeValue", 255)

}
