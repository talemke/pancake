package net.tassia.pancake.http.routes.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import net.tassia.pancake.Pancake;
import net.tassia.pancake.http.HttpRequest;
import net.tassia.pancake.http.HttpRoute;
import net.tassia.pancake.orm.Account;
import net.tassia.pancake.orm.structs.AccountJsonStructure;
import net.tassia.pancake.orm.structs.GroupJsonStructure;

import java.util.UUID;

class V0_GET_Account implements HttpRoute {

    @Override
    public byte[] route(Pancake pancake, HttpRequest request, String[] matches) {
        // Check auth
        // TODO


        // Parse UUID
        UUID uuid;
        try {
            uuid = UUID.fromString(matches[0]);
        } catch (IllegalArgumentException ex) {
            request.setErrorPage(404);
            return null;
        }


        // Fetch account
        Account account = pancake.getAccount(uuid);


        // Is valid?
        if (account == null) {
            request.setErrorPage(404);
            return null;
        }


        // Generate JSON
        byte[] data;
        try {
            ResponseStructure res = new ResponseStructure();
            res.account = new AccountJsonStructure(account);
            res.group = new GroupJsonStructure(account.getGroup());
            data = pancake.getMapper().writeValueAsBytes(res);
        } catch (JsonProcessingException ex) {
            ex.printStackTrace();
            request.setErrorPage(500);
            return null;
        }


        // Send response
        request.setResponseCode(200);
        return data;
    }



    private static class ResponseStructure {
        public AccountJsonStructure account = null;
        public GroupJsonStructure group = null;
    }

}
