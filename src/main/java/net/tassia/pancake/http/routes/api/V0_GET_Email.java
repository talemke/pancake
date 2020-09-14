package net.tassia.pancake.http.routes.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import net.tassia.pancake.Pancake;
import net.tassia.pancake.http.HttpRequest;
import net.tassia.pancake.http.HttpRoute;
import net.tassia.pancake.orm.Email;
import net.tassia.pancake.orm.structs.AccountJsonStructure;
import net.tassia.pancake.orm.structs.EmailJsonStructure;
import net.tassia.pancake.orm.structs.InboxJsonStructure;

import java.sql.SQLException;
import java.util.UUID;

class V0_GET_Email implements HttpRoute {

    @Override
    public byte[] route(Pancake pancake, HttpRequest request, String[] matches) {
        // Check auth
		if (!request.checkAuth()) {
			request.setErrorPage(403);
			return null;
		}


        // Parse UUID
        UUID uuid;
        try {
            uuid = UUID.fromString(matches[0]);
        } catch (IllegalArgumentException ex) {
            request.setErrorPage(404);
            return null;
        }


        // Fetch email
        Email email;
        try {
            email = pancake.getDatabase().fetchEmail(uuid);
        } catch (SQLException ex) {
            ex.printStackTrace();
            request.setErrorPage(500);
            return null;
        }


        // Is valid?
        if (email == null) {
            request.setErrorPage(404);
            return null;
        }


        // Generate JSON
        byte[] data;
        try {
            ResponseStructure res = new ResponseStructure();
            res.email = new EmailJsonStructure(email);
            if (email.getAccount() != null) res.owner = new AccountJsonStructure(email.getAccount());
            if (email.getInbox() != null) res.inbox = new InboxJsonStructure(email.getInbox());
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
        public EmailJsonStructure email = null;
        public AccountJsonStructure owner = null;
        public InboxJsonStructure inbox = null;
    }

}
