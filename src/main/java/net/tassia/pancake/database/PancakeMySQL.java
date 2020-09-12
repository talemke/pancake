package net.tassia.pancake.database;

import net.tassia.pancake.Pancake;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class PancakeMySQL extends PancakeSQL {

	/* Constructor */
	public PancakeMySQL(Pancake pancake) {
		super(pancake, "MySQL");
	}
	/* Constructor */



	/* Connect */
	@Override
	public void connect() throws SQLException, ClassNotFoundException {
		String hostname = pancake.getConfig().mysqlHostname;
		int port = pancake.getConfig().mysqlPort;
		String database = pancake.getConfig().mysqlDatabase;
		String username = pancake.getConfig().mysqlUsername;
		String password = pancake.getConfig().mysqlPassword;

		Class.forName("com.mysql.cj.jdbc.Driver");
		String url = "jdbc:mysql://" + hostname + ":" + port + "/" + database + "?rewriteBatchedStatements=true&serverTimezone=UTC";
		connection = DriverManager.getConnection(url, username, password);
	}
	/* Connect */

}
