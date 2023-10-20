package org.acme;

import io.smallrye.mutiny.Uni;
import io.vertx.mutiny.core.eventbus.EventBus;
import io.vertx.mutiny.core.eventbus.Message;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/async")
public class EventResource {

    @Inject
    EventBus bus;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("{name}")
    public Uni<String> greeting(String name) {
        return bus.<String>request("greeting", name)
                .onItem().transform(Message::body);
    }
}