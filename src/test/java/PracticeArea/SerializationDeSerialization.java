package PracticeArea;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import static io.restassured.RestAssured.given;

public class SerializationDeSerialization {

    //Serialization POJO to JSON
    @Test
    void convertpojo2json() throws JsonProcessingException {
      mainPOJO main = new mainPOJO();

      dataPOJO data = new dataPOJO();
      data.setId(1);
      data.setFirst_name("Feroz");
      data.setLast_name("khan");
      data.setEmail("abc@gmail.com");
      data.setAvatar("abc");

      supportPOJO support = new supportPOJO();
      support.setText("test");
      support.setUrl("testURL");

      main.setData(data);
      main.setSupport(support);

        ObjectMapper mapper = new ObjectMapper();
        String inputdata=mapper.writerWithDefaultPrettyPrinter().writeValueAsString(main);
        System.out.println("Result is : "+inputdata);
    }
@Test
    void convertjson2pojo() throws JsonProcessingException {
       String jsonstring ="{\n" +
               "  \"data\" : {\n" +
               "    \"id\" : 1,\n" +
               "    \"email\" : \"abc@gmail.com\",\n" +
               "    \"first_name\" : \"Feroz\",\n" +
               "    \"last_name\" : \"khan\",\n" +
               "    \"avatar\" : \"abc\"\n" +
               "  },\n" +
               "  \"support\" : {\n" +
               "    \"url\" : \"testURL\",\n" +
               "    \"text\" : \"test\"\n" +
               "  }\n" +
               "}";
       //convert json string to object
        ObjectMapper mapper = new ObjectMapper();
        mainPOJO main=mapper.readValue(jsonstring, mainPOJO.class);
       String url= main.getSupport().getUrl();
       String text = main.getSupport().getText();
        System.out.println("URL : "+url+" text :"+text);

    }
    @Test
    public void outputtofile() throws IOException {
        Response response = given()
                .baseUri("https://reqres.in/api/users/2")
                .contentType(ContentType.JSON)
                .when()
                .get();
        response.prettyPrint();
//        BufferedWriter writer = new BufferedWriter(new FileWriter("output.json"));
//        writer.write(response.prettyPrint());
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(new File("output.json"),response);

    }
}
