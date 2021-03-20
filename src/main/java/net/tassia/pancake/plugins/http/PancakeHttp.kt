package net.tassia.pancake.plugins.http

import io.ktor.application.*
import io.ktor.routing.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import net.tassia.pancake.Pancake
import net.tassia.pancake.http.RouteRegistrar
import net.tassia.pancake.http.event.HttpRegisterRoutesEvent
import net.tassia.pancake.http.feature.Logging
import net.tassia.pancake.http.feature.ResponseHeaders
import net.tassia.pancake.http.route.v1.RegisterRoutesV1
import net.tassia.pancake.plugin.Plugin
import net.tassia.pancake.plugin.PluginInfo

/**
 * The base class for the HTTP sub-system of Pancake.
 *
 * @param pancake the [Pancake] instance
 *
 * @since Pancake 1.0
 * @author Tassilo
 */
class PancakeHttp(override val pancake: Pancake) : Plugin(pancake) {

	override val info: PluginInfo = Info

	override val events = setOf(HttpRegisterRoutesEvent::class)

	/**
	 * The underlying HTTP engine.
	 */
	private var engine: NettyApplicationEngine? = null



	override fun onEnable() {
		pancake.events.registerEvent(HttpRegisterRoutesEvent::class)

		pancake.logger.info("HTTP | Initializing Netty Server Engine...")
		engine = embeddedServer(Netty, host = pancake.config.httpHostname, port = pancake.config.httpPort) {

			pancake.logger.info("HTTP | Installing Features... ")

			pancake.logger.fine("HTTP | - CORS Headers")
			// TODO

			pancake.logger.fine("HTTP | - Response Headers")
			install(ResponseHeaders)

			pancake.logger.fine("HTTP | - Logging")
			install(Logging) {
				logger = pancake.logger
			}

			pancake.logger.fine("HTTP | - Routing")
			install(Routing)

			pancake.logger.info("HTTP | Register Routes...")
			pancake.events.registerListener(HttpRegisterRoutesEvent::class, RegisterRoutesV1)
			routing {
				pancake.events.callEvent(HttpRegisterRoutesEvent(pancake, RouteRegistrar(this, pancake)))
			}

		}

		pancake.logger.info("HTTP | Starting Server...")
		engine?.start(wait = false)
	}



	override fun onDisable() = stop()

	/**
	 * Shuts down the HTTP server.
	 *
	 * @param gracePeriodMillis the maximum amount of time for activity to cool down
	 * @param timeoutMillis the maximum amount of time to wait until server stops gracefully
	 */
	fun stop(gracePeriodMillis: Long = 5000, timeoutMillis: Long = 5000) {
		engine?.stop(gracePeriodMillis, timeoutMillis)
	}



	companion object {

		/**
		 * The version information for the HTTP plugin.
		 */
		val Version = Pancake.VERSION

		/**
		 * The plugin information for the CLI plugin.
		 */
		val Info = PluginInfo(
			id = "net.tassia:HTTP",
			name = "HTTP",
			description = "The HTTP server implementation. Adds the Rest API functionality.",
			authors = setOf("Tassilo"),
			version = Version,
			constructor = ::PancakeHttp
		)

	}

}
