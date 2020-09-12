package net.tassia.pancake.database;

import net.tassia.pancake.Pancake;

import java.sql.DriverManager;
import java.sql.SQLException;

public class PancakeSQLite extends PancakeSQL {

	/* Constructor */
	public PancakeSQLite(Pancake pancake) {
		super(pancake, "SQLite");
	}
	/* Constructor */



	/* Connect */
	@Override
	public void connect() throws SQLException {
		connection = DriverManager.getConnection("jdbc:sqlite:storage.db");
	}
	/* Connect */

}
