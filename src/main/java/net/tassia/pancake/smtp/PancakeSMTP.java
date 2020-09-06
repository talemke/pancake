package net.tassia.pancake.smtp;

import net.tassia.pancake.Pancake;
import org.subethamail.smtp.server.SMTPServer;

public class PancakeSMTP {
	private final SMTPServer server;

	public PancakeSMTP(Pancake pancake) {
		PancakeMessageHandlerFactory factory = new PancakeMessageHandlerFactory(pancake);
		server = new SMTPServer(factory);
		server.setSoftwareName("PancakeSMTP");
		server.setPort(2500);
	}

	public void start() {
		server.start();
	}

}
