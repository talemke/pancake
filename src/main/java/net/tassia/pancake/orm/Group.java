package net.tassia.pancake.orm;

import net.tassia.pancake.Pancake;

import java.util.UUID;

public class Group {
	public static final Group ROOT;
	public static final Group USER;
	private final UUID uuid;
	private String name;
	private String description;
	private long flags;

	/* Constructor */
	public Group() {
		this(UUID.randomUUID());
	}

	public Group(UUID uuid) {
		this.uuid = uuid;
		this.name = null;
		this.description = null;
		this.flags = 0;
	}
	/* Constructor */





	/* Static Initialize */
	static {
		ROOT = new Group(new UUID(-1L, -1L));
		ROOT.addFlag(Pancake.FLAG_GROUP_ADMIN);
		ROOT.name = "root";
		ROOT.description = "The group of the root user. This will always be the highest available group.";

		USER = new Group(new UUID(0L, 0L));
		USER.addFlag(Pancake.FLAG_GROUP_ADMIN);
		USER.name = "user";
		USER.description = "The default group for new users.";
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

	public long getFlags() {
		return flags;
	}

	public void setFlags(long flags) {
		this.flags = flags;
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



	/* Equals */
	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Group)) return false;
		Group g = (Group) obj;
		return uuid.equals(g.uuid);
	}

	public boolean isRoot() {
		return equals(Group.ROOT);
	}
	/* Equals */

}
