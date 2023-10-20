package org.acme;

import io.nats.client.AuthHandler;
import io.nats.client.Connection;
import io.nats.client.ConnectionListener;
import io.nats.client.ErrorListener;
import io.nats.client.Nats;
import io.nats.client.Options;
import io.quarkus.logging.Log;
import io.quarkus.vertx.ConsumeEvent;

import jakarta.enterprise.context.ApplicationScoped;

import java.time.Duration;

@ApplicationScoped
public class GreetingService {

    @ConsumeEvent("greeting")
    public void consume(String name) {
        Log.info("received message: "+name);
    }


}