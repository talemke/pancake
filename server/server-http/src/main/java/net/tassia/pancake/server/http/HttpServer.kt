package net.tassia.pancake.server.http

import io.ktor.server.engine.*
import net.tassia.pancake.server.Server

/**
 * The HTTP server.
 *
 * @since Pancake 1.0
 */
class HttpServer(private val engine: ApplicationEngine) : Server() {

	override fun start() {
		engine.start(wait = false)
		this.isRunning = true
	}

	override fun stop() {
		this.isRunning = false
		engine.stop(2500L, 2500L)
	}

	override var isRunning: Boolean = false; private set

}
