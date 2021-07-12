package net.tassia.pancake.security;

import net.tassia.pancake.TestUtils;
import org.junit.Assert;
import org.junit.Test;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

public final class PasswordSecurityTest {

	@Test
	public final void testRandomized() {
		for (int i = 0; i < TestUtils.ENTROPY_ITERATIONS; i++) {
			String pw = new String(TestUtils.randomData(), StandardCharsets.UTF_8);
			String hash = PasswordSecurity.hashPassword(pw);

			String pw2 = TestUtils.changeString(pw);
			String hash2 = TestUtils.changeString(hash);

			Assert.assertTrue(PasswordSecurity.verifyPassword(hash, pw));
			Assert.assertFalse(PasswordSecurity.verifyPassword(hash2, pw));
			Assert.assertFalse(PasswordSecurity.verifyPassword(hash, pw2));
			Assert.assertFalse(PasswordSecurity.verifyPassword(hash2, pw2));
		}
	}

}
