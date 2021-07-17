package net.tassia.pancake.database

import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import java.lang.ClassNotFoundException
import net.tassia.pancake.PancakeException
import net.tassia.pancake.config.BootConfiguration
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.transactions.TransactionManager
import java.sql.Connection

class DatabaseManager {

    private fun resolveDriver(driver: DatabaseDriver): String {
		try {
            Class.forName(driver.driverClass)
            return driver.driverClass
        } catch (ex: ClassNotFoundException) {
            throw PancakeException("Cannot load database, as driver for $driver is not loaded on classpath.", ex)
        }
    }

    fun connect(config: BootConfiguration): Database {
        return when (config.databaseDriver.lowercase()) {
            "postgresql" -> connectPostgreSQL(config)
            "mysql" -> connectMySQL(config)
            "mysql-hikari" -> connectHikariMySQL(config)
            "oracle" -> connectOracle(config)
            "sqlite" -> connectSQLite(config)
            "h2" -> connectH2(config)
            "sqlserver" -> connectSQLServer(config)
            else -> throw PancakeException("Unsupported database driver: " + config.databaseDriver)
        }
    }



    private fun connectPostgreSQL(config: BootConfiguration): Database {
        return connectPostgreSQL(
            config.postgresqlHostname, config.postgresqlPort,
            config.postgresqlDatabase, config.postgresqlUsername, config.postgresqlPassword
        )
    }

    private fun connectPostgreSQL(hostname: String, port: Int, database: String, username: String, password: String): Database {
        val driver = resolveDriver(DatabaseDriver.POSTGRESQL)
        val url = String.format("jdbc:postgresql://%s:%d/%s", hostname, port, database)
        return Database.connect(url, driver, username, password)
    }



    private fun connectMySQL(config: BootConfiguration): Database {
        return connectMySQL(
            config.mysqlHostname, config.mysqlPort, config.mysqlDatabase,
            config.mysqlUsername, config.mysqlPassword
        )
    }

    private fun connectMySQL(hostname: String, port: Int, database: String, username: String, password: String): Database {
        val driver = resolveDriver(DatabaseDriver.MYSQL)
        val url = String.format("jdbc:mysql://%s:%d/%s", hostname, port, database)
        return Database.connect(url, driver, username, password)
    }



    private fun connectHikariMySQL(config: BootConfiguration): Database {
        return connectHikariMYSQL(
            config.mysqlHostname, config.mysqlPort, config.mysqlDatabase,
            config.mysqlUsername, config.mysqlPassword, config.mysqlPoolSize
        )
    }

    private fun connectHikariMYSQL(hostname: String, port: Int, database: String, username: String, password: String, poolSize: Int): Database {
        val config = HikariConfig().also {
			it.driverClassName = resolveDriver(DatabaseDriver.MYSQL_HIKARI)
        	it.jdbcUrl = String.format("jdbc:mysql://%s:%d/%s", hostname, port, database)
			it.username = username
			it.password = password
			it.maximumPoolSize = poolSize
		}
		return Database.connect(HikariDataSource(config))
    }



    private fun connectOracle(config: BootConfiguration): Database {
        return connectOracle(
            config.oracleHostname, config.oraclePort, config.oracleDatabase,
            config.oracleUsername, config.oraclePassword
        )
    }

    private fun connectOracle(hostname: String, port: Int, database: String, username: String, password: String): Database {
        val driver = resolveDriver(DatabaseDriver.ORACLE)
		val url = String.format("jdbc:jdbc:oracle:thin:@//%s:%d/%s", hostname, port, database)
        return Database.connect(url, driver, username, password)
    }



    private fun connectSQLite(config: BootConfiguration): Database {
        return connectSQLite(config.sqliteInMemory)
    }

    private fun connectSQLite(inMemory: Boolean): Database {
        val driver = resolveDriver(DatabaseDriver.SQLITE)
		TransactionManager.manager.defaultIsolationLevel = Connection.TRANSACTION_SERIALIZABLE
		return if (inMemory) {
			Database.connect("jdbc:sqlite:file:test?mode=memory&cache=shared", driver)
		} else {
			Database.connect("jdbc:sqlite:./data/storage.db", driver)
		}
    }



    private fun connectH2(config: BootConfiguration): Database {
        return connectH2(config.h2InMemory, config.h2KeepAlive)
    }

    private fun connectH2(inMemory: Boolean, keepAlive: Boolean): Database {
        val driver = resolveDriver(DatabaseDriver.H2)
		return if (!inMemory) {
			Database.connect("jdbc:h2:./data/storage.h2", driver)
		} else if (keepAlive) {
			Database.connect("jdbc:h2:mem:regular", driver)
		} else {
			Database.connect("jdbc:h2:mem:regular;DB_CLOSE_DELAY=-1;", driver)
		}
    }



    private fun connectSQLServer(config: BootConfiguration): Database {
        return connectSQLServer(
            config.sqlServerHostname, config.sqlServerPort,
            config.sqlServerDatabase, config.sqlServerUsername, config.sqlServerPassword
        )
    }

    private fun connectSQLServer(hostname: String, port: Int, database: String, username: String, password: String): Database {
        val driver = resolveDriver(DatabaseDriver.SQL_SERVER)
		val url = String.format("jdbc:sqlserver://%s:%d;databaseName=%s", hostname, port, database)
        return Database.connect(url, driver, username, password)
    }

}
