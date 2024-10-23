package com.djamware.school.student.dtos.oas;

import java.util.Date;

import org.eclipse.microprofile.openapi.annotations.media.Schema;

public class StudentOAS {
    @Schema(name = "StudentOAS.Request")
    public class Request {
        @Schema(required = true, example = "0000111")
        public String sidn;
        @Schema(required = true, example = "Elon Dust")
        public String name;
        @Schema(required = true, example = "elon.dust@x.com")
        public String email;
        @Schema(required = true, example = "+1567655665")
        public String phone;
    }

    @Schema(name = "StudentOAS.Response")
    public class Response {
        @Schema(required = true, example = "1")
        public Long id;
        @Schema(required = true, example = "0000111")
        public String sidn;
        @Schema(required = true, example = "Elon Dust")
        public String name;
        @Schema(required = true, example = "elon.dust@x.com")
        public String email;
        @Schema(required = true, example = "+1567655665")
        public String phone;
        @Schema(required = true, example = "2024-09-21T06:50:15.445890")
        public Date createdAt;
    }

    @Schema(name = "StudentOAS.BadRequest")
    public class BadRequest {
        @Schema(example = "BAD_REQUEST", enumeration = { "BAD_REQUEST", "INVALID_BODY" })
        public String message;
    }
}
