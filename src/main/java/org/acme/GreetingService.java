package org.acme;

import io.quarkus.vertx.ConsumeEvent;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class GreetingService {

    @ConsumeEvent
    public String consume(String name) {
        return name.toUpperCase();
    }
}