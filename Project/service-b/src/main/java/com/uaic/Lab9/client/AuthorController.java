package com.uaic.Lab9.client;

import com.uaic.Lab9.dtos.AuthorDto;
import com.uaic.Lab9.entities.Author;
import com.uaic.Lab9.services.AuthorService;
import org.eclipse.microprofile.metrics.annotation.Counted;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponseSchema;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.util.List;

@ApplicationScoped
@Path("/v1/authors")
public class AuthorController {
    @Inject
    private AuthorService authorService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @APIResponseSchema(
            value = List.class,
            responseDescription = "Uploaded Authors",
            responseCode = "200"
    )
    @APIResponse(
            responseCode = "404",
            description = "Author not found"
    )
    @APIResponse(
            responseCode = "500",
            description = "Server error"
    )
    @Operation(
            summary = "Get all authors"
    )
    public Response getAll() {
        try {
            List<Author> authors = this.authorService.getAll();
            return Response.ok().entity(authors).build();
        } catch (EntityNotFoundException exception) {
            return Response.status(Response.Status.NOT_FOUND).entity(exception.getMessage()).build();
        } catch (Throwable exception) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(exception.getMessage()).build();
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Operation(
            summary = "Create an author"
    )
    @Transactional
    public Response create(AuthorDto authorDto, @Context UriInfo uriInfo) {
        try {
            Author author = this.authorService.create(authorDto);
            UriBuilder uriBuilder = uriInfo.getAbsolutePathBuilder();
            uriBuilder.path(Integer.toString(author.getId()));
            return Response.created(uriBuilder.build()).build();
        } catch (EntityNotFoundException exception) {
            return Response.status(Response.Status.NOT_FOUND).entity(exception.getMessage()).build();
        } catch (Throwable exception) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(exception.getMessage()).build();
        }
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Operation(
            summary = "Update an author"
    )
    @Counted
    @Transactional
    public Response update(@PathParam("id") Integer id, AuthorDto authorDto) {
        try {
            this.authorService.update(id, authorDto);
            return Response.noContent().build();
        } catch (EntityNotFoundException exception) {
            return Response.status(Response.Status.NOT_FOUND).entity(exception.getMessage()).build();
        } catch (Throwable exception) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(exception.getMessage()).build();
        }
    }

    @DELETE
    @Path("/{id}")
    @Operation(
            summary = "Delete an author"
    )
    @Transactional
    public Response remove(@PathParam("id") Integer id) {
        try {
            this.authorService.remove(id);
            return Response.noContent().build();
        } catch (EntityNotFoundException exception) {
            return Response.status(Response.Status.NOT_FOUND).entity(exception.getMessage()).build();
        } catch (Throwable exception) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }
}
