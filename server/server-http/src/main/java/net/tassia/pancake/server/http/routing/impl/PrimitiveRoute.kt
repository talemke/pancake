package net.tassia.pancake.server.http.routing.impl

import io.ktor.application.*
import io.ktor.http.*
import io.ktor.util.pipeline.*
import net.tassia.pancake.plugin.Plugin
import net.tassia.pancake.server.http.routing.Route

internal class PrimitiveRoute(plugin: Plugin, path: String, method: HttpMethod, override val handle: PipelineInterceptor<Unit, ApplicationCall>)
	: Route(plugin, path, method)
