package net.tassia.pancake.config

import java.io.File
import kotlin.reflect.KMutableProperty1
import kotlin.reflect.KProperty1
import kotlin.reflect.full.findAnnotation
import kotlin.reflect.full.memberProperties

/**
 * The BootConfiguration contains configurable information about how the application should be launched.
 * This includes the port numbers, logging levels and database credentials. Everything else, which needs
 * to be synchronized across different nodes is configured in the database.
 *
 * @since Pancake 1.0
 * @author Tassilo
 */
class BootConfiguration {

	@PropertyName("Server.HTTP", "Enabled")
	@CommandLineOverride("http-enabled")
	var httpEnabled: Boolean = true

	@PropertyName("Server.HTTP", "BindAddress")
	@CommandLineOverride("http-bind-address")
	var httpBindAddress: String = "0.0.0.0"

	@PropertyName("Server.HTTP", "BindPort")
	@CommandLineOverride("http-bind-port")
	var httpBindPort: Int = 8080



	@PropertyName("Server.SMTP", "Enabled")
	@CommandLineOverride("smtp-enabled")
	var smtpEnabled: Boolean = true

	@PropertyName("Server.SMTP", "BindAddress")
	@CommandLineOverride("smtp-bind-address")
	var smtpBindAddress: String = "0.0.0.0"

	@PropertyName("Server.SMTP", "BindPort")
	@CommandLineOverride("smtp-bind-port")
	var smtpBindPort: Int = 25



	@PropertyName("Database", "Driver")
	@CommandLineOverride("db-driver")
	var databaseDriver: String = "SQLite"



	@PropertyName("Database.PostgreSQL", "Hostname")
	@CommandLineOverride("postgresql-hostname")
	var postgresqlHostname: String = "localhost"

	@PropertyName("Database.PostgreSQL", "Port")
	@CommandLineOverride("postgresql-port")
	var postgresqlPort: Int = 5432

	@PropertyName("Database.PostgreSQL", "Database")
	@CommandLineOverride("postgresql-database")
	var postgresqlDatabase: String = "database"

	@PropertyName("Database.PostgreSQL", "Username")
	@CommandLineOverride("postgresql-username")
	var postgresqlUsername: String = "username"

	@PropertyName("Database.PostgreSQL", "Password")
	@CommandLineOverride("postgresql-password")
	var postgresqlPassword: String = "password"



	@PropertyName("Database.MySQL", "Hostname")
	@CommandLineOverride("mysql-hostname")
	var mysqlHostname: String = "localhost"

	@PropertyName("Database.MySQL", "Port")
	@CommandLineOverride("mysql-port")
	var mysqlPort: Int = 3306

	@PropertyName("Database.MySQL", "Database")
	@CommandLineOverride("mysql-database")
	var mysqlDatabase: String = "database"

	@PropertyName("Database.MySQL", "Username")
	@CommandLineOverride("mysql-username")
	var mysqlUsername: String = "username"

	@PropertyName("Database.MySQL", "Password")
	@CommandLineOverride("mysql-password")
	var mysqlPassword: String = "password"

	@PropertyName("Database.MySQL", "PoolSize")
	@CommandLineOverride("mysql-pool-size")
	var mysqlPoolSize: Int = 10



	@PropertyName("Database.Oracle", "Hostname")
	@CommandLineOverride("oracle-hostname")
	var oracleHostname: String = "localhost"

	@PropertyName("Database.Oracle", "Port")
	@CommandLineOverride("oracle-port")
	var oraclePort: Int = 1433

	@PropertyName("Database.Oracle", "Database")
	@CommandLineOverride("oracle-database")
	var oracleDatabase: String = "database"

	@PropertyName("Database.Oracle", "Username")
	@CommandLineOverride("oracle-username")
	var oracleUsername: String = "username"

	@PropertyName("Database.Oracle", "Password")
	@CommandLineOverride("oracle-password")
	var oraclePassword: String = "password"



	@PropertyName("Database.SQLite", "InMemory")
	@CommandLineOverride("sqlite-in-memory")
	var sqliteInMemory: Boolean = false



	@PropertyName("Database.H2", "InMemory")
	@CommandLineOverride("h2-in-memory")
	var h2InMemory: Boolean = false

	@PropertyName("Database.H2", "KeepAlive")
	@CommandLineOverride("h2-keep-alive")
	var h2KeepAlive: Boolean = true



	@PropertyName("Database.SQLServer", "Hostname")
	@CommandLineOverride("sqlserver-hostname")
	var sqlServerHostname: String = "localhost"

	@PropertyName("Database.SQLServer", "Port")
	@CommandLineOverride("sqlserver-port")
	var sqlServerPort: Int = 1433

	@PropertyName("Database.SQLServer", "Database")
	@CommandLineOverride("sqlserver-database")
	var sqlServerDatabase: String = "database"

	@PropertyName("Database.SQLServer", "Username")
	@CommandLineOverride("sqlserver-username")
	var sqlServerUsername: String = "username"

	@PropertyName("Database.SQLServer", "Password")
	@CommandLineOverride("sqlserver-password")
	var sqlServerPassword: String = "password"





	fun load(file: File) {
		TODO()
	}

	fun checkOverrides(args: Array<String>) {
		for (prop in this::class.memberProperties) {
			val name = prop.findAnnotation<CommandLineOverride>() ?: continue
			val override = args.find { it.startsWith("--${name.value}=") } ?: continue
			assignValue(prop, override.substring("--${name.value}=".length))
		}
	}

	@Suppress("UNCHECKED_CAST")
	private fun assignValue(prop: KProperty1<out BootConfiguration, *>, value: String) {
		require(prop is KMutableProperty1)
		when (prop.returnType) {
			Int::class -> {
				// Set integer
				val intValue = value.toInt()
				(prop as KMutableProperty1<BootConfiguration, Int>).set(this, intValue)

			}
			String::class -> {
				// Set string
				(prop as KMutableProperty1<BootConfiguration, String>).set(this, value)

			}
			else -> throw IllegalStateException("${prop.name} has unsupported type: ${prop.returnType}")
		}
	}

}
