package api.testCases;


import api.endpoints.UserEndpoints;
import api.payload.User;
import api.*;
import api.utilities.DataProviders;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class DDUserTests {
public Logger log;

    @BeforeClass
    public void setup()
    {
        log = LogManager.getLogger(DDUserTests.class);
    }
    @Test(priority = 1, dataProvider = "Data", dataProviderClass = DataProviders.class)
    public void postUser(String UserId,String UserName,String FirstName,String LastName,String Email,String Password,String Phone)
    {
        User upayload = new User();
        //User userPayload = new User();

        upayload.setId(Integer.parseInt(UserId));
        upayload.setUsername(UserName);
        upayload.setFirstName(FirstName);
        upayload.setLastName(LastName);
        upayload.setEmail(Email);
        upayload.setPhone(Phone);
        //userPayload.setUserStatus();
        upayload.setPassword(Password);

        Response response = UserEndpoints.createUser(upayload);
        Assert.assertEquals(response.getStatusCode(),200);
        log.info("DataDriven is executed...");
    }
}
