package org.acme;


import io.nats.client.Connection;
import io.nats.client.Message;
import io.nats.client.Nats;
import io.nats.client.Subscription;

import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.Date;

//@QuarkusMain
public class NatsResponse {

    private static String server = "nats://10.224.61.200:4222";

    private static String subject = "greeter";

    public static void main(String[] args) {
        try (Connection nc = Nats.connect(server)) {
            Subscription sub = nc.subscribe(subject);
            while (true) {
                Message msg = sub.nextMessage(Duration.ofMillis(500));
                if (msg != null) {
                    String request = new String(msg.getData(), StandardCharsets.UTF_8);
                    String response = "Hi there Earthling, it's been "+ (new Date()).getTime() +" seconds since the epoch";
                    System.out.printf("\nrequest=%s response=%s",request,response);
                    nc.publish(msg.getReplyTo(), response.getBytes());
                    System.out.println();
                } else {
                    System.out.print(".");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}