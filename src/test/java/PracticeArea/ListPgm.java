package PracticeArea;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.util.Arrays;
import java.util.List;

import static io.restassured.RestAssured.given;

public class ListPgm {
    public static void main(String[] args) {
        List<String> items = Arrays.asList("Hello","Apple","Magic","Super","Hello");
        boolean result=items.contains("Apples");
        System.out.println("Result is : "+result);

        //Get method
        Response response = given()
                .baseUri("https://reqres.in/api/users")
                .contentType(ContentType.JSON)
                .get();
        response.body().prettyPrint();
        String page= response.getBody().path("data[3].first_name");
        System.out.println("Page is : "+page);


      //response.prettyPrint();
    }
}
