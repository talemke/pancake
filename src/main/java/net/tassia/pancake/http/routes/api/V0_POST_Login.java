package net.tassia.pancake.http.routes.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import net.tassia.pancake.Pancake;
import net.tassia.pancake.http.HttpRequest;
import net.tassia.pancake.http.HttpRoute;
import net.tassia.pancake.orm.Account;

import java.io.IOException;
import java.sql.SQLException;

class V0_POST_Login implements HttpRoute {

    @Override
    public byte[] route(Pancake pancake, HttpRequest request, String[] matches) {
		// Check auth
		if (request.checkAuth()) {
			request.setErrorPage(403);
			return null;
		}


        // Fetch payload
		RequestStructure req;
		try {
			req = pancake.getMapper().readValue(request.getRequestBody(), RequestStructure.class);
		} catch (IOException ex) {
			request.setErrorPage(400);
			return null;
		}
		req.username = req.username.trim();
		req.password = req.password.trim();


		// Prepare response
		ResponseStructure res = new ResponseStructure();


		// Attempt login
		Account acc = pancake.getSecurity().attemptLogin(req.username, req.password);
		if (acc != null) {
			String token = pancake.getSecurity().generateSessionID();
			pancake.getHTTP().addSession(token, acc);
			res.token = token;
		} else {
			res.error = "Wrong username or password.";
		}


        // Generate JSON
        byte[] data;
        try {
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



    private static class RequestStructure {
    	public String username = null;
    	public String password = null;
	}

    private static class ResponseStructure {
    	public String error = null;
    	public String token = null;
    }

}
