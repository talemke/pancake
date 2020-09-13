package net.tassia.pancake.http.routes.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import net.tassia.pancake.Pancake;
import net.tassia.pancake.http.HttpRequest;
import net.tassia.pancake.http.HttpRoute;
import net.tassia.pancake.orm.Account;
import net.tassia.pancake.orm.structs.AccountJsonStructure;
import net.tassia.pancake.orm.structs.GroupJsonStructure;

import java.io.IOException;
import java.util.UUID;

class V0_POST_Register implements HttpRoute {

    @Override
    public byte[] route(Pancake pancake, HttpRequest request, String[] matches) {
        // Check auth
        // TODO


        // Fetch payload
		RequestStructure req;
		try {
			req = pancake.getMapper().readValue(request.getRequestBody(), RequestStructure.class);
		} catch (IOException ex) {
			request.setErrorPage(400);
			return null;
		}


		// Prepare response
		ResponseStructure res = new ResponseStructure();


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
    }

}
