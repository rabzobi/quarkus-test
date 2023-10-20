package org.acme;

import io.vertx.mutiny.core.eventbus.EventBus;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;

@Path("/hello")
public class GreetingResource {

    @Inject
    EventBus bus;
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello(@QueryParam("message") String message) {
        String m = message + " " + System.currentTimeMillis();
        NatsReq.sendTest(m);
        bus.send("greeting",m);
        return m;
    }
}
