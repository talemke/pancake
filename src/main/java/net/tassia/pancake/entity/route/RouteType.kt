package net.tassia.pancake.entity.route

/**
 * Defines the behavior of a route. The correct route is determined is by going through all routes and seeing
 * which one matches first. The routes are grouped by their types and then checked in the following order:
 *
 * 1. [EQUAL] routes
 * 2. [NOT_EQUAL] routes
 * 3. [MATCH] routes
 * 4. [NOT_MATCH] routes
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
