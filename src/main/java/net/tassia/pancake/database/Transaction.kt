package net.tassia.pancake.database

import org.intellij.lang.annotations.Language
import java.sql.PreparedStatement

abstract class Transaction {

	abstract fun prepareStatement(@Language("SQL") query: String): PreparedStatement

}
