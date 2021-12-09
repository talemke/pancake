package net.tassia.pancake.server.http.transaction

/**
 * Thrown to interrupt the execution of an HTTP transaction.
 *
 * @param message the error message
 * @param cause the error cause
 *
 * @since Pancake 1.0
 */
class InterruptTransactionException(message: String? = null, cause: Throwable? = null) : Exception(message, cause)
