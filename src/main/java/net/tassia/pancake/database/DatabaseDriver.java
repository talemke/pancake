package net.tassia.pancake.database;

/**
 * Defines a possible database driver.
 *
 * @since Pancake 1.0
 * @author Tassilo
 */
public enum DatabaseDriver {

	POSTGRESQL("org.postgresql.Driver"),

	MYSQL("com.mysql.jdbc.Driver"),

	MYSQL_HIKARI("com.mysql.cj.jdbc.Driver"),

	ORACLE("oracle.jdbc.OracleDriver"),

	SQLITE("org.sqlite.JDBC"),

	H2("org.h2.Driver"),

	SQL_SERVER("com.microsoft.sqlserver.jdbc.SQLServerDriver");



	/**
	 * The database driver implementation class.
	 */
	public final String driverClass;

	DatabaseDriver(String driverClass) {
		this.driverClass = driverClass;
	}

}
