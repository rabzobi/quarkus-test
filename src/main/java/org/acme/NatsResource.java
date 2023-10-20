package org.acme;

import io.nats.client.Connection;
import io.nats.client.Message;
import io.nats.client.Nats;
import io.nats.client.impl.NatsMessage;
import io.quarkus.logging.Log;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;

import java.nio.charset.StandardCharsets;
import java.time.Duration;

@Path("/nats")
public class NatsResource {

    String server = "nats://10.224.61.200:4222";

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String nats(@QueryParam("subject") String subject) {
        try (Connection nc = Nats.connect(server)) {
            Message reply = nc.request(NatsMessage.builder()
                            .subject(subject)
                            .build(),
                    Duration.ofSeconds(5));
            Log.info("Reply: "+reply);
            return new String(reply.getData(), StandardCharsets.UTF_8);
        }
        catch (Exception e) {
            return e.getMessage();
        }
    }
}