package net.tassia.pancake.security;

import org.junit.Assert;
import org.junit.Test;

import java.security.MessageDigest;

public final class MessageDigestsTest {

	@Test
	public final void testSHA256Available() {
		MessageDigest md = MessageDigests.getSHA256();
		Assert.assertNotNull(md);
		Assert.assertEquals("SHA-256", md.getAlgorithm());
		Assert.assertEquals(32, md.getDigestLength());
	}



	@Test
	public final void testSHA384Available() {
		MessageDigest md = MessageDigests.getSHA384();
		Assert.assertNotNull(md);
		Assert.assertEquals("SHA-384", md.getAlgorithm());
		Assert.assertEquals(48, md.getDigestLength());
	}



	@Test
	public final void testSHA512Available() {
		MessageDigest md = MessageDigests.getSHA512();
		Assert.assertNotNull(md);
		Assert.assertEquals("SHA-512", md.getAlgorithm());
		Assert.assertEquals(64, md.getDigestLength());
	}

}
