package net.tassia.pancake.orm;

import java.util.UUID;

public class Group {
	public static final Group ROOT;
	private final UUID uuid;
	private String name;
	private String description;

	/* Constructor */
	public Group() {
		this(UUID.randomUUID());
	}

	public Group(UUID uuid) {
		this.uuid = uuid;
		this.name = null;
		this.description = null;
	}
	/* Constructor */





	/* Static Initialize */
	static {
		ROOT = new Group(new UUID(0L, 0L));
		ROOT.name = "root";
		ROOT.description = "The group of the root user. This will always be the highest available group.";
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	/* Getters & Setters */

}
