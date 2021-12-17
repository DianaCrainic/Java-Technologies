package com.uaic.lab7.web_services;

import com.uaic.lab7.dtos.CreateDocumentDto;
import com.uaic.lab7.dtos.UpdateDocumentDto;
import com.uaic.lab7.dtos_rest.CreateDocumentRestDto;
import com.uaic.lab7.dtos_rest.UpdateDocumentRestDto;
import com.uaic.lab7.entities.Document;
import com.uaic.lab7.services.DocumentService;
import org.eclipse.microprofile.openapi.annotations.OpenAPIDefinition;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.enums.SchemaType;
import org.eclipse.microprofile.openapi.annotations.info.Info;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.parameters.Parameter;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponseSchema;

import javax.inject.Inject;
import javax.persistence.EntityNotFoundException;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.util.List;

@OpenAPIDefinition(info = @Info(
        title = "Document Manager App",
        version = "1.0.0"
))
@Path("/documents")
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
            @QueryParam("authorId") Integer authorId
    ) {
        try {
            List<Document> documents = this.documentService.getAll(authorId);
            return Response.ok().entity(documents).build();
        } catch (EntityNotFoundException exception) {
            return Response.status(Response.Status.NOT_FOUND).entity(exception.getMessage()).build();
        } catch (Throwable exception) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response create(CreateDocumentRestDto createDocumentRestDto, @Context UriInfo uriInfo) {
        try {
            CreateDocumentDto createDocumentDto = new CreateDocumentDto(
                    createDocumentRestDto.getName(),
                    createDocumentRestDto.getContent().getBytes(),
                    createDocumentRestDto.getAuthorId()
            );
            Document document = this.documentService.create(createDocumentDto);
            UriBuilder uriBuilder = uriInfo.getAbsolutePathBuilder();
            uriBuilder.path(Integer.toString(document.getId()));
            return Response.created(uriBuilder.build()).build();
        } catch (EntityNotFoundException exception) {
            return Response.status(Response.Status.NOT_FOUND).entity(exception.getMessage()).build();
        } catch (Throwable exception) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response update(@PathParam("id") Integer id, UpdateDocumentRestDto updateDocumentRestDto) {
        try {
            UpdateDocumentDto updateDocumentDto = new UpdateDocumentDto(
                    updateDocumentRestDto.getName(),
                    updateDocumentRestDto.getContent().getBytes()
            );
            this.documentService.update(id, updateDocumentDto);
            return Response.noContent().build();
        } catch (EntityNotFoundException exception) {
            return Response.status(Response.Status.NOT_FOUND).entity(exception.getMessage()).build();
        } catch (Throwable exception) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DELETE
    @Path("/{id}")
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
