package net.tassia.pancake.security

/**
 * Defines how to hash passwords (actually, how to hash any data).
 *
 * @since Pancake 1.0
 * @author Tassilo
 */
enum class HashAlgorithm(val id: String, val constructor: () -> HashFunction) {

	// MD5("1"), TODO

	// BLOWFISH("2y"), TODO

	// SHA256("5"), TODO

	/**
	 * The SHA-512 algorithm.
	 *
	 * @see net.tassia.pancake.security.SHA512
	 */
	SHA512("6", ::SHA512);



	companion object {

		/**
		 * Finds the [HashAlgorithm] with the given ID.
		 *
		 * @param id the ID
		 * @return the algorithm, or `null`
		 */
		fun fromID(id: String): HashAlgorithm? {
			return values().firstOrNull { it.id == id }
		}

	}

}
