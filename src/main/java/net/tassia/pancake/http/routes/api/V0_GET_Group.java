package net.tassia.pancake.http.routes.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import net.tassia.pancake.Pancake;
import net.tassia.pancake.http.HttpRequest;
import net.tassia.pancake.http.HttpRoute;
import net.tassia.pancake.orm.Group;
import net.tassia.pancake.orm.structs.GroupJsonStructure;

import java.util.UUID;

class V0_GET_Group implements HttpRoute {

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


        // Fetch group
        Group group = pancake.getGroup(uuid);


        // Is valid?
        if (group == null) {
            request.setErrorPage(404);
            return null;
        }


        // Generate JSON
        byte[] data;
        try {
            data = pancake.getMapper().writeValueAsBytes(new GroupJsonStructure(group));
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

}
