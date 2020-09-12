package net.tassia.pancake.http.routes.api;

import net.tassia.pancake.http.PancakeHttpServer;

public class ApiRoutes {

    public void registerRoutes(PancakeHttpServer server) {

        // Accounts
        server.GET("\\/api\\/accounts\\/(.*)", new GET_Account());

        // Emails
        server.GET("\\/api\\/emails\\/(.*)", new GET_Email());

    }

}
