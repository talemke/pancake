package net.tassia.pancake;

import java.util.Random;

public final class TestUtils {

	public static final int ENTROPY_ITERATIONS = 4096 * 8;
	private static final Random RANDOM = new Random();

	public static byte[] randomData() {
		byte[] buffer = new byte[RANDOM.nextInt(512)];
		RANDOM.nextBytes(buffer);
		return buffer;
	}

	public static String changeString(String src) {
		if (src.length() == 0) return "A";
		return src.substring(1);
	}

}
