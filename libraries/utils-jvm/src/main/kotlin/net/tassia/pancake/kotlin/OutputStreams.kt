package net.tassia.pancake.kotlin

import java.io.DataOutputStream
import java.io.OutputStream
import java.nio.charset.Charset

val OutputStream.data: DataOutputStream
	get() = DataOutputStream(this)

fun OutputStream.writeBoolean(value: Boolean) = write(if (value) 1 else 0)
fun OutputStream.writeByte(value: Int) = write(value)
fun OutputStream.writeShort(value: Int) = data.writeShort(value)
fun OutputStream.writeInt(value: Int) = data.writeInt(value)
fun OutputStream.writeLong(value: Long) = data.writeLong(value)
fun OutputStream.writeFloat(value: Float) = data.writeFloat(value)
fun OutputStream.writeDouble(value: Double) = data.writeDouble(value)

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

fun OutputStream.writeString1(value: String, charset: Charset = Charsets.UTF_8) {
	writeBytes1(value.toByteArray(charset))
}

fun OutputStream.writeString2(value: String, charset: Charset = Charsets.UTF_8) {
	writeBytes2(value.toByteArray(charset))
}

fun OutputStream.writeString4(value: String, charset: Charset = Charsets.UTF_8) {
	writeBytes4(value.toByteArray(charset))
}
