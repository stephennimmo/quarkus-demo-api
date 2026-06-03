package com.stephennimmo.quarkusdemoapi.api;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.Matchers.notNullValue;

@QuarkusTest
class StressResourceTest {

    @Test
    void stress() {
        given()
                .when().get("/api/v1/stress/1")
                .then()
                .statusCode(200)
                .body("seconds", is(1))
                .body("threads", is(1))
                .body("elapsedMs", greaterThanOrEqualTo(1000));
    }

    @Test
    void stressWithThreads() {
        given()
                .when().get("/api/v1/stress/1?threads=2")
                .then()
                .statusCode(200)
                .body("seconds", is(1))
                .body("threads", is(2))
                .body("elapsedMs", notNullValue());
    }

    @Test
    void stressInvalidSeconds() {
        given()
                .when().get("/api/v1/stress/0")
                .then()
                .statusCode(400)
                .body("error", notNullValue());
    }

    @Test
    void stressInvalidThreads() {
        given()
                .when().get("/api/v1/stress/1?threads=0")
                .then()
                .statusCode(400)
                .body("error", notNullValue());
    }

}
