package net.tassia.pancake.spam.generic;

import net.tassia.pancake.orm.Mail;
import net.tassia.pancake.spam.PancakeSpam;
import net.tassia.pancake.spam.PancakeSpamDriver;

public class GenericSpamFilter implements PancakeSpamDriver {

	@Override
	public String getName() {
		return "Generic";
	}

	@Override
	public String getVersion() {
		return "1.0.0";
	}

	@Override
	public PancakeSpam.SpamFilterResult filter(Mail mail) {
		// Sender = Recipient?
		if (mail.getSender().equalsIgnoreCase(mail.getRecipient())) {
			return new PancakeSpam.SpamFilterResult("Sender = Recipient", 1D);
		}

		// Check HELO
		// TODO

		return null;
	}

	@Override
	public void learn(Mail mail, PancakeSpam.SpamFilterResult result) {
		// Do nothing
	}

}
