package PracticeArea;

import com.google.gson.JsonArray;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.annotations.Test;

import java.sql.SQLOutput;
import java.util.List;

import static io.restassured.RestAssured.given;

public class ParsingJson {
    @Test(priority = 1)
    public void firstTest(){
        Response response = given()
                .baseUri("https://reqres.in/api/users")
                .contentType(ContentType.JSON)
                .get();
       // response.body().prettyPrint();
        String page= response.getBody().path("data[3].first_name");
        System.out.println("Page is : "+page);
    }

    @Test(priority = 2)
    public void secondTest(){
        Response response = given()
                .baseUri("https://reqres.in/api/users")
                .contentType(ContentType.JSON)
                .get();
        // response.body().prettyPrint();
        String page= response.jsonPath().get("data[3].first_name");
        System.out.println("Page is : "+page);

    }
    @Test(priority = 3)
    public void thirdTest(){
        Response response = given()
                .baseUri("https://reqres.in/api/users")
                .contentType(ContentType.JSON)
                .when()
                .get();
         response.body().prettyPrint();

        JSONObject jo = new JSONObject(response.asString());

        for(int i=0;i<jo.getJSONArray("data").length();i++)
        {
            String title= jo.getJSONArray("data").getJSONObject(i).get("email").toString();
            System.out.println("Email is : "+title);
        }

    }
    @Test
    public void fourthtest()
    {
        Response response = given()
                .baseUri("https://reqres.in/api/users/2")
                .contentType(ContentType.JSON)
                .when()
                .get();
        response.body().prettyPrint();
    }
}
