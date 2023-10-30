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
import org.eclipse.microprofile.config.inject.ConfigProperty;

import java.nio.charset.StandardCharsets;
import java.time.Duration;

@Path("/nats")
public class NatsRequest {

    @ConfigProperty(name = "nats.server")
    String server;
    @ConfigProperty(name = "default.subject")
    String defaultSubject;
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String nats(@QueryParam("subject") String subject) {

        String sub = subject.isEmpty() ? defaultSubject : subject;

        try (Connection nc = Nats.connect(server)) {
            Message reply = nc.request(NatsMessage.builder()
                            .subject(sub)
                            .data("Hi")
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