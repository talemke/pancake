package net.tassia.pancake.logging.publisher

import net.tassia.pancake.logging.LogRecord
import net.tassia.pancake.util.writeByte
import net.tassia.pancake.util.writeLong
import net.tassia.pancake.util.writeString1
import net.tassia.pancake.util.writeString2
import java.io.OutputStream

/**
 * This publisher writes to an output stream in binary data format.
 *
 * @since Pancake 1.0
 * @author Tassilo
 */
class BinaryStreamPublisher(

	val stream: OutputStream

) : LogPublisher {

	override fun publish(record: LogRecord) {
		stream.writeLong(record.timestamp)
		stream.writeString1(record.plugin?.info?.id ?: "")
		stream.writeByte(record.level.ordinal)
		stream.writeString2(record.message ?: "")

		// TODO: Handle exception
	}

	override fun flush() = stream.flush()
	override fun close() = stream.close()

}
