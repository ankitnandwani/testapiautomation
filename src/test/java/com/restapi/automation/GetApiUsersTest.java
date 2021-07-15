package com.restapi.automation;

import com.restapi.automation.dto.RestApiResponse;
import org.testng.Assert;
import org.testng.annotations.Test;

public class GetApiUsersTest extends BaseTest{

    @Test
    public void statusCodeTest(){
        RestApiResponse response = sendGetApiRequest();
        Assert.assertEquals(response.getStatusCode(), SUCCESS_CODE);
    }

    @Test
    public void verifyData(){
        RestApiResponse response = sendGetApiRequest();
        long countOfUsersOnPage = response.getApiResponseBody().getContent().size();
        long size = response.getApiResponseBody().getPage().getSize();
        logger.info("count of users = " + countOfUsersOnPage + " size = " + size);
        Assert.assertEquals(countOfUsersOnPage, size);
    }
}


