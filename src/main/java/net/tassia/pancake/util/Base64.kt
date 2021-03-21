package net.tassia.pancake.util

import java.nio.charset.StandardCharsets
import java.util.Base64

/**
 * Allows to encode/decode byte arrays into Base64.
 *
 * @since Pancake 1.0
 * @author Tassilo
 */
object Base64 {

	/**
	 * Encodes the given byte array into a Base64 string.
	 *
	 * @param data the bytes to encode
	 * @return the Base64 string
	 */
	fun encode(data: ByteArray): String {
		return Base64.getEncoder().encode(data).toString(StandardCharsets.US_ASCII)
	}

	/**
	 * Decodes the given Base64 string into its source byte array.
	 *
	 * @param base64 the Base64 string
	 * @return the source bytes
	 */
	fun decode(base64: String): ByteArray {
		return Base64.getDecoder().decode(base64)
	}

}
