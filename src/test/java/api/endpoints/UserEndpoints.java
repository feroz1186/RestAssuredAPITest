package api.endpoints;

import api.payload.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

//created for perform CRUD operations
public class UserEndpoints {

    public static Response createUser(User payload) {
        Response response=given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(payload)
                .when()
                .post(Routes.post_url);

        return response;

    }

    public static Response readUser(String uname) {
        Response response=given()
                .pathParam("username",uname)
                .when()
                .get(Routes.get_url);

        return response;

    }
    public static Response updateUser(User payload, String UserName) {
        Response response=given()
                .pathParam("username",UserName)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(payload)
                .when()
                .put(Routes.put_url);

        return response;

    }
}
