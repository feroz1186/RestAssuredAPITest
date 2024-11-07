package api.testCases;

import api.endpoints.UserEndpoints;
import api.payload.User;
import com.github.javafaker.Faker;
import io.restassured.response.Response;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;



public class UserTests {
   User userPayload;
   Faker faker= new Faker();
   public Logger LOG;
   @BeforeClass
    public void setup()
    {
        userPayload = new User();

        userPayload.setId(faker.idNumber().hashCode());
        userPayload.setUsername(faker.name().username());
        userPayload.setFirstName(faker.name().firstName());
        userPayload.setLastName(faker.name().lastName());
        userPayload.setEmail(faker.internet().safeEmailAddress());
        userPayload.setPhone(faker.phoneNumber().cellPhone());
        //userPayload.setUserStatus();
        userPayload.setPassword(faker.internet().password(7,10));
        LOG = LogManager.getLogger(UserTests.class);

    }

    @Test(priority = 1)
    public void testPostUser()
    {
        LOG.info("*************Post method is started******");
        Response response=UserEndpoints.createUser(userPayload);
        response.then().log().all();
        Assert.assertEquals(response.getStatusCode(),200);
        LOG.info("*************Post method is completed******");
    }
    @Test(priority = 2)
    public void testGetUser()
    {
        LOG.info("*************Get method is started******");
        Response response=UserEndpoints.readUser(userPayload.getUsername());
        response.then().log().all();
        Assert.assertEquals(response.getStatusCode(),200);
        LOG.info("*************Get method is completed******");
    }
    @Test(priority = 3)
    public void testPutUser()
    {
        LOG.info("*************Put method is started******");
        userPayload.setFirstName(faker.name().firstName());
        userPayload.setLastName(faker.name().lastName());
        userPayload.setEmail(faker.internet().safeEmailAddress());

        Response response=UserEndpoints.updateUser(userPayload, userPayload.getUsername());
        response.then().log().all();
        Assert.assertEquals(response.getStatusCode(),200);

        Response readResponse=UserEndpoints.readUser(userPayload.getUsername());
        readResponse.then().log().all();
        Assert.assertEquals(readResponse.getStatusCode(),200);

        LOG.info("*************Put method is completed******");
    }
}
