package net.tassia.pancake.entity

import java.sql.ResultSet
import java.util.*

fun ResultSet.getUUID(index: Int): UUID {
	return UUID.fromString(getString(index))
}

fun ResultSet.getUUID(name: String): UUID {
	return UUID.fromString(getString(name))
}
