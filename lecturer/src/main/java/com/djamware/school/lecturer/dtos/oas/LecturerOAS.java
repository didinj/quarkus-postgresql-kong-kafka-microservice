package com.djamware.school.lecturer.dtos.oas;

import java.util.Date;

import org.eclipse.microprofile.openapi.annotations.media.Schema;

public class LecturerOAS {
    @Schema(name = "LecturerOAS.Request")
    public class Request {
        @Schema(required = true, example = "0000111")
        public String lidn;
        @Schema(required = true, example = "Elon Dust")
        public String name;
        @Schema(required = true, example = "elon.dust@x.com")
        public String email;
        @Schema(required = true, example = "+1567655665")
        public String phone;
        @Schema(required = true, example = "Math")
        public String major;
    }

    @Schema(name = "LecturerOAS.Response")
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
        @Schema(required = true, example = "Math")
        public String major;
        @Schema(required = true, example = "2024-09-21T06:50:15.445890")
        public Date createdAt;
    }

    @Schema(name = "LecturerOAS.BadRequest")
    public class BadRequest {
        @Schema(example = "BAD_REQUEST", enumeration = { "BAD_REQUEST", "INVALID_BODY" })
        public String message;
    }
}
