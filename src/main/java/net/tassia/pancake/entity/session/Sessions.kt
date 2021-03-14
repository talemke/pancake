package net.tassia.pancake.entity.session

/**
 * Used to help interacting with [Session]s.
 *
 * @since Pancake 1.0
 * @author Tassilo
 */
object Sessions {

	/**
	 * Finds a session by it's [Session.token].
	 *
	 * @param token the token to search for
	 * @return the session or `null`
	 */
	fun getSessionByToken(token: String): Session? {
		return Session.find { SessionTable.token eq token }.singleOrNull()
	}

}
