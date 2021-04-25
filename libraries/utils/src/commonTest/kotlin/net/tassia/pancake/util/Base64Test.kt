package net.tassia.pancake.util

import net.tassia.pancake.ITERATIONS
import kotlin.random.Random
import kotlin.test.Test
import kotlin.test.assertTrue

class Base64Test {

	private fun randomData(): ByteArray {
		return Random.nextBytes(Random.nextInt(1, 8))
	}

	@Test
	fun testBase64() {
		repeat(ITERATIONS) {
			val data = randomData()
			val hex = Base64.encode(data)
			val decoded = Base64.decode(hex)

			if (!data.contentEquals(decoded)) {
				assertTrue(false, "Base64 failed for $data")
			}
		}
	}

}
