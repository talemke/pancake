package net.tassia.pancake.security.hash

import java.security.MessageDigest

/**
 * Implementation of the MD5 hash algorithm. Uses Java's [MessageDigest] API under the hood.
 *
 * @since Pancake 1.0
 * @author Tassilo
 */
class MD5 : HashFunction {

	private val digest = MessageDigest.getInstance("MD5")

	override fun hash(input: ByteArray, salt: ByteArray): ByteArray {
		digest.reset()
		digest.update(salt)
		digest.update(input)
		return digest.digest()
	}

}
