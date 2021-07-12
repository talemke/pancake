package net.tassia.pancake.security;

import net.tassia.pancake.PancakeIOException;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Utility class to safely access {@link MessageDigest}s.
 *
 * @since Pancake 1.0
 * @author Tassilo
 */
public final class MessageDigests {

	/**
	 * Returns the <code>SHA-256</code> message digest.
	 *
	 * @return the SHA-256 message digest
	 */
	public static MessageDigest getSHA256() {
		try {
			return MessageDigest.getInstance("SHA-256");
		} catch (NoSuchAlgorithmException ex) {
			throw new PancakeIOException("SHA-256 is not supported on your platform.", ex);
		}
	}

	/**
	 * Returns the <code>SHA-384</code> message digest.
	 *
	 * @return the SHA-384 message digest
	 */
	public static MessageDigest getSHA384() {
		try {
			return MessageDigest.getInstance("SHA-384");
		} catch (NoSuchAlgorithmException ex) {
			throw new PancakeIOException("SHA-384 is not supported on your platform.", ex);
		}
	}

	/**
	 * Returns the <code>SHA-512</code> message digest.
	 *
	 * @return the SHA-512 message digest
	 */
	public static MessageDigest getSHA512() {
		try {
			return MessageDigest.getInstance("SHA-512");
		} catch (NoSuchAlgorithmException ex) {
			throw new PancakeIOException("SHA-512 is not supported on your platform (wtf?)", ex);
		}
	}



	/**
	 * Static class.
	 */
	private MessageDigests() {
	}

}
