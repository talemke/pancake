package net.tassia.pancake.http.feature

import io.ktor.application.*
import io.ktor.request.*
import io.ktor.util.*
import io.ktor.util.pipeline.*
import java.util.logging.Logger

/**
 * This feature logs HTTP requests to the given logger.
 *
 * @since Pancake 1.0
 * @author Tassilo
 */
class Logging(private val logger: Logger) {

	/**
	 * Log the HTTP transaction.
	 *
	 * @param self context
	 */
	fun logTransaction(self: PipelineContext<Unit, ApplicationCall>) {
		val method = self.context.request.httpMethod.value
		val url = self.context.request.uri
		val code = self.context.response.status()?.value
		if (code == null || code >= 500) {
			logger.warning("HTTP | $method $url - $code")
		} else {
			logger.fine("HTTP | $method $url - $code")
		}
	}



	/**
	 * Represents a mutable configuration for this feature.
	 */
	data class Configuration(

		/**
		 * The logger to use. This must be set to a non-null value.
		 */
		var logger: Logger? = null

	)



	companion object Feature : ApplicationFeature<Application, Configuration, Logging> {

		override val key: AttributeKey<Logging> = AttributeKey("Logging")

		override fun install(pipeline: Application, configure: Configuration.() -> Unit): Logging {
			// Load configuration
			val config = Configuration().apply(configure)

			// Verify configuration
			val logger = config.logger ?: throw IllegalStateException("Logger cannot be null.")

			// Create feature and pipeline interception
			val feature = Logging(logger)
			pipeline.intercept(ApplicationCallPipeline.Fallback) { feature.logTransaction(this) }
			return feature
		}

	}

}
