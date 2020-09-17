package net.tassia.pancake.smtp;

import net.tassia.pancake.Pancake;
import net.tassia.pancake.orm.Account;
import net.tassia.pancake.orm.Email;
import net.tassia.pancake.smtp.subethamail.SubethaSMTPDriver;

import java.sql.SQLException;

public class PancakeSMTP {
	private final Pancake pancake;
	private final PancakeSMTPDriver driver;

	public PancakeSMTP(Pancake pancake) {
		this.pancake = pancake;
		this.driver = new SubethaSMTPDriver(pancake);
	}

	public boolean incomingEmail(Email email) {
		// Determine email owner
		email.setAccount(pancake.getAccountByEmailName(email.getRecipient().split("@", 2)[0]));

		// Store email
		try {
			pancake.getDatabase().storeEmail(email);
		} catch (SQLException ex) {
			ex.printStackTrace();
			return false;
		}
		return true;
	}

	public void start() {
		driver.start(pancake);
	}

}
