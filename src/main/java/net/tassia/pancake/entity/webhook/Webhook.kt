package net.tassia.pancake.entity.webhook

import net.tassia.pancake.entity.account.Account
import org.jetbrains.exposed.dao.Entity
import org.jetbrains.exposed.dao.EntityClass
import org.jetbrains.exposed.dao.id.EntityID
import java.util.*

/**
 * Represents a simple webhook.
 *
 * @since Pancake 1.0
 * @author Tassilo
 */
class Webhook(id: EntityID<UUID>) : Entity<UUID>(id) {

	companion object : EntityClass<UUID, Webhook>(WebhookTable)

	var url by WebhookTable.url
	var contentType by WebhookTable.contentType
	var events by WebhookTable.events

	var account by Account referencedOn WebhookTable.account

}
