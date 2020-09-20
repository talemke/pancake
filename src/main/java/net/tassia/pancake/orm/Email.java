package net.tassia.pancake.orm;

import net.tassia.pancake.parser.ParsedMail;

import java.util.Map;
import java.util.UUID;

public class Email {
	private final UUID uuid;
	private long timestamp;
	private String sender;
	private String recipient;
	private byte[] data;
	private Map<String, String> headers;
	private String content;
	private String helo;
	private String remoteAddress;
	private int type;
	private Account account;
	private Inbox inbox;
	private ParsedMail parsed;

	/* Constructor */
	public Email() {
		this(UUID.randomUUID());
	}

	public Email(UUID uuid) {
		this.uuid = uuid;
	}
	/* Constructor */





	/* Getters & Setters */
	public UUID getUUID() {
		return uuid;
	}

	public long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}

	public String getSender() {
		return sender;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}

	public String getRecipient() {
		return recipient;
	}

	public void setRecipient(String recipient) {
		this.recipient = recipient;
	}

	public byte[] getData() {
		return data;
	}

	public void setData(byte[] data) {
		this.data = data;
	}

	public Map<String, String> getHeaders() {
		return headers;
	}

	public void setHeaders(Map<String, String> headers) {
		this.headers = headers;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getHelo() {
		return helo;
	}

	public void setHelo(String helo) {
		this.helo = helo;
	}

	public String getRemoteAddress() {
		return remoteAddress;
	}

	public void setRemoteAddress(String remoteAddress) {
		this.remoteAddress = remoteAddress;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public Inbox getInbox() {
		return inbox;
	}

	public void setInbox(Inbox inbox) {
		this.inbox = inbox;
	}

	public ParsedMail getParsed() {
		return parsed;
	}

	public void setParsed(ParsedMail parsed) {
		this.parsed = parsed;
	}
	/* Getters & Setters */

}
