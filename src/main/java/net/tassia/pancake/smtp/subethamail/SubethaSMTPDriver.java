package net.tassia.pancake.smtp.subethamail;

import net.tassia.pancake.Pancake;
import net.tassia.pancake.smtp.PancakeSMTPDriver;
import org.subethamail.smtp.AuthenticationHandlerFactory;
import org.subethamail.smtp.MessageContext;
import org.subethamail.smtp.auth.EasyAuthenticationHandlerFactory;
import org.subethamail.smtp.auth.LoginFailedException;
import org.subethamail.smtp.auth.UsernamePasswordValidator;
import org.subethamail.smtp.server.SMTPServer;

public class SubethaSMTPDriver implements PancakeSMTPDriver {
	private final SMTPServer server;

	public SubethaSMTPDriver(Pancake pancake) {
		AuthenticationHandlerFactory authFactory = new EasyAuthenticationHandlerFactory(new Validator(pancake));

		server = SMTPServer
			.port(pancake.getConfig().smtpPort)
			.softwareName("Pancake-" + getName() + "/" + getVersion())
			.authenticationHandlerFactory(authFactory)
			.backlog(pancake.getConfig().smtpBacklog)
			.enableTLS(pancake.getConfig().smtpEnableTLS)
			.hideTLS(pancake.getConfig().smtpHideTLS)
			.requireTLS(pancake.getConfig().smtpRequireTLS)
			.maxConnections(pancake.getConfig().smtpMaxConnections)
			.connectionTimeoutMs(pancake.getConfig().smtpConnectionTimeout)
			.maxRecipients(pancake.getConfig().smtpMaxRecipients)
			.maxMessageSize(pancake.getConfig().smtpMaxMessageSize)
			.messageHandler(new MessageHandler(pancake))
			.build();
	}

	@Override
	public void start(Pancake pancake) {
		server.start();
	}

	@Override
	public String getName() {
		return "SubethaSMTP";
	}

	@Override
	public String getVersion() {
		return "3.1.7";
	}



	protected class Validator implements UsernamePasswordValidator {
		private final Pancake pancake;
		protected Validator(Pancake pancake) {
			this.pancake = pancake;
		}
		@Override
		public void login(String username, String password, MessageContext context) throws LoginFailedException {
			this.pancake.getLogger().fine("Attempted login: " + username + ", " + password);
		}
	}

}
