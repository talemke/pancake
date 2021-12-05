package net.tassia.pancake.entity

import net.tassia.pancake.database.Transaction
import java.sql.ResultSet

internal class PluginOptionResultSet(result: ResultSet) : PluginOption {

	override val pluginID: String = result.getString("PluginID")
	override val option: String = result.getString("Option")
	override val description: String? = result.getString("Description")
	override val defaultValue: String = result.getString("DefaultValue")
	override val currentValue: String? = result.getString("CurrentValue")

	override val effectiveValue: String = currentValue ?: defaultValue

	override fun reload(transaction: Transaction): PluginOption {
		TODO("Not yet implemented")
	}

	override fun delete(transaction: Transaction) {
		TODO("Not yet implemented")
	}

}
