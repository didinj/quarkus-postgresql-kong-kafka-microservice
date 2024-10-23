package com.djamware.school.student;

import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;

import com.djamware.school.student.dtos.StudentRequestDto;
import com.djamware.school.student.dtos.oas.StudentOAS;
import com.djamware.school.student.services.StudentService;

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

@Path("/v1/students")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class StudentResource {
    StudentService studentService;

    @Inject
    public StudentResource(StudentService studentService) {
        this.studentService = studentService;
    }

    @POST
    @Operation(summary = "Add a new Student", description = "This API will add a new Student to database")
    @RequestBody(content = {
            @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = StudentOAS.Request.class)) })
    @APIResponses(value = {
            @APIResponse(responseCode = "201", description = "Accepted", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = StudentOAS.Response.class))),
            @APIResponse(responseCode = "400", description = "Bad Request", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = StudentOAS.BadRequest.class)))
    })
    public Response addStudent(StudentRequestDto request) throws ValidationException {
        return Response.accepted().entity(studentService.saveStudent(request)).build();
    }

    @PUT
    @Path("/{id}")
    @Operation(summary = "Update Student", description = "This API will update Student to database")
    @RequestBody(content = {
            @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = StudentOAS.Request.class)) })
    @APIResponses(value = {
            @APIResponse(responseCode = "200", description = "OK", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = StudentOAS.Response.class))),
            @APIResponse(responseCode = "400", description = "Bad Request", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = StudentOAS.BadRequest.class)))
    })
    public Response updateStudent(StudentRequestDto request, @PathParam("id") Long id) throws ValidationException {
        return Response.ok().entity(studentService.updateStudent(request, id)).build();
    }

    @GET
    @Operation(summary = "Get List Student", description = "This API will get List Student from Database")
    @APIResponses(value = {
            @APIResponse(responseCode = "200", description = "OK", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = StudentOAS.Response.class))),
            @APIResponse(responseCode = "400", description = "Bad Request", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = StudentOAS.BadRequest.class)))
    })
    public Response getListStudent() {
        return Response.ok().entity(studentService.getListStudent()).build();
    }

    @GET
    @Path("/{id}")
    @Operation(summary = "Get Student By ID", description = "This API will get Student by ID from Database")
    @APIResponses(value = {
            @APIResponse(responseCode = "200", description = "OK", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = StudentOAS.Response.class))),
            @APIResponse(responseCode = "400", description = "Bad Request", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = StudentOAS.BadRequest.class)))
    })
    public Response getStudentByID(@PathParam("id") Long id) {
        return Response.ok().entity(studentService.getStudentByID(id)).build();
    }

    @GET
    @Path("/sidn/{sidn}")
    @Operation(summary = "Get Student by SIDN", description = "This API will get Student by  from Database")
    @APIResponses(value = {
            @APIResponse(responseCode = "200", description = "OK", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = StudentOAS.Response.class))),
            @APIResponse(responseCode = "400", description = "Bad Request", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = StudentOAS.BadRequest.class)))
    })
    public Response getStudentBySidn(@PathParam("sidn") String sidn) {
        return Response.ok().entity(studentService.getStudentBySidn(sidn)).build();
    }

    @DELETE
    @Path("/{id}")
    @Operation(summary = "Delete Student", description = "This API will delete Student by ID")
    @APIResponses(value = {
            @APIResponse(responseCode = "200", description = "OK", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = StudentOAS.Response.class))),
            @APIResponse(responseCode = "400", description = "Bad Request", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = StudentOAS.BadRequest.class)))
    })
    public Response deleteStudent(@PathParam("id") Long id) throws ValidationException {
        return Response.ok().entity(studentService.deleteStudent(id)).build();
    }
}
