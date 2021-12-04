package net.tassia.pancake.entity

import java.util.*

interface Node : DatabaseEntity<Node> {

	val nodeID: UUID

}
