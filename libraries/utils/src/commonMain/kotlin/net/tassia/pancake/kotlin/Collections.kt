package net.tassia.pancake.kotlin

fun Collection<String>.concat(separator: (Int) -> String, start: Int = 0, end: Int = this.size): String {
	val builder = StringBuilder()

	for ((index, element) in this.withIndex()) {
		if (index in start until end) {
			if (index != start) {
				builder.append(separator(index))
			}
			builder.append(element)
		}
	}

	return builder.toString()
}

fun Collection<String>.concat(separator: String, start: Int = 0, end: Int = this.size): String {
	return this.concat({separator}, start, end)
}

fun Collection<String>.smartConcat(start: Int = 0, end: Int = this.size): String {
	return this.concat({ either(it != end - 1, ", ", " and ") }, start, end)
}

fun <T : Any> Collection<T>.concat(separator: (Int) -> String, transform: (T) -> String = Any::toString, start: Int = 0, end: Int = this.size): String {
	return this.map(transform).concat(separator, start, end)
}

fun <T : Any> Collection<T>.concat(separator: String, transform: (T) -> String = Any::toString, start: Int = 0, end: Int = this.size): String {
	return this.map(transform).concat(separator, start, end)
}

fun <T : Any> Collection<T>.smartConcat(transform: (T) -> String = Any::toString, start: Int = 0, end: Int = this.size): String {
	return this.map(transform).smartConcat(start, end)
}
