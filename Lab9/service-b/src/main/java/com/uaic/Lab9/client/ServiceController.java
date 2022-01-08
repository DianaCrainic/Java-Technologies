package com.uaic.Lab9.client;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

@ApplicationScoped
@Path("/hello")
public class ServiceController {
    @GET
    public String hello() {
        return "Hello World";
    }
}
