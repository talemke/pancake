package net.tassia.pancake.plugin.http

class HttpConfiguration {

	var hostname: String = "0.0.0.0"
	var port: Int = 8080



	fun loadConfig() {
		this.hostname = System.getenv("HTTP_HOSTNAME") ?: hostname
		this.port = System.getenv("HTTP_PORT")?.toInt() ?: port
	}

}
