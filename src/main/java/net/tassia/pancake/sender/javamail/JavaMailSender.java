package net.tassia.pancake.sender.javamail;

import net.tassia.pancake.DNSUtility;
import net.tassia.pancake.Pancake;
import net.tassia.pancake.orm.Mail;
import net.tassia.pancake.parser.ParsedMail;
import net.tassia.pancake.sender.PancakeSenderDriver;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.naming.NamingException;
import java.util.Properties;

@Deprecated
public class JavaMailSender implements PancakeSenderDriver {

	@Override
	public boolean deliverMail(Pancake pancake, Mail mail) {
		if (true) return false;
		Properties props = new Properties();
		try {
			String host = mail.getRecipient().split("@")[1];
			String[] mx = DNSUtility.lookupMX(host);
			if (mx.length == 0) {
				pancake.getLogger().warning("Found no MX records for " + host);
				return false;
			}
			props.put("mail.smtp.host", mx[0]);
		} catch (NamingException ex) {
			ex.printStackTrace();
			return false;
		}
		Session s = Session.getDefaultInstance(props, null);
		MimeMessage msg = new MimeMessage(s);

		ParsedMail parsed = mail.getParsed();
		if (parsed == null) parsed = pancake.getParser().parse(mail.getData());
		if (parsed == null) {
			pancake.getLogger().warning("Failed to parse mail " + mail.getUUID() + ", thus delivery failed.");
			return false;
		}

		try {
			msg.setFrom(new InternetAddress(mail.getSender()));
			msg.setRecipient(Message.RecipientType.TO, new InternetAddress(mail.getRecipient()));
			msg.setHeader("Server", pancake.getServerName());
			msg.setSubject(parsed.subject);
			msg.setText(parsed.display);

			Transport.send(msg);
		} catch (MessagingException ex) {
			ex.printStackTrace();
			return false;
		}
		return true;
	}

	@Override
	public String getName() {
		return "JavaMail";
	}

	@Override
	public String getVersion() {
		return "1.4.4";
	}

}
