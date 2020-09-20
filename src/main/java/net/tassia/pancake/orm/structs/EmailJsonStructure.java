package net.tassia.pancake.orm.structs;

import net.tassia.pancake.orm.Mail;

import java.util.UUID;

public class EmailJsonStructure {

    public EmailJsonStructure() {
    }

    public EmailJsonStructure(Mail mail) {
        this.uuid = mail.getUUID();
        this.timestamp = mail.getTimestamp();
        this.sender = mail.getSender();
        this.recipient = mail.getRecipient();
        this.data = mail.getData();
        this.helo = mail.getHelo();
        this.remoteAddress = mail.getRemoteAddress();
        this.type = mail.getType();
    }

    public UUID uuid;
    public long timestamp;
    public String sender;
    public String recipient;
    public byte[] data;
    public String helo;
    public String remoteAddress;
    public int type;

}
