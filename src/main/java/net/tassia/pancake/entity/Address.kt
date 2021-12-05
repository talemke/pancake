package net.tassia.pancake.entity

import java.util.*

interface Address : DatabaseEntity<Address> {

	val address: String
	val accountID: UUID

}
