package net.tassia.pancake.http.routes.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import net.tassia.pancake.Pancake;
import net.tassia.pancake.http.HttpRequest;
import net.tassia.pancake.http.HttpRoute;

import java.io.IOException;
import java.sql.SQLException;

class V0_POST_Register implements HttpRoute {

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


		// Validate username & password
		if (req.username.length() < 3) {
			res.error = "Username is too short (<3).";

		} else if (req.username.length() > 15) {
			res.error = "Username is too long (>15).";

		} else if (req.password.length() < 6) {
			res.error = "Password is too short (<6).";

		} else if (req.password.length() > 63) {
			res.error = "Password is too long (>63).";

		}

		if (!req.username.matches("[A-Za-z0-9]]+")) {
			res.error = "Username must be alphanumeric.";
		} else if (res.error == null && pancake.getAccountByUsername(req.username) != null) {
			res.error = "Username is already in use.";
		}


		// Create account
		if (res.error == null) {
			try {
				pancake.createAccount(req.username, req.password);
			} catch (SQLException ex) {
				ex.printStackTrace();
				request.setErrorPage(500);
				return null;
			}
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
		request.setContentType("application/json");
        request.setResponseCode(200);
        return data;
    }



    private static class RequestStructure {
    	public String username = null;
    	public String password = null;
	}

    private static class ResponseStructure {
    	public String error = null;
    }

}
