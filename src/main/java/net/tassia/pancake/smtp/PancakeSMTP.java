package net.tassia.pancake.smtp;

import net.tassia.pancake.Pancake;
import org.subethamail.smtp.Version;
import org.subethamail.smtp.auth.MultipleAuthenticationHandlerFactory;
import org.subethamail.smtp.server.SMTPServer;

public class PancakeSMTP {
	private final SMTPServer server;

	public PancakeSMTP(Pancake pancake) {
		PancakeMessageHandlerFactory factory = new PancakeMessageHandlerFactory(pancake);
		server = new SMTPServer(factory, new MultipleAuthenticationHandlerFactory());
		server.setSoftwareName("PancakeSMTP - " + Version.getSpecification());
		server.setPort(pancake.getConfig().smtpPort);
		server.setBacklog(pancake.getConfig().smtpBacklog);
		server.setEnableTLS(pancake.getConfig().smtpEnableTLS);
		server.setHideTLS(pancake.getConfig().smtpHideTLS);
		server.setRequireTLS(pancake.getConfig().smtpRequireTLS);
		server.setDisableReceivedHeaders(pancake.getConfig().smtpDisableReceivedHeaders);
		server.setMaxConnections(pancake.getConfig().smtpMaxConnections);
		server.setConnectionTimeout(pancake.getConfig().smtpConnectionTimeout);
		server.setMaxRecipients(pancake.getConfig().smtpMaxRecipients);
		server.setMaxMessageSize(pancake.getConfig().smtpMaxMessageSize);
	}

	public void start() {
		server.start();
	}

}
