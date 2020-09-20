package net.tassia.pancake.smtp;

import net.tassia.pancake.Pancake;
import net.tassia.pancake.orm.Mail;
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

	public boolean incomingEmail(Mail mail) {
		// Determine email owner
		mail.setAccount(pancake.getAccountByEmailName(mail.getRecipient()));

		// Spam filter
		PancakeSpam.Action action = pancake.getSpamFilter().filter(mail);
		if (action == PancakeSpam.Action.REJECT) {
			return false;
		} else if (action == PancakeSpam.Action.SPAM) {
			mail.setType(Pancake.TYPE_SPAM);
		}


		// Store email
		try {
			pancake.getDatabase().storeEmail(mail);
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
