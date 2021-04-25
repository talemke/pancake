package net.tassia.pancake.kotlin

fun Array<String>.concat(separator: (Int) -> String, start: Int = 0, end: Int = this.size): String {
	return this.toList().concat(separator, start, end)
}

fun Array<String>.concat(separator: String, start: Int = 0, end: Int = this.size): String {
	return this.concat({separator}, start, end)
}

fun Array<String>.smartConcat(start: Int = 0, end: Int = this.size): String {
	return this.concat({ either(it != end - 1, ", ", " and ") }, start, end)
}

fun <T : Any> Array<T>.concat(separator: (Int) -> String, transform: (T) -> String = Any::toString, start: Int = 0, end: Int = this.size): String {
	return this.map(transform).concat(separator, start, end)
}

fun <T : Any> Array<T>.concat(separator: String, transform: (T) -> String = Any::toString, start: Int = 0, end: Int = this.size): String {
	return this.map(transform).concat(separator, start, end)
}

fun <T : Any> Array<T>.smartConcat(transform: (T) -> String = Any::toString, start: Int = 0, end: Int = this.size): String {
	return this.map(transform).smartConcat(start, end)
}
