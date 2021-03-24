package net.tassia.pancake.entity.webhook

import net.tassia.pancake.entity.account.AccountTable
import org.jetbrains.exposed.dao.id.IdTable
import java.util.*

/**
 * The database table for [Webhook]s.
 *
 * @since Pancake 1.0
 * @author Tassilo
 */
object WebhookTable : IdTable<UUID>("pancake_webhook") {

	override val id = entityId("WebhookID", WebhookTable)
	override val primaryKey = PrimaryKey(id)

	val url = varchar("URL", 255).uniqueIndex()
	val contentType = enumeration("ContentType", WebhookPayloadType::class)
	val events = long("Events")

	val account = reference("AccountID", AccountTable)

}
