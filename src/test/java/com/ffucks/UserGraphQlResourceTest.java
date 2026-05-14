package com.ffucks;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

@QuarkusTest
public class UserGraphQlResourceTest {

    @Test
    void shouldCreateUser() {

        String mutation = """
            mutation {
              createUser(
                name: "Fabio",
                email: "fabio@test.com"
              ) {
                id
                name
                email
              }
            }
            """;

        given()
                .contentType(ContentType.JSON)
                .body("""
                {
                  "query": "%s"
                }
                """.formatted(mutation.replace("\"", "\\\"")))
                .when()
                .post("/graphql")
                .then()
                .statusCode(200)
                .body("data.createUser.name", equalTo("Fabio"))
                .body("data.createUser.email", equalTo("fabio@test.com"));
    }

    @Test
    void shouldListUsers() {

        String mutation = """
            mutation {
              createUser(
                name: "Maria",
                email: "maria@test.com"
              ) {
                id
              }
            }
            """;

        given()
                .contentType(ContentType.JSON)
                .body("""
                {
                  "query": "%s"
                }
                """.formatted(mutation.replace("\"", "\\\"")))
                .when()
                .post("/graphql");

        String query = """
            query {
              users {
                id
                name
                email
              }
            }
            """;

        given()
                .contentType(ContentType.JSON)
                .body("""
                {
                  "query": "%s"
                }
                """.formatted(query.replace("\"", "\\\"")))
                .when()
                .post("/graphql")
                .then()
                .statusCode(200)
                .body("data.users.size()", greaterThan(0));
    }
}
