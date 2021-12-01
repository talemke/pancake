package net.tassia.pancake.util

// TODO: Documentation
inline fun <reified T : Any> readResource(path: String): String {
	return readResource(T::class.java, path)
}

// TODO: Documentation
fun readResource(klass: Class<*>, path: String): String {
	return klass.getResourceAsStream(path)?.use {
		return@use it.readAllBytes().toString(Charsets.UTF_8)
	} ?: throw IllegalArgumentException("Resource '$path' not found.")
}
