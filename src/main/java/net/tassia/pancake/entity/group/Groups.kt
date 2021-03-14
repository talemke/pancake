package net.tassia.pancake.entity.group

import org.jetbrains.exposed.sql.and
import org.jetbrains.exposed.sql.deleteWhere
import org.jetbrains.exposed.sql.insert
import java.util.*

/**
 * Used to interact with [Group]s and [GroupPrivilege]s.
 *
 * @since Pancake 1.0
 * @author Tassilo
 */
object Groups {

	fun getPrivilege(group: Group, name: String): GroupPrivilege? {
		return getPrivilege(group.id.value, name)
	}

	fun getPrivilege(groupID: UUID, name: String): GroupPrivilege? {
		return GroupPrivilege.find {
			(GroupPrivilegeTable.group eq groupID) and (GroupPrivilegeTable.name eq name)
		}.singleOrNull()
	}

	fun getPrivilegeString(group: Group, name: String): String? {
		return getPrivilege(group, name)?.value
	}

	fun getPrivilegeString(groupID: UUID, name: String): String? {
		return getPrivilege(groupID, name)?.value
	}



	fun setPrivilege(group: Group, name: String, value: String) {
		setPrivilege(group.id.value, name, value)
	}

	fun setPrivilege(groupID: UUID, name: String, value: String) {
		unsetPrivilege(groupID, name)
		GroupPrivilegeTable.insert {
			it[id] = UUID.randomUUID()
			it[group] = groupID
			it[GroupPrivilegeTable.name] = name
			it[GroupPrivilegeTable.value] = value
		}
	}



	fun unsetPrivilege(group: Group, name: String) {
		unsetPrivilege(group.id.value, name)
	}

	fun unsetPrivilege(groupID: UUID, name: String) {
		GroupPrivilegeTable.deleteWhere {
			(GroupPrivilegeTable.group eq groupID) and (GroupPrivilegeTable.name eq name)
		}
	}

}
