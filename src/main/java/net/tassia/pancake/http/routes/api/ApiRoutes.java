package net.tassia.pancake.http.routes.api;

import net.tassia.pancake.http.PancakeHttpServer;

public class ApiRoutes {

    public void registerRoutes(PancakeHttpServer server) {
        server.GET("\\/api\\/emails\\/(.*)", new GET_Email());
    }

}
