package net.tassia.pancake.orm;

import java.util.UUID;

public class Email {
	private final UUID uuid;
	private long timestamp;
	private String sender;
	private String recipient;
	private byte[] data;
	private String helo;
	private String remoteAddress;
	private boolean deleted;
	private boolean draft;
	private boolean outgoing;
	private Account account;
	private Inbox inbox;

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

	public boolean isDeleted() {
		return deleted;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}

	public boolean isDraft() {
		return draft;
	}

	public void setDraft(boolean draft) {
		this.draft = draft;
	}

	public boolean isOutgoing() {
		return outgoing;
	}

	public void setOutgoing(boolean outgoing) {
		this.outgoing = outgoing;
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
	/* Getters & Setters */

}
