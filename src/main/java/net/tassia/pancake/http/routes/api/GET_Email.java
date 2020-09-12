package net.tassia.pancake.http.routes.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import net.tassia.pancake.Pancake;
import net.tassia.pancake.http.HttpRequest;
import net.tassia.pancake.http.HttpRoute;
import net.tassia.pancake.orm.Email;

import java.sql.SQLException;
import java.util.UUID;

class GET_Email implements HttpRoute {

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
            data = pancake.getMapper().writeValueAsBytes(email);
        } catch (JsonProcessingException ex) {
            ex.printStackTrace();
            request.setErrorPage(500);
            return null;
        }


        // Send response
        request.setResponseCode(200);
        return data;
    }

}
