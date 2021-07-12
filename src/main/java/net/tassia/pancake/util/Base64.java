package net.tassia.pancake.util;

/**
 * Utility class for Base64 encoding/decoding.
 *
 * @since Pancake 1.0
 * @author Tassilo
 */
public final class Base64 {

	/**
	 * Encodes the data using Base64.
	 *
	 * @param data the data to encode
	 * @return a base64 string
	 */
	public static String encode(byte[] data) {
		return java.util.Base64.getEncoder().encodeToString(data);
	}

	/**
	 * Decodes the Base64 string.
	 *
	 * @param base64 the base64 string
	 * @return the decoded data
	 */
	public static byte[] decode(String base64) {
		return java.util.Base64.getDecoder().decode(base64);
	}



	/**
	 * Static class.
	 */
	private Base64() {
	}

}
