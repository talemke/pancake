package net.tassia.pancake.spam;

import net.tassia.pancake.orm.Email;

public interface PancakeSpamDriver {

	String getName();
	String getVersion();

	PancakeSpam.SpamFilterResult filter(Email email);
	void learn(Email email, PancakeSpam.SpamFilterResult result);

}
