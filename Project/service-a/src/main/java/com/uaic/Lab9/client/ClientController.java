package com.uaic.Lab9.client;

import org.eclipse.microprofile.rest.client.inject.RestClient;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

@Path("/v1/books")
@ApplicationScoped
public class ClientController {
    @Inject
    @RestClient
    private Service service;

    @GET
    public Response onClientSide() {
        return service.getBooks();
    }
}
