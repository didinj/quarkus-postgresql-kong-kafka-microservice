package com.djamware.school.student;

import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusTest;
import static io.restassured.RestAssured.given;

@QuarkusTest
class StudentResourceTest {
    @Test
    void testHelloEndpoint() {
        given()
                .when().get("/api/v1/students")
                .then()
                .statusCode(404);
    }

}