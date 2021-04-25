package net.tassia.pancake.kotlin

import java.io.DataInputStream
import java.io.InputStream
import java.nio.charset.Charset

val InputStream.data: DataInputStream
	get() = DataInputStream(this)

fun InputStream.readBoolean() = read() != 0
fun InputStream.readByte() = read().toByte()
fun InputStream.readShort() = data.readShort()
fun InputStream.readInt() = data.readInt()
fun InputStream.readLong() = data.readLong()
fun InputStream.readFloat() = data.readFloat()
fun InputStream.readDouble() = data.readDouble()

fun InputStream.readBytes1() = ByteArray(readByte().toInt()).also { read(it) }
fun InputStream.readBytes2() = ByteArray(readShort().toInt()).also { read(it) }
fun InputStream.readBytes4() = ByteArray(readInt()).also { read(it) }

fun InputStream.readString1(charset: Charset = Charsets.UTF_8) = String(readBytes1(), charset)
fun InputStream.readString2(charset: Charset = Charsets.UTF_8) = String(readBytes2(), charset)
fun InputStream.readString4(charset: Charset = Charsets.UTF_8) = String(readBytes4(), charset)
