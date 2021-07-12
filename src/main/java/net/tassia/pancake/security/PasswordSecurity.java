package net.tassia.pancake.security;

import net.tassia.pancake.util.Base64;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

/**
 * Handles password related security.
 *
 * @since Pancake 1.0
 * @author Tassilo
 */
public final class PasswordSecurity {

	// TODO: Add support for salts, switch to bcrypt and add customizable costs

	// TODO: Documentation
	public static String hashPassword(String password) {
		byte[] pwBytes = password.getBytes(StandardCharsets.UTF_8);

		MessageDigest sha512 = MessageDigests.getSHA512();
		sha512.reset();
		sha512.update(pwBytes, 0, pwBytes.length);

		byte[] hash = sha512.digest();
		return Base64.encode(hash);
	}



	// TODO: Documentation
	public static boolean verifyPassword(String hash, String password) {
		String testHash = hashPassword(password);
		return hash.equals(testHash);
	}



	/**
	 * Static class.
	 */
	private PasswordSecurity() {
	}

}
