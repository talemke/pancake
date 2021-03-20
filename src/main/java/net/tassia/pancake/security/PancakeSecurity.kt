package net.tassia.pancake.security

import net.tassia.pancake.util.Base16
import java.nio.charset.StandardCharsets
import java.security.SecureRandom
import java.util.regex.Pattern

/**
 * Provides security for Pancake :D
 *
 * @since Pancake 1.0
 * @author Tassilo
 */
object PancakeSecurity {

	/**
	 * The [Pattern] for [buildHashString].
	 */
	private val hashStringPattern = Pattern.compile("^\\$([A-z0-9]+)\\$([0-9a-f]+)\\$([0-9a-f]+)$")

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
		return hashPassword(password, HashAlgorithm.SHA512, generateSalt())
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
		val matcher = hashStringPattern.matcher(hash)
		if (!matcher.matches()) return false

		val algorithm = matcher.group(1)?.let { HashAlgorithm.fromID(it) } ?: return false
		val salt = matcher.group(2)?.let { Base16.decode(it) } ?: return false
		return hash == hashPassword(password, algorithm, salt)
	}



	/**
	 * Generates a new random and secure salt for usage.
	 *
	 * @return the salt
	 */
	private fun generateSalt(): ByteArray {
		val salt = ByteArray(16)
		SecureRandom().nextBytes(salt)
		return salt
	}

	/**
	 * Hashes a password using the given algorithm.
	 *
	 * @param password the password to hash
	 * @param algorithm the algorithm to use
	 * @param salt to salt to use
	 * @return the hash string
	 */
	private fun hashPassword(password: String, algorithm: HashAlgorithm, salt: ByteArray): String {
		val function = algorithm.constructor()
		val passBytes = password.toByteArray(StandardCharsets.UTF_8)
		return buildHashString(algorithm.id, salt, function.hash(passBytes, salt))
	}

	/**
	 * Builds a hash string for the given algorithm with the given salt and hash.
	 *
	 * @param algorithm the algorithm used
	 * @param salt the salt
	 * @param hash the hash
	 * @return the hash string
	 */
	private fun buildHashString(algorithm: String, salt: ByteArray, hash: ByteArray): String {
		return "$${ algorithm }$${ Base16.encode(salt) }$${ Base16.encode(hash) }"
	}

}
