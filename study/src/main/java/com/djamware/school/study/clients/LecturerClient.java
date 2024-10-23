package com.djamware.school.study.clients;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import com.djamware.school.study.clients.dtos.LecturerResponseDto;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@RegisterRestClient(configKey = "lecturer-api")
public interface LecturerClient {
    @GET
    @Path("/api/v1/lecturers/lidn/{lidn}")
    LecturerResponseDto getLecturerByLidn(@PathParam("lidn") String lidn);
}
