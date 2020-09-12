package net.tassia.pancake.orm.structs;

import net.tassia.pancake.orm.Email;

import java.util.UUID;

public class EmailJsonStructure {

    public EmailJsonStructure() {
    }

    public EmailJsonStructure(Email email) {
        this.uuid = email.getUUID();
        this.timestamp = email.getTimestamp();
        this.sender = email.getSender();
        this.recipient = email.getRecipient();
        this.data = email.getData();
        this.helo = email.getHelo();
        this.remoteAddress = email.getRemoteAddress();
        this.deleted = email.isDeleted();
        this.draft = email.isDraft();
        this.outgoing = email.isOutgoing();
    }

    public UUID uuid;
    public long timestamp;
    public String sender;
    public String recipient;
    public byte[] data;
    public String helo;
    public String remoteAddress;
    public boolean deleted;
    public boolean draft;
    public boolean outgoing;

}
