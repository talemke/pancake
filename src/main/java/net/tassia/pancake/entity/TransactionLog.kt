package net.tassia.pancake.entity

data class TransactionLog(val log: List<Pair<Role, ByteArray>>) {

	enum class Role {
		CLIENT, SERVER;
	}

}
