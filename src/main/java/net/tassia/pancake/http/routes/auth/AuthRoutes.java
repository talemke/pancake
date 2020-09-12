package net.tassia.pancake.http.routes.auth;

import net.tassia.pancake.http.PancakeHttpServer;

public class AuthRoutes {

    public void registerRoutes(PancakeHttpServer server) {

        // Login
        server.GET("\\/auth\\/login", new GET_Login());

        // Register
        server.GET("\\/auth\\/register", new GET_Register());

    }

}
