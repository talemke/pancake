package net.tassia.pancake.orm.structs;

import net.tassia.pancake.orm.Group;

import java.util.UUID;

public class GroupJsonStructure {

    public GroupJsonStructure() {
    }

    public GroupJsonStructure(Group group) {
        this.uuid = group.getUUID();
        this.name = group.getName();
        this.description = group.getDescription();
    }

    public UUID uuid;
    public String name;
    public String description;

}
