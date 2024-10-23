package com.djamware.school.lecturer;

import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;

import com.djamware.school.lecturer.dtos.LecturerRequestDto;
import com.djamware.school.lecturer.dtos.oas.LecturerOAS;
import com.djamware.school.lecturer.services.LecturerService;

import jakarta.inject.Inject;
import jakarta.validation.ValidationException;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/v1/lecturers")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class LecturerResource {
    LecturerService lecturerService;

    @Inject
    public LecturerResource(LecturerService lecturerService) {
        this.lecturerService = lecturerService;
    }

    @POST
    @Operation(summary = "Add a new Lecturer", description = "This API will add a new Lecturer to database")
    @RequestBody(content = {
            @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = LecturerOAS.Request.class)) })
    @APIResponses(value = {
            @APIResponse(responseCode = "201", description = "Accepted", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = LecturerOAS.Response.class))),
            @APIResponse(responseCode = "400", description = "Bad Request", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = LecturerOAS.BadRequest.class)))
    })
    public Response addLecturer(LecturerRequestDto request) throws ValidationException {
        return Response.accepted().entity(lecturerService.saveLecturer(request)).build();
    }

    @PUT
    @Path("/{id}")
    @Operation(summary = "Update Lecturer", description = "This API will update Lecturer to database")
    @RequestBody(content = {
            @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = LecturerOAS.Request.class)) })
    @APIResponses(value = {
            @APIResponse(responseCode = "200", description = "OK", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = LecturerOAS.Response.class))),
            @APIResponse(responseCode = "400", description = "Bad Request", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = LecturerOAS.BadRequest.class)))
    })
    public Response updateLecturer(LecturerRequestDto request, @PathParam("id") Long id) throws ValidationException {
        return Response.ok().entity(lecturerService.updateLecturer(request, id)).build();
    }

    @GET
    @Operation(summary = "Get List Lecturer", description = "This API will get List Lecturer from Database")
    @APIResponses(value = {
            @APIResponse(responseCode = "200", description = "OK", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = LecturerOAS.Response.class))),
            @APIResponse(responseCode = "400", description = "Bad Request", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = LecturerOAS.BadRequest.class)))
    })
    public Response getListLecturer() {
        return Response.ok().entity(lecturerService.getListLecturer()).build();
    }

    @GET
    @Path("/{id}")
    @Operation(summary = "Get Lecturer by ID", description = "This API will get Lecturer by ID from Database")
    @APIResponses(value = {
            @APIResponse(responseCode = "200", description = "OK", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = LecturerOAS.Response.class))),
            @APIResponse(responseCode = "400", description = "Bad Request", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = LecturerOAS.BadRequest.class)))
    })
    public Response getLecturerByID(@PathParam("id") Long id) {
        return Response.ok().entity(lecturerService.getLecturerByID(id)).build();
    }

    @GET
    @Path("/lidn/{lidn}")
    @Operation(summary = "Get Lecturer by LIDN", description = "This API will get Lecturer by LIDN from Database")
    @APIResponses(value = {
            @APIResponse(responseCode = "200", description = "OK", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = LecturerOAS.Response.class))),
            @APIResponse(responseCode = "400", description = "Bad Request", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = LecturerOAS.BadRequest.class)))
    })
    public Response getLecturerByLidn(@PathParam("lidn") String lidn) {
        return Response.ok().entity(lecturerService.getLecturerByLidn(lidn)).build();
    }

    @DELETE
    @Path("/{id}")
    @Operation(summary = "Delete Lecturer", description = "This API will delete Lecturer by ID")
    @APIResponses(value = {
            @APIResponse(responseCode = "200", description = "OK", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = LecturerOAS.Response.class))),
            @APIResponse(responseCode = "400", description = "Bad Request", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = LecturerOAS.BadRequest.class)))
    })
    public Response deleteLecturer(@PathParam("id") Long id) throws ValidationException {
        return Response.ok().entity(lecturerService.deleteLecturer(id)).build();
    }
}
