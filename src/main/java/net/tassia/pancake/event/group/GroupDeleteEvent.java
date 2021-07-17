package net.tassia.pancake.event.group;

import net.tassia.pancake.entity.Group;

public class GroupDeleteEvent extends GroupEvent {

	public GroupDeleteEvent(Group group) {
		super(group);
	}

}
