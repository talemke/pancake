package net.tassia.pancake.kotlin

import java.io.EOFException
import java.io.Reader
import java.util.function.Predicate

/**
 * Reads all characters until the predicate returns `true`.
 *
 * @param predicate the predicate
 * @param builder the builder
 * @return the builder
 *
 * @since Pancake 1.0
 * @author Tassilo
 */
@Throws(EOFException::class)
fun Reader.readUntil(predicate: Predicate<Char>, builder: StringBuilder = StringBuilder()): StringBuilder {
	while (true) {
		val next = read()
		if (next == -1) throw EOFException()
		val nextChar = next.toChar()
		if (predicate.test(nextChar)) break
		builder.append(nextChar)
	}
	return builder
}

/**
 * Reads all characters until the predicate returns `true` or EOF is reached.
 *
 * @param predicate the predicate
 * @param builder the builder
 * @return the builder
 *
 * @since Pancake 1.0
 * @author Tassilo
 */
fun Reader.readUntilOrEOF(predicate: Predicate<Char>, builder: StringBuilder = StringBuilder()): StringBuilder {
	while (true) {
		val next = read()
		if (next == -1) break
		val nextChar = next.toChar()
		if (predicate.test(nextChar)) break
		builder.append(nextChar)
	}
	return builder
}
