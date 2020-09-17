package net.tassia.pancake.spam.generic;

import net.tassia.pancake.orm.Email;
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
	public PancakeSpam.SpamFilterResult filter(Email email) {
		// Sender = Recipient?
		if (email.getSender().equalsIgnoreCase(email.getRecipient())) {
			return new PancakeSpam.SpamFilterResult("Sender = Recipient", 1D);
		}

		// Check HELO
		// TODO

		return null;
	}

	@Override
	public void learn(Email email, PancakeSpam.SpamFilterResult result) {
		// Do nothing
	}

}
