package net.tassia.pancake.event.group;

import net.tassia.pancake.entity.Group;
import net.tassia.pancake.event.PancakeEvent;

public class GroupEvent extends PancakeEvent {

	public final Group group;

	public GroupEvent(Group group) {
		this.group = group;
	}

}
