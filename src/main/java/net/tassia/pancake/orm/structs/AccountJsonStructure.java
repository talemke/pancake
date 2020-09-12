package net.tassia.pancake.orm.structs;

import net.tassia.pancake.orm.Account;

import java.util.UUID;

public class AccountJsonStructure {

    public AccountJsonStructure() {
    }

    public AccountJsonStructure(Account account) {
        this.uuid = account.getUUID();
        this.name = account.getName();
    }

    public UUID uuid;
    public String name;

}
