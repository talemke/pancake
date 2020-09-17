package net.tassia.pancake.orm;

import java.util.ArrayList;
import java.util.Collection;
import java.util.UUID;

public class Account {
	public static final Account ROOT;
	private final UUID uuid;
	private String name;
	private String password;
	private Group group;
	private long flags;
	private Collection<Inbox> inboxes;

	/* Constructors */
	public Account() {
		this(UUID.randomUUID());
	}

	public Account(UUID uuid) {
		this.uuid = uuid;
		this.name = null;
		this.password = null;
		this.group = null;
		this.flags = 0;
		this.inboxes = new ArrayList<>();
	}
	/* Constructors */





	/* Static Initialize */
	static {
		ROOT = new Account(new UUID(0L, 0L));
		ROOT.name = "root";
		ROOT.password = ""; // Leave this empty: No one can ever sign in to this account using a password.
		ROOT.group = Group.ROOT;
	}
	/* Static Initialize */





	/* Getters & Setters */
	public UUID getUUID() {
		return uuid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Group getGroup() {
		return group;
	}

	public void setGroup(Group group) {
		this.group = group;
	}

	public long getFlags() {
		return flags;
	}

	public void setFlags(long flags) {
		this.flags = flags;
	}

	public Collection<Inbox> getInboxes() {
		return inboxes;
	}

	public void setInboxes(Collection<Inbox> inboxes) {
		this.inboxes = inboxes;
	}
	/* Getters & Setters */



	/* Flags */
	public boolean hasFlag(long flag) {
		return (flags & flag) == flag;
	}

	public void addFlag(long flag) {
		flags |= flag;
	}

	public void removeFlag(long flag) {
		flags &= ~flag;
	}
	/* Flags */



	/* Inboxes */
	public Inbox getInbox(UUID uuid) {
		for (Inbox box : inboxes) {
			if (box.getUUID().equals(uuid)) {
				return box;
			}
		}
		return null;
	}
	/* Inboxes */

}
