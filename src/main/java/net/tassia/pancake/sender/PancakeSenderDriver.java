package net.tassia.pancake.sender;

import net.tassia.pancake.Pancake;
import net.tassia.pancake.orm.Mail;

public interface PancakeSenderDriver {

	boolean deliverMail(Pancake pancake, Mail mail);
	String getName();
	String getVersion();

}
