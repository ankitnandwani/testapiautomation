package com.restapi.automation;

import com.restapi.automation.dto.RestApiResponse;
import com.restapi.automation.dto.User;
import org.testng.Assert;
import org.testng.annotations.Test;

public class PostApiUsersTests extends BaseTest{

    @Test
    public void statusCodeTest(){
        RestApiResponse response = sendPostApiRequest();
        Assert.assertEquals(response.getStatusCode(), CREATED_CODE);
    }

    @Test
    public void createUserTest(){
        RestApiResponse createUser = sendPostApiRequest();
        User expected = createUser.getSingleUserResponse();

        RestApiResponse getUser = sendGetApiRequest(expected.getId());
        User actual = getUser.getSingleUserResponse();

        Assert.assertEquals(actual.getId(), expected.getId());
        Assert.assertEquals(actual.getDayOfBirth(), expected.getDayOfBirth());
        Assert.assertEquals(actual.getFirstName(), expected.getFirstName());
        Assert.assertEquals(actual.getLastName(), expected.getLastName());
        Assert.assertEquals(actual.getEmail(), expected.getEmail());
    }
}
