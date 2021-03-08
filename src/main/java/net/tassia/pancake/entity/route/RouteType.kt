package net.tassia.pancake.entity.route

/**
 * Defines the behavior of a route.
 *
 * @since Pancake 1.0
 * @author Tassilo
 */
enum class RouteType {

	/**
	 * The mail target must match.
	 */
	MATCH,

	/**
	 * The mail target must be equal.
	 */
	EQUAL,

	/**
	 * The mail target must not match.
	 */
	NOT_MATCH,

	/**
	 * The mail target must not be equal.
	 */
	NOT_EQUAL;

}
