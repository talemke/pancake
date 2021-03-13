package net.tassia.pancake.http

import io.ktor.application.*
import io.ktor.routing.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import net.tassia.pancake.Pancake
import net.tassia.pancake.http.event.HttpRegisterRoutesEvent
import net.tassia.pancake.http.feature.Logging

/**
 * The base class for the HTTP sub-system of Pancake.
 *
 * @param pancake the [Pancake] instance
 *
 * @since Pancake 1.0
 * @author Tassilo
 */
class PancakeHttp(private val pancake: Pancake) {

	/**
	 * The underlying HTTP engine.
	 */
	private val engine: NettyApplicationEngine

	init {
		pancake.events.registerEvent(HttpRegisterRoutesEvent::class)

		pancake.logger.info("HTTP | Initializing Netty Server Engine...")
		engine = embeddedServer(Netty, host = pancake.config.httpHostname, port = pancake.config.httpPort) {

			pancake.logger.info("HTTP | Installing Features... ")

			pancake.logger.fine("HTTP | - CORS Headers")
			// TODO

			pancake.logger.fine("HTTP | - Default Response Headers")
			// TODO

			pancake.logger.fine("HTTP | - Logging")
			install(Logging) {
				logger = pancake.logger
			}

			pancake.logger.fine("HTTP | - Routing")
			install(Routing)

			pancake.logger.info("HTTP | Register Routes...")
			routing {
				pancake.events.callEvent(HttpRegisterRoutesEvent(pancake, RouteRegistrar(this, pancake)))
			}

		}

		pancake.logger.info("HTTP | Starting Server...")
		engine.start(wait = false)
	}

}
