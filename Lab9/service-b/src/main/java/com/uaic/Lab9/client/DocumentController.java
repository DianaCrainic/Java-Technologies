package com.uaic.Lab9.client;

import com.uaic.Lab9.dtos.CreateDocumentDto;
import com.uaic.Lab9.dtos.UpdateDocumentDto;
import com.uaic.Lab9.entities.Document;
import com.uaic.Lab9.services.DocumentService;
import org.eclipse.microprofile.metrics.annotation.Counted;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.enums.SchemaType;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.parameters.Parameter;
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
@Path("/v1/documents")
public class DocumentController {
    @Inject
    private DocumentService documentService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @APIResponseSchema(
            value = List.class,
            responseDescription = "Uploaded Documents",
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
            summary = "Get all documents"
    )
    public Response getAll(
            @Parameter(
                    description = "The id of the author to filter documents by",
                    example = "1",
                    schema = @Schema(type = SchemaType.NUMBER)
            )
            @QueryParam("authorUsername") String authorUsername
    ) {
        try {
            List<Document> documents = this.documentService.getAll(authorUsername);
            return Response.ok().entity(documents).build();
        } catch (EntityNotFoundException exception) {
            return Response.status(Response.Status.NOT_FOUND).entity(exception.getMessage()).build();
        } catch (Throwable exception) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(exception.getMessage()).build();
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public Response create(CreateDocumentDto createDocumentDto, @Context UriInfo uriInfo) {
        try {
            Document document = this.documentService.create(createDocumentDto);
            UriBuilder uriBuilder = uriInfo.getAbsolutePathBuilder();
            uriBuilder.path(Integer.toString(document.getId()));
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
    @Counted
    @Transactional
    public Response update(@PathParam("id") Integer id, UpdateDocumentDto updateDocumentDto) {
        try {
            this.documentService.update(id, updateDocumentDto);
            return Response.noContent().build();
        } catch (EntityNotFoundException exception) {
            return Response.status(Response.Status.NOT_FOUND).entity(exception.getMessage()).build();
        } catch (Throwable exception) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(exception.getMessage()).build();
        }
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    public Response remove(@PathParam("id") Integer id) {
        try {
            this.documentService.remove(id);
            return Response.noContent().build();
        } catch (EntityNotFoundException exception) {
            return Response.status(Response.Status.NOT_FOUND).entity(exception.getMessage()).build();
        } catch (Throwable exception) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }
}
