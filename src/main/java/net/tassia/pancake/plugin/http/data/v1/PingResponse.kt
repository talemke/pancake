package net.tassia.pancake.plugin.http.data.v1

import com.fasterxml.jackson.annotation.JsonProperty
import net.tassia.pancake.Pancake

/**
 * A basic 'Ping' response.
 *
 * @since Pancake 1.0
 * @author Tassilo
 */
class PingResponse {

	@JsonProperty("version")
	val version: String = Pancake.VERSION.toDisplayString()

}
