package net.tassia.pancake.logging.publisher

import net.tassia.pancake.logging.LogRecord
import net.tassia.pancake.plugin.Plugin
import net.tassia.pancake.kotlin.writeByte
import net.tassia.pancake.kotlin.writeLong
import net.tassia.pancake.kotlin.writeString1
import net.tassia.pancake.kotlin.writeString2
import java.io.OutputStream

/**
 * This publisher writes to an output stream in binary data format.
 *
 * @since Pancake 1.0
 * @author Tassilo
 */
class BinaryStreamPublisher(

	val stream: OutputStream

) : LogPublisher<Plugin> {

	override fun publish(record: LogRecord<Plugin>) {
		stream.writeLong(record.timestamp)
		stream.writeString1(record.data?.info?.id ?: "")
		stream.writeByte(record.level.ordinal)
		stream.writeString2(record.message ?: "")

		// TODO: Handle exception
	}

	override fun flush() = stream.flush()
	override fun close() = stream.close()

}
