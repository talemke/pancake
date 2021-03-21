package net.tassia.pancake.plugin.http

import io.ktor.application.*
import io.ktor.routing.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import net.tassia.event.EventManager
import net.tassia.pancake.Pancake
import net.tassia.pancake.plugin.Plugin
import net.tassia.pancake.plugin.PluginInfo
import net.tassia.pancake.plugin.http.event.HttpRegisterRoutesEvent
import net.tassia.pancake.plugin.http.feature.Logging
import net.tassia.pancake.plugin.http.feature.ResponseHeaders
import net.tassia.pancake.plugin.http.v1.RegisterRoutesV1

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

	/**
	 * The underlying HTTP engine.
	 */
	private var engine: NettyApplicationEngine? = null



	override fun onEnable() {
		pancake.events.registerEvent(HttpRegisterRoutesEvent::class)

		info("Initializing Netty Server Engine...")
		engine = embeddedServer(Netty, host = pancake.config.httpHostname, port = pancake.config.httpPort) {

			info("Installing Features... ")

			debug("- CORS Headers")
			// TODO

			debug("- Response Headers")
			install(ResponseHeaders)

			debug("- Logging")
			install(Logging) {
				http = this@PancakeHttp
			}

			debug("- Routing")
			install(Routing)

			debug("Register Routes...")
			pancake.events.registerListener(HttpRegisterRoutesEvent::class, RegisterRoutesV1)
			routing {
				pancake.events.callEvent(HttpRegisterRoutesEvent(pancake, RouteRegistrar(this, this@PancakeHttp)))
			}

		}

		info("Starting Server...")
		engine?.start(wait = false)
	}



	override fun onDisable() = stop()

	/**
	 * Shuts down the HTTP server.
	 *
	 * @param gracePeriodMillis the maximum amount of time for activity to cool down
	 * @param timeoutMillis the maximum amount of time to wait until server stops gracefully
	 */
	private fun stop(gracePeriodMillis: Long = 5000, timeoutMillis: Long = 5000) {
		engine?.stop(gracePeriodMillis, timeoutMillis)
	}



	companion object {

		/**
		 * The version information for the HTTP plugin.
		 */
		private val Version = Pancake.VERSION

		/**
		 * Registers all events for this plugin.
		 */
		private val RegisterEvents = { events: EventManager ->
			events.registerEvent<HttpRegisterRoutesEvent>()
		}

		/**
		 * The plugin information for the CLI plugin.
		 */
		val Info = PluginInfo(
			id = "net.tassia:HTTP",
			name = "HTTP",
			description = "The HTTP server implementation. Adds the Rest API functionality.",
			authors = setOf("Tassilo"),
			version = Version,
			constructor = ::PancakeHttp,
			events = RegisterEvents,
		)

	}

}
