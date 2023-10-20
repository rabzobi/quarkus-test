package org.acme;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Path("/hello")
public class GreetingResource {
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello(@QueryParam("name") String name,@QueryParam("birth") String birth) {
        long days = ChronoUnit.DAYS.between(LocalDate.parse(birth), LocalDate.now());
        return "Hello " +name + ". You are " + days + " days old!";
    }
}
