package net.tassia.pancake.plugin.http

import net.tassia.Version
import net.tassia.pancake.Pancake
import net.tassia.pancake.database.Transaction
import net.tassia.pancake.plugin.Plugin
import net.tassia.pancake.plugin.PluginInformation
import net.tassia.pancake.server.http.HttpServer

class HttpPlugin(pancake: Pancake) : Plugin(pancake, HttpPlugin) {

	private var server: HttpServer? = null



	override suspend fun onLoad() {
		TODO("Not yet implemented")
	}

	override suspend fun onEnable() {
		server.also {
			require(it != null)
			it.start()
		}
	}

	override suspend fun onDisable() {
		server?.stop()
		server = null
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
