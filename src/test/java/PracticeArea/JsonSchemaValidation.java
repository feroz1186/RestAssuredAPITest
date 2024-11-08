package PracticeArea;

import io.restassured.module.jsv.JsonSchemaValidator;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class JsonSchemaValidation {

    @Test
    public void jsonschema()
    {
        given()
                .baseUri("https://reqres.in/api/users")
                .when()
                .get()
                .then()
                .assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath("schema.json"));

    }
}
