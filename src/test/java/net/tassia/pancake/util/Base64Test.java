package net.tassia.pancake.util;

import net.tassia.pancake.TestUtils;
import org.junit.Assert;
import org.junit.Test;

public final class Base64Test {

	@Test
	public final void testRandomized() {
		for (int i = 0; i < TestUtils.ENTROPY_ITERATIONS; i++) {
			byte[] data = TestUtils.randomData();
			byte[] data2 = Base64.decode(Base64.encode(data));
			Assert.assertArrayEquals(data, data2);
		}
	}

}
