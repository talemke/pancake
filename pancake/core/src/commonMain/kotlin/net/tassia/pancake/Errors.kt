package net.tassia.pancake

open class PancakeException(message: String? = null, cause: Throwable? = null) : Exception(message, cause)

open class PancakeBootException(message: String? = null, cause: Throwable? = null) : PancakeException(message, cause)
