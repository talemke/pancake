package net.tassia.pancake.entity

import net.tassia.pancake.util.UUID

interface Route {

	val routeID: UUID

	var value: String
	var type: RouteType

	var targetID: UUID

}
