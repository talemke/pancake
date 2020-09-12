package net.tassia.pancake.orm.structs;

import net.tassia.pancake.orm.Account;
import net.tassia.pancake.orm.Group;

import java.util.UUID;

public class AccountJsonStructure {

    public AccountJsonStructure() {
    }

    public AccountJsonStructure(Account account) {
        this.uuid = account.getUUID();
        this.name = account.getName();
        this.group = new GroupJsonStructure(account.getGroup());
    }

    public UUID uuid;
    public String name;
    public GroupJsonStructure group;

}
