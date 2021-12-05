package net.tassia.pancake.server.http.routing

import io.ktor.application.*
import io.ktor.http.*
import io.ktor.util.pipeline.*
import net.tassia.pancake.plugin.Plugin

abstract class Route(val plugin: Plugin, val path: String, val method: HttpMethod) {

	var description: String? = null

	abstract val handle: PipelineInterceptor<Unit, ApplicationCall>

}
