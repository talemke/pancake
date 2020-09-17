package net.tassia.pancake.http.routes.api;

import net.tassia.pancake.Pancake;
import net.tassia.pancake.http.PancakeHttpServer;

public class ApiRoutes {

    public void registerRoutes(PancakeHttpServer server) {

    	// Auth
		server.POST("\\/api\\/v0\\/auth\\/login", new V0_POST_Login());
		server.POST("\\/api\\/v0\\/auth\\/register", new V0_POST_Register());

    }

}
