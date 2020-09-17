package net.tassia.pancake.smtp.subethamail;

import net.tassia.pancake.Pancake;
import net.tassia.pancake.smtp.PancakeSMTPDriver;
import org.subethamail.smtp.AuthenticationHandlerFactory;
import org.subethamail.smtp.Version;
import org.subethamail.smtp.auth.EasyAuthenticationHandlerFactory;
import org.subethamail.smtp.auth.LoginFailedException;
import org.subethamail.smtp.auth.UsernamePasswordValidator;
import org.subethamail.smtp.server.SMTPServer;

public class SubethaSMTPDriver implements PancakeSMTPDriver {
	private final SMTPServer server;

	public SubethaSMTPDriver(Pancake pancake) {
		AuthenticationHandlerFactory authFactory = new EasyAuthenticationHandlerFactory(new Validator(pancake));
		PancakeMessageHandlerFactory factory = new PancakeMessageHandlerFactory(pancake);

		server = new SMTPServer(factory, authFactory);
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
		public void login(String username, String password) throws LoginFailedException {
			this.pancake.getLogger().fine("Attempted login: " + username + ", " + password);
		}
	}

}
