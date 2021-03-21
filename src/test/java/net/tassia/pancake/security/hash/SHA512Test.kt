package net.tassia.pancake.security.hash

import net.tassia.pancake.ITERATIONS
import java.util.*
import kotlin.random.Random
import kotlin.test.Test

class SHA512Test {

	private val hash: HashFunction = SHA512()

	private fun randomSalt(): ByteArray = Random.nextBytes(Random.nextInt(128))
	private fun randomData(): ByteArray = Random.nextBytes(Random.nextInt(4096))

	private fun testRandomData() {
		val salt = randomSalt()
		val data = randomData()

		val hash1 = hash.hash(data, salt)
		val hash2 = hash.hash(data, salt)

		assert(hash1.contentEquals(hash2))
	}

	@Test
	fun testRandoms() {
		repeat(ITERATIONS) {
			testRandomData()
		}
	}

}
