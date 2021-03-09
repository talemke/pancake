package net.tassia.pancake

/**
 * Allows to encode/decode byte arrays into Base16 (hexadecimal).
 *
 * @since Pancake 1.0
 * @author Tassilo
 */
object Base16 {

	/**
	 * The table used for encoding.
	 */
	@Suppress("SpellCheckingInspection")
	private val encodeTable: CharArray = "0123456789abcdef".toCharArray()

	/**
	 * The table used for decoding.
	 */
	private val decodeTable: Map<Char, Int> = mapOf(
		Pair('0', 0x0), Pair('1', 0x1), Pair('2', 0x2), Pair('3', 0x3),
		Pair('4', 0x4), Pair('5', 0x5), Pair('6', 0x6), Pair('7', 0x7),
		Pair('8', 0x8), Pair('9', 0x9), Pair('a', 0xA), Pair('b', 0xB),
		Pair('c', 0xC), Pair('d', 0xD), Pair('e', 0xE), Pair('f', 0xF)
	)



	/**
	 * Encodes the given byte array into a Base16 (hexadecimal) string.
	 *
	 * @param data the bytes to encode
	 * @return the Base16 string
	 */
	fun encode(data: ByteArray): String {
		return String(CharArray(data.size * 2) {
			if (it % 2 == 0) {
				val value = data[it / 2].toInt() and 0xFF
				return@CharArray encodeTable[value shr 4 and 0xF]
			} else {
				val value = data[(it - 1) / 2].toInt() and 0xFF
				return@CharArray encodeTable[value and 0xF]
			}
		})
	}

	/**
	 * Decodes the given Base16 string into its source byte array.
	 *
	 * @param base16 the Base16 string
	 * @return the source bytes
	 */
	fun decode(base16: String): ByteArray {
		if (base16.length % 2 != 0) throw IllegalArgumentException("$base16 is not a valid Base16 string.")
		return ByteArray(base16.length / 2) {
			val a = decodeTable[base16[it * 2]] ?: throw IllegalArgumentException("$base16 is not a valid Base16 string.")
			val b = decodeTable[base16[it * 2 + 1]] ?: throw IllegalArgumentException("$base16 is not a valid Base16 string.")
			return@ByteArray (((a shl 4 and 0xF0) or (b and 0xF)) and 0xFF).toByte()
		}
	}

}
