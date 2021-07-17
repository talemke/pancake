package net.tassia.pancake.event.group;

import net.tassia.pancake.entity.Group;

public class GroupCreateEvent extends GroupEvent {

	public GroupCreateEvent(Group group) {
		super(group);
	}

}
