package net.tassia.pancake.entity

/**
 * Defines the status of a [Session].
 *
 * @since Pancake 1.0
 * @author Tassilo
 */
enum class SessionStatus {

	/**
	 * The session is currently active.
	 */
	ACTIVE,

	/**
	 * The session has expired and can no-longer be used.
	 */
	EXPIRED,

	/**
	 * The session has manually been invalidated by the user.
	 */
	REVOKED_BY_USER,

	/**
	 * The session has manually been invalidated by an administrator.
	 */
	REVOKED_BY_ADMIN;

}
