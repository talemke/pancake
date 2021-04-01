package net.tassia.pancake.util

import java.io.DataInputStream
import java.io.DataOutputStream
import java.io.InputStream
import java.io.OutputStream
import java.nio.charset.Charset


val InputStream.data: DataInputStream
	get() = DataInputStream(this)

val OutputStream.data: DataOutputStream
	get() = DataOutputStream(this)

fun InputStream.readBoolean() = read() != 0
fun InputStream.readByte() = read().toByte()
fun InputStream.readShort() = data.readShort()
fun InputStream.readInt() = data.readInt()
fun InputStream.readLong() = data.readLong()
fun InputStream.readFloat() = data.readFloat()
fun InputStream.readDouble() = data.readDouble()

fun OutputStream.writeBoolean(value: Boolean) = write(if (value) 1 else 0)
fun OutputStream.writeByte(value: Int) = write(value)
fun OutputStream.writeShort(value: Int) = data.writeShort(value)
fun OutputStream.writeInt(value: Int) = data.writeInt(value)
fun OutputStream.writeLong(value: Long) = data.writeLong(value)
fun OutputStream.writeFloat(value: Float) = data.writeFloat(value)
fun OutputStream.writeDouble(value: Double) = data.writeDouble(value)



fun InputStream.readBytes1() = ByteArray(readByte().toInt()).also { read(it) }
fun InputStream.readBytes2() = ByteArray(readShort().toInt()).also { read(it) }
fun InputStream.readBytes4() = ByteArray(readInt()).also { read(it) }

fun OutputStream.writeBytes1(bytes: ByteArray) {
	require(bytes.size < 256)
	writeByte(bytes.size); write(bytes)
}

fun OutputStream.writeBytes2(bytes: ByteArray) {
	require(bytes.size < 65536)
	writeShort(bytes.size); write(bytes)
}

fun OutputStream.writeBytes4(bytes: ByteArray) {
	writeInt(bytes.size); write(bytes)
}



fun InputStream.readString1(charset: Charset = Charsets.UTF_8) = String(readBytes1(), charset)
fun InputStream.readString2(charset: Charset = Charsets.UTF_8) = String(readBytes2(), charset)
fun InputStream.readString4(charset: Charset = Charsets.UTF_8) = String(readBytes4(), charset)

fun OutputStream.writeString1(value: String, charset: Charset = Charsets.UTF_8) {
	writeBytes1(value.toByteArray(charset))
}

fun OutputStream.writeString2(value: String, charset: Charset = Charsets.UTF_8) {
	writeBytes2(value.toByteArray(charset))
}

fun OutputStream.writeString4(value: String, charset: Charset = Charsets.UTF_8) {
	writeBytes4(value.toByteArray(charset))
}
