package net.tassia.pancake.entity.route

import org.jetbrains.exposed.sql.and
import org.jetbrains.exposed.sql.not

/**
 * Used to help interacting with [Route]s.
 *
 * @since Pancake 1.0
 * @author Tassilo
 */
object Routes {

	/**
	 * Finds the first [Route] with a [RouteType.EQUAL] type and a [Route.value] equal to `address`.
	 *
	 * @return the route or `null`
	 */
	fun findFirstEqual(address: String): Route? {
		return Route.find {
			(RouteTable.type eq RouteType.EQUAL) and (RouteTable.value eq address)
		}.singleOrNull()
	}

	/**
	 * Finds the first [Route] with a [RouteType.NOT_EQUAL] type and a [Route.value] not equal to `address`.
	 *
	 * @return the route or `null`
	 */
	fun findFirstNotEqual(address: String): Route? {
		return Route.find {
			(RouteTable.type eq RouteType.NOT_EQUAL) and (RouteTable.value neq address)
		}.singleOrNull()
	}

	/**
	 * Finds the first [Route] with a [RouteType.MATCH] type and a [Route.value] matching `address`.
	 *
	 * @return the route or `null`
	 */
	fun findFirstMatch(address: String): Route? {
		return Route.find {
			(RouteTable.type eq RouteType.MATCH) and (RouteTable.value match address)
		}.singleOrNull()
	}

	/**
	 * Finds the first [Route] with a [RouteType.NOT_MATCH] type and a [Route.value] not matching `address`.
	 *
	 * @return the route or `null`
	 */
	fun findFirstNotMatch(address: String): Route? {
		return Route.find {
			(RouteTable.type eq RouteType.NOT_MATCH) and not(RouteTable.value match address)
		}.singleOrNull()
	}

}
