package net.tassia.pancake.orm;

import java.util.UUID;

public class Inbox {
	private final UUID uuid;
	private Account owner;
	private String name;
	private String description;
	private boolean system;

	/* Constructor */
	public Inbox() {
		this(UUID.randomUUID());
	}

	public Inbox(UUID uuid) {
		this.uuid = uuid;
		this.owner = null;
		this.name = null;
		this.description = null;
		this.system = false;
	}
	/* Constructor */





	/* Getters & Setters */
	public UUID getUUID() {
		return uuid;
	}

	public Account getOwner() {
		return owner;
	}

	public void setOwner(Account owner) {
		this.owner = owner;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean isSystem() {
		return system;
	}

	public void setSystem(boolean system) {
		this.system = system;
	}
	/* Getters & Setters */

}
