package com.uaic.Lab9.client;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.GET;
import javax.ws.rs.core.Response;

@RegisterRestClient
@ApplicationScoped
public interface Service {
    @GET
    Response getDocuments();
}
