package net.tassia.pancake.util.version

import net.tassia.pancake.ITERATIONS
import kotlin.random.Random
import kotlin.test.Test
import kotlin.test.assertEquals

class VersionTest {

	@Suppress("SpellCheckingInspection")
	private fun randomHead(): String = CharArray(40) { "0123456789abcdef".random() }.concatToString()

	private fun randomBranch(): String {
		val chars = (CharRange('0', '9') + CharRange('A', 'Z') + CharRange('a', 'z')).joinToString("") + "-_"
		return CharArray(Random.nextInt(3, 15)) { chars.random() }.concatToString()
	}

	private fun randomVersion(): Version {
		return Version(
			major = Random.nextInt(0, 127),
			minor = Random.nextInt(0, 127),
			patch = Random.nextInt(0, 255),
			build = Random.nextInt(0, 65535),
			headFull = randomHead(),
			branch = randomBranch(),
			type = VersionType.values().random()
		)
	}



	@Test
	fun testIdentifiers() {
		repeat(ITERATIONS) {
			val version = randomVersion()
			val identifier = version.toIdentifier()
			assertEquals(version, Version.parse(identifier), "Couldn't determine version identifier: $identifier")
		}
	}

}
