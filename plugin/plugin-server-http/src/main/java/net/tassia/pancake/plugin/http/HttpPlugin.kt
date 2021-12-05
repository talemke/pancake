package net.tassia.pancake.plugin.http

import io.ktor.server.engine.*
import io.ktor.server.netty.*
import net.tassia.Version
import net.tassia.pancake.Pancake
import net.tassia.pancake.database.Transaction
import net.tassia.pancake.plugin.Plugin
import net.tassia.pancake.plugin.PluginInformation
import net.tassia.pancake.plugin.http.routing.registerGenericRoutes
import net.tassia.pancake.server.http.event.RegisterRoutesEvent
import net.tassia.pancake.server.http.plugin.installContentNegotiation
import net.tassia.pancake.server.http.plugin.installDefaultHeaders
import net.tassia.pancake.server.http.plugin.installRouting
import net.tassia.pancake.server.http.HttpServer
import net.tassia.pancake.server.http.routing.Router

class HttpPlugin(pancake: Pancake) : Plugin(pancake, HttpPlugin) {

	val config = HttpConfiguration()
	var server: HttpServer? = null; private set



	override suspend fun onLoad() {
		// Load configuration
		config.loadConfig()

		// Add listener
		listenSync<RegisterRoutesEvent> {
			it.router.registerGenericRoutes(this)
		}
	}

	override suspend fun onEnable() {
		// Load routes
		val router = Router()
		callEvent(RegisterRoutesEvent(router))

		// Create engine
		val engine = embeddedServer(Netty, host = config.hostname, port = config.port) {
			installRouting(router)
			installContentNegotiation()
			installDefaultHeaders()
		}

		// Create & start server
		this.server = HttpServer(engine).also {
			it.start()
		}
	}

	override suspend fun onDisable() {
		server?.stop()
		this.server = null
	}



	override suspend fun onUpgrade(transaction: Transaction, from: Int, to: Int) {
		TODO("Not yet implemented")
	}

	override suspend fun onDowngrade(transaction: Transaction, from: Int, to: Int) {
		TODO("Not yet implemented")
	}





	companion object : PluginInformation() {

		override val identifier: String = "pancake:http-server"
		override val name: String = "http-server"
		override val displayName: String = "HTTP Server"
		override val authors: Set<String> = setOf("Tassilo")
		override val description: String = "Provides an HTTP server to handle API requests."
		override val website: String = "https://github.com/TASSIA710/pancake"
		override val version: Version = Pancake.VERSION
		override val installationVersion: Int = 1

		override val isGlobal: Boolean = false

	}

}
