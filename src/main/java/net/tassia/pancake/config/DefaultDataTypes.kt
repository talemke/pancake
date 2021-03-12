package net.tassia.pancake.config

import java.util.*


/**
 * The [ConfigDataType] for [Boolean]s.
 *
 * @since Pancake 1.0
 * @author Tassilo
 */
val BooleanDataType = ConfigDataType(
	Boolean::class, Boolean::toString, String::toBoolean
)


/**
 * The [ConfigDataType] for [Int]s.
 *
 * @since Pancake 1.0
 * @author Tassilo
 */
val IntDataType = ConfigDataType(
	Int::class, Int::toString, String::toInt
)


/**
 * The [ConfigDataType] for [Long]s.
 *
 * @since Pancake 1.0
 * @author Tassilo
 */
val LongDataType = ConfigDataType(
	Long::class, Long::toString, String::toLong
)


/**
 * The [ConfigDataType] for [Float]s.
 *
 * @since Pancake 1.0
 * @author Tassilo
 */
val FloatDataType = ConfigDataType(
	Float::class, Float::toString, String::toFloat
)


/**
 * The [ConfigDataType] for [Double]s.
 *
 * @since Pancake 1.0
 * @author Tassilo
 */
val DoubleDataType = ConfigDataType(
	Double::class, Double::toString, String::toDouble
)


/**
 * The [ConfigDataType] for [String]s.
 *
 * @since Pancake 1.0
 * @author Tassilo
 */
val StringDataType = ConfigDataType(
	String::class, {it}, {it}
)


/**
 * The [ConfigDataType] for [UUID]s.
 *
 * @since Pancake 1.0
 * @author Tassilo
 */
val UUIDDataType = ConfigDataType(
	UUID::class, UUID::toString, UUID::fromString
)
