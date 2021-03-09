package net.tassia.pancake.security

import net.tassia.pancake.DIGITS
import net.tassia.pancake.ITERATIONS
import net.tassia.pancake.LETTERS_LOWER
import net.tassia.pancake.LETTERS_UPPER

import kotlin.random.Random
import kotlin.random.nextInt

import kotlin.test.Test
import kotlin.test.assertTrue

class PancakeSecurityTest {

	private val chars = LETTERS_UPPER + LETTERS_LOWER + DIGITS

	private fun randomPassword(): String {
		return String(CharArray(Random.nextInt(3..16)) { chars.random() })
	}

	private fun testPassword(password: String) {
		val hash = PancakeSecurity.hashPassword(password)
		assertTrue(PancakeSecurity.verifyPassword(hash, password), "Password verification failed for: $password")
	}

	@Test
	fun testPasswords() {
		repeat(ITERATIONS) { testPassword(randomPassword()) }
	}

}
