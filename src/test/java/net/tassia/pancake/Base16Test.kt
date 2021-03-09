package net.tassia.pancake

import kotlin.random.Random
import kotlin.test.Test
import kotlin.test.assertTrue

class Base16Test {

	private fun randomData(): ByteArray {
		return Random.nextBytes(Random.nextInt(1, 8))
	}

	@Test
	fun testBase16() {
		repeat(ITERATIONS) {
			val data = randomData()
			val hex = Base16.encode(data)
			val decoded = Base16.decode(hex)

			if (!data.contentEquals(decoded)) {
				assertTrue(false, "Base16 failed for $data")
			}
		}
	}

}
