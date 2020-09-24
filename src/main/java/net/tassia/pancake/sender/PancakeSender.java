package net.tassia.pancake.sender;

import net.tassia.pancake.Pancake;
import net.tassia.pancake.orm.Mail;
import net.tassia.pancake.sender.commons.ApacheCommonsEmailSender;

public class PancakeSender {
	private final Pancake pancake;
	private final PancakeSenderDriver driver;

	public PancakeSender(Pancake pancake) {
		this.pancake = pancake;
		this.driver = new ApacheCommonsEmailSender();

		pancake.getLogger().info("  Driver: " + driver.getName() + " - v" + driver.getVersion());
	}

	public boolean deliverMail(Mail mail) {
		return driver.deliverMail(pancake, mail);
	}

}
