package net.tassia.pancake.security;

import net.tassia.pancake.Pancake;
import net.tassia.pancake.orm.Account;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

public class PancakeSecurity {
	private final Pancake pancake;

	/* Constructor */
	public PancakeSecurity(Pancake pancake) {
		this.pancake = pancake;
	}
	/* Constructor */





	/* Session IDs */
	public String generateSessionID() {
		SecureRandom random = new SecureRandom();
		byte[] buf = new byte[128];
		random.nextBytes(buf);
		return Base64.getEncoder().encodeToString(buf);
	}
	/* Session IDs */





	/* Authentication */
	public Account attemptLogin(String username, String password) {
		// Find account
		Account acc = pancake.getAccountByUsername(username);
		if (acc == null) return null;

		// Verify password
		if (verifyPassword(acc, password)) {
			return acc;
		} else {
			return null;
		}
	}
	/* Authentication */





	/* Passwords */
	public String hashPassword(String raw) {
		return sha512(raw, "");
	}

	public boolean verifyPassword(Account account, String password) {
		return account.getPassword().equals(sha512(password, ""));
	}
	/* Passwords */





	/* Hashing */
	protected String hash(String text, String salt, String algorithm) {
		try {
			MessageDigest md = MessageDigest.getInstance(algorithm);
			md.update(salt.getBytes(StandardCharsets.UTF_8));
			byte[] bytes = md.digest(text.getBytes(StandardCharsets.UTF_8));
			StringBuilder sb = new StringBuilder();
			for (byte aByte : bytes) {
				sb.append(Integer.toString((aByte & 0xff) + 0x100, 16).substring(1));
			}
			return sb.toString();
		} catch (NoSuchAlgorithmException ex) {
			throw new Error(ex);
		}
	}

	public String sha512(String text, String salt) {
		return hash(text, salt, "SHA-512");
	}

	public String md5(String text, String salt) {
		return hash(text, salt, "MD5");
	}
	/* Hashing */

}
