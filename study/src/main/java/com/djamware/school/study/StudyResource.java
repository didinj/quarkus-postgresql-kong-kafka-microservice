package com.djamware.school.study;

import org.bson.types.ObjectId;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;

import com.djamware.school.study.dtos.StudyRequestDto;
import com.djamware.school.study.dtos.oas.StudyOAS;
import com.djamware.school.study.services.StudyService;
import com.fasterxml.jackson.core.JsonProcessingException;

import jakarta.inject.Inject;
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
import jakarta.xml.bind.ValidationException;

@Path("/v1/studies")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class StudyResource {
    StudyService studyService;

    @Inject
    public StudyResource(StudyService studyService) {
        this.studyService = studyService;
    }

    @POST
    @Operation(summary = "Add a new study", description = "This API will add a new study to database")
    @RequestBody(content = {
            @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = StudyOAS.Request.class)) })
    @APIResponses(value = {
            @APIResponse(responseCode = "201", description = "Accepted", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = StudyOAS.Response.class))),
            @APIResponse(responseCode = "400", description = "Bad Request", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = StudyOAS.BadRequest.class)))
    })
    public Response addLecturer(StudyRequestDto request) throws ValidationException, JsonProcessingException {
        return Response.accepted().entity(studyService.saveStudy(request)).build();
    }

    @PUT
    @Path("/{id}")
    @Operation(summary = "Update Study", description = "This API will update Study to database")
    @RequestBody(content = {
            @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = StudyOAS.Request.class)) })
    @APIResponses(value = {
            @APIResponse(responseCode = "200", description = "OK", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = StudyOAS.Response.class))),
            @APIResponse(responseCode = "400", description = "Bad Request", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = StudyOAS.BadRequest.class)))
    })
    public Response updateLecturer(StudyRequestDto request, @PathParam("id") ObjectId id) throws ValidationException {
        return Response.ok().entity(studyService.updateStudy(request, id)).build();
    }

    @GET
    @Operation(summary = "Get List Study", description = "This API will get List Study from Database")
    @APIResponses(value = {
            @APIResponse(responseCode = "200", description = "OK", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = StudyOAS.Response.class))),
            @APIResponse(responseCode = "400", description = "Bad Request", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = StudyOAS.BadRequest.class)))
    })
    public Response getListLecturer() {
        return Response.ok().entity(studyService.getListStudy()).build();
    }

    @GET
    @Path("/{id}")
    @Operation(summary = "Get Study by ID", description = "This API will get Study by ID from Database")
    @APIResponses(value = {
            @APIResponse(responseCode = "200", description = "OK", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = StudyOAS.Response.class))),
            @APIResponse(responseCode = "400", description = "Bad Request", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = StudyOAS.BadRequest.class)))
    })
    public Response getLecturerByID(@PathParam("id") ObjectId id) {
        return Response.ok().entity(studyService.getStudyByID(id)).build();
    }

    @DELETE
    @Path("/{id}")
    @Operation(summary = "Delete Study", description = "This API will delete Study by ID")
    @APIResponses(value = {
            @APIResponse(responseCode = "200", description = "OK", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = StudyOAS.Response.class))),
            @APIResponse(responseCode = "400", description = "Bad Request", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = StudyOAS.BadRequest.class)))
    })
    public Response deleteLecturer(@PathParam("id") ObjectId id) throws ValidationException {
        return Response.ok().entity(studyService.deleteStudy(id)).build();
    }
}
