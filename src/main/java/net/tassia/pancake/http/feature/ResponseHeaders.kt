package net.tassia.pancake.http.feature

import io.ktor.application.*
import io.ktor.response.*
import io.ktor.util.*
import io.ktor.util.pipeline.*
import net.tassia.pancake.Pancake

/**
 * This feature inserts default headers (such as the 'Server' header) to every HTTP response.
 *
 * @since Pancake 1.0
 * @author Tassilo
 */
class ResponseHeaders {

	/**
	 * Inserts default response headers.
	 *
	 * @param self context
	 */
	fun insertHeaders(self: PipelineContext<Unit, ApplicationCall>) {
		self.call.response.header("Server", "Ktor/1.5.1")
		self.call.response.header("PancakeVersion", Pancake.VERSION.toDisplayString())
	}



	companion object Feature : ApplicationFeature<Application, Nothing, ResponseHeaders> {

		override val key: AttributeKey<ResponseHeaders> = AttributeKey("ResponseHeaders")

		override fun install(pipeline: Application, configure: Nothing.() -> Unit): ResponseHeaders {
			// Create feature and pipeline interception
			val feature = ResponseHeaders()
			pipeline.intercept(ApplicationCallPipeline.Features) { feature.insertHeaders(this) }
			return feature
		}

	}

}
