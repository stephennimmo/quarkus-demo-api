package com.stephennimmo.quarkusdemoapi.api;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
class HelloResourceTest {

    @Test
    void hello() {
        given()
                .when().get("/api/v1/hello")
                .then()
                .statusCode(200)
                .body(is("Hello, World!"));
    }

    @Test
    void helloName() {
        given()
                .when().get("/api/v1/hello/Quarkus")
                .then()
                .statusCode(200)
                .body(is("Hello, Quarkus!"));
    }

    @Test
    void helloNameWithSpecialCharacters() {
        given()
                .when().get("/api/v1/hello/O'Brien")
                .then()
                .statusCode(200)
                .body(is("Hello, O'Brien!"));
    }

}
