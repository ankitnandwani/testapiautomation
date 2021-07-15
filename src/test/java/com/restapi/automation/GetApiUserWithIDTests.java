package com.restapi.automation;

import com.restapi.automation.dto.RestApiResponse;
import org.testng.Assert;
import org.testng.annotations.Test;

public class GetApiUserWithIDTests extends BaseTest{

    @Test
    public void statusCodeTest(){
        RestApiResponse response = sendGetApiRequest(21);
        Assert.assertEquals(response.getStatusCode(), SUCCESS_CODE);
    }

    @Test
    public void existingUserTest(){
        int totalUsers = sendGetApiRequest().getApiResponseBody().getPage().getTotalElements();
        int userId = RandomUtils.randomNumber(totalUsers);
        logger.info("Random User selected for test = " + userId);
        RestApiResponse response = sendGetApiRequest(userId);

        Assert.assertEquals(response.getStatusCode(), SUCCESS_CODE);
        Assert.assertEquals(response.getSingleUserResponse().getId(), userId);
        Assert.assertNotNull(response.getSingleUserResponse().getDayOfBirth());
        Assert.assertNotNull(response.getSingleUserResponse().getFirstName());
        Assert.assertNotNull(response.getSingleUserResponse().getLastName());
        Assert.assertNotNull(response.getSingleUserResponse().getEmail());
    }

    @Test
    public void nonExistingUserTest(){
        int totalUsers = sendGetApiRequest().getApiResponseBody().getPage().getTotalElements();
        RestApiResponse response = sendGetApiRequest(totalUsers+1);

        Assert.assertEquals(response.getStatusCode(), NOT_FOUND);
    }
}
