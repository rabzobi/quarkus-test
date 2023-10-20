package org.acme;

import io.nats.client.Connection;
import io.nats.client.Message;
import io.nats.client.Nats;
import io.nats.client.impl.NatsMessage;

import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.time.LocalDateTime;

public class NatsReq {

    public static void sendTest(String message) {

        try (Connection nc = Nats.connect("nats://10.224.61.200:4222")) {

            Message reply = nc.request(NatsMessage.builder()
                            .subject("greeter")
                            //.headers(exArgs.headers)
                            .data(message, StandardCharsets.UTF_8)
                            .build(),
                    Duration.ofSeconds(5));

        }
        catch (Exception e) {
            System.err.println(e);
        }
    }
}