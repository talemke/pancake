package net.tassia.pancake.util

/**
 * Allows to encode/decode byte arrays into Base64.
 *
 * @since Pancake 1.0
 * @author Tassilo
 */
expect object Base64 {

	/**
	 * Encodes the given byte array into a Base64 string.
	 *
	 * @param data the bytes to encode
	 * @return the Base64 string
	 */
	fun encode(data: ByteArray): String

	/**
	 * Decodes the given Base64 string into its source byte array.
	 *
	 * @param base64 the Base64 string
	 * @return the source bytes
	 */
	fun decode(base64: String): ByteArray

}
