package net.tassia.pancake.smtp;

import net.tassia.pancake.Pancake;
import net.tassia.pancake.orm.Email;
import net.tassia.pancake.smtp.subethamail.SubethaSMTPDriver;
import net.tassia.pancake.spam.PancakeSpam;

import java.sql.SQLException;

public class PancakeSMTP {
	private final Pancake pancake;
	private final PancakeSMTPDriver driver;

	public PancakeSMTP(Pancake pancake) {
		this.pancake = pancake;
		this.driver = new SubethaSMTPDriver(pancake);

		pancake.getLogger().info("  Driver: " + driver.getName() + " - v" + driver.getVersion());
	}

	public boolean incomingEmail(Email email) {
		// Determine email owner
		email.setAccount(pancake.getAccountByEmailName(email.getRecipient().split("@", 2)[0]));

		// Spam filter
		PancakeSpam.Action action = pancake.getSpamFilter().filter(email);
		if (action == PancakeSpam.Action.REJECT) {
			return false;
		} else if (action == PancakeSpam.Action.SPAM) {
			email.setType(Pancake.TYPE_SPAM);
		}


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
