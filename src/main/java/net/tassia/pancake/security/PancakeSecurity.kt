package net.tassia.pancake.security

/**
 * Provides security for Pancake :D
 *
 * @since Pancake 1.0
 * @author Tassilo
 */
object PancakeSecurity {

	/**
	 * Hashes a password.
	 *
	 * Note: This function does not use a specific hash algorithm. The used hash algorithm will be included
	 * 		 in the generated hash, so this function can seemingly upgrade to a better hash algorithm without
	 * 		 breaking old passwords.
	 *
	 * @param password the password to hash
	 * @return the generated hash
	 */
	fun hashPassword(password: String): String {
		TODO()
	}

	/**
	 * Verifies a password.
	 *
	 * Note: This function determines the hash algorithm to try from the hash string itself.
	 * 		 See [hashPassword] for more information.
	 *
	 * @param hash the stored hash
	 * @param password the password to check
	 * @return whether they match
	 */
	fun verifyPassword(hash: String, password: String): Boolean {
		TODO()
	}

}
