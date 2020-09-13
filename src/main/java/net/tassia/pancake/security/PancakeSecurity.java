package net.tassia.pancake.security;

import net.tassia.pancake.orm.Account;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class PancakeSecurity {

	/* Constructor */
	public PancakeSecurity() {
	}
	/* Constructor */





	/* Session IDs */
	public String generateSessionID() {
		SecureRandom random = new SecureRandom();
		return random.getAlgorithm() + "-" + random.getProvider().getName();
	}
	/* Session IDs */





	/* Passwords */
	public String hashPassword(String raw) {
		return sha512(raw, "");
	}

	public boolean verifyPassword(Account account, String password) {
		return account.getPassword() == sha512(password, "");
	}
	/* Passwords */





	/* Hashing */
	public String sha512(String text, String salt) {
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-512");
			md.update(salt.getBytes(StandardCharsets.UTF_8));
			byte[] bytes = md.digest(text.getBytes(StandardCharsets.UTF_8));
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < bytes.length; i++) {
				sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
			}
			return sb.toString();
		} catch (NoSuchAlgorithmException ex) {
			throw new Error(ex);
		}
	}
	/* Hashing */

}
