package net.tassia.pancake.http.routes.api;

import net.tassia.pancake.http.PancakeHttpServer;

public class ApiRoutes {

    public void registerRoutes(PancakeHttpServer server) {

        // Accounts
        server.GET("\\/api\\/v0-alpha\\/accounts\\/(.*)", new GET_Account());

        // Emails
        server.GET("\\/api\\/v0-alpha\\/emails\\/(.*)", new GET_Email());

        // Groups
        server.GET("\\/api\\/v0-alpha\\/groups\\/(.*)", new GET_Group());

    }

}
