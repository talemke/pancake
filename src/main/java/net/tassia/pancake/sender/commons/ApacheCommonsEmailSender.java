package net.tassia.pancake.sender.commons;

import net.tassia.pancake.DNSUtility;
import net.tassia.pancake.Pancake;
import net.tassia.pancake.orm.Mail;
import net.tassia.pancake.parser.ParsedMail;
import net.tassia.pancake.sender.PancakeSenderDriver;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;

import javax.naming.NamingException;

public class ApacheCommonsEmailSender implements PancakeSenderDriver {

	@Override
	public boolean deliverMail(Pancake pancake, Mail mail) {
		try {
			SimpleEmail mailObj = new SimpleEmail();

			ParsedMail parsed = mail.getParsed();
			if (parsed == null) parsed = pancake.getParser().parse(mail.getData());
			if (parsed == null) {
				pancake.getLogger().warning("Failed to parse mail " + mail.getUUID() + ", thus delivery failed.");
				return false;
			}

			String host = mail.getRecipient().split("@")[1];
			String[] mx = DNSUtility.lookupMX(host);
			if (mx.length == 0) {
				pancake.getLogger().warning("Found no MX records for " + host);
				return false;
			}

			mailObj.setHostName(mx[0]);
			mailObj.addTo(mail.getRecipient());
			mailObj.setFrom(mail.getSender());
			mailObj.setSubject(parsed.subject);
			mailObj.setMsg(parsed.display);

			mailObj.send();
			return true;
		} catch (EmailException | NamingException ex) {
			ex.printStackTrace();
			return false;
		}
	}

	@Override
	public String getName() {
		return "Apache Commons Email";
	}

	@Override
	public String getVersion() {
		return "1.5";
	}

}
