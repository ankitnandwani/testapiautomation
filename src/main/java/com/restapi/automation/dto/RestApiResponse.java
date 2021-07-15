package com.restapi.automation.dto;

import lombok.Data;

@Data
public class RestApiResponse {
    private int statusCode;
    private Users apiResponseBody;
    private User singleUserResponse;
}
