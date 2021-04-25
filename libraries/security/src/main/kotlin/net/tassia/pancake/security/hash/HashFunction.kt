package net.tassia.pancake.security.hash

/**
 * Abstraction for password hash functions.
 *
 * `INPUT + SALT -> OUTPUT`
 *
 * @since Pancake 1.0
 * @author Tassilo
 */
fun interface HashFunction {

	/**
	 * Hashes the input data with the given salt.
	 *
	 * @param input the input data
	 * @param salt the salt
	 * @return the hash
	 */
	fun hash(input: ByteArray, salt: ByteArray): ByteArray

}
