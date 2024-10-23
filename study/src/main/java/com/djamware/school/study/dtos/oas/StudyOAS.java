package com.djamware.school.study.dtos.oas;

import java.util.List;

import org.bson.types.ObjectId;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

public class StudyOAS {
    @Schema(name = "StudyOAS.Request")
    public class Request {
        @Schema(required = true, example = "DSA")
        public String studyName;
        @Schema(required = true, example = "Data Structure and Algorithm")
        public String studyDescription;
        @Schema(required = true, example = "2024-10-18 09:00:00")
        public String studyDate;
        @Schema(required = true, example = "A12345")
        public String lidn;
        @Schema(required = true, example = "S0001,S0002,S003")
        public List<String> sidn;
    }

    @Schema(name = "StudyOAS.Response")
    public class Response {
        @Schema(required = true, example = "1")
        public ObjectId id;
        @Schema(required = true, example = "DSA")
        public String studyName;
        @Schema(required = true, example = "Data Structure and Algorithm")
        public String studyDescription;
        @Schema(required = true, example = "2024-10-18 09:00:00")
        public String studyDate;
        @Schema(required = true, example = "A12345")
        public String lidn;
        @Schema(required = true, example = "S0001,S0002,S003")
        public List<String> sidn;
    }

    @Schema(name = "StudyOAS.BadRequest")
    public class BadRequest {
        @Schema(example = "BAD_REQUEST", enumeration = { "BAD_REQUEST", "INVALID_BODY" })
        public String message;
    }
}
