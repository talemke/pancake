package net.tassia.pancake.security

import java.security.MessageDigest

/**
 * Implementation of the SHA-512 hash algorithm. Uses Java's [MessageDigest] API under the hood.
 *
 * @since Pancake 1.0
 * @author Tassilo
 */
class SHA512 : HashFunction {

	private val digest = MessageDigest.getInstance("SHA-512")

	override fun hash(input: ByteArray, salt: ByteArray): ByteArray {
		digest.reset()
		digest.update(salt)
		digest.update(input)
		return digest.digest()
	}

}
