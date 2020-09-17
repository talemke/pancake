package net.tassia.pancake.smtp;

import net.tassia.pancake.Pancake;

public interface PancakeSMTPDriver {

	void start(Pancake pancake);
	String getName();
	String getVersion();

}
