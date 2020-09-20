package net.tassia.pancake.spam;

import net.tassia.pancake.orm.Mail;

public interface PancakeSpamDriver {

	String getName();
	String getVersion();

	PancakeSpam.SpamFilterResult filter(Mail mail);
	void learn(Mail mail, PancakeSpam.SpamFilterResult result);

}
