package com.restapi.automation;

import com.google.gson.Gson;
import com.restapi.automation.dto.RestApiResponse;
import com.restapi.automation.dto.User;
import com.restapi.automation.dto.Users;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Value;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Log4j
public class BaseTest extends AutomationApplicationTests{

    @Value("${restapi.baseUrl}")
    String restApiUrl;

    public static final int SUCCESS_CODE = 200;
    public static final int CREATED_CODE = 201;
    public static final int NOT_FOUND = 404;

    private final HttpClient httpClient = HttpClient.newBuilder()
            .version(HttpClient.Version.HTTP_2)
            .build();

    public RestApiResponse sendGetApiRequest(){

        var request = HttpRequest.newBuilder(
                URI.create(restApiUrl + "/users"))
                .GET()
                .build();

        RestApiResponse response = new RestApiResponse();
        try{
            var httpresponse = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            Users users = new Gson().fromJson(httpresponse.body(), Users.class);
            response.setStatusCode(httpresponse.statusCode());
            response.setApiResponseBody(users);

        }catch (IOException | InterruptedException x){
            logger.info("Error while sending request. " + x);
        }

        return response;
    }

    public RestApiResponse sendPostApiRequest(){

        Map<String, String> data = new HashMap<>();
        data.put("dayOfBirth", RandomUtils.randomDateOfBirth());
        data.put("email", RandomUtils.randomString(12) + "@gmail.com");
        data.put("firstName", RandomUtils.randomString(6));
        data.put("lastName", RandomUtils.randomString(8));

        logger.info("Creating new User = " + data);

        var request  = HttpRequest.newBuilder(
                URI.create(restApiUrl + "/users"))
                .POST(buildRequestFromMap(data))
                .header("Content-Type", "application/json")
                .build();

        RestApiResponse response = new RestApiResponse();
        try {
            var httpresponse = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            User user = new Gson().fromJson(httpresponse.body(), User.class);
            response.setStatusCode(httpresponse.statusCode());
            response.setSingleUserResponse(user);
        } catch (IOException | InterruptedException x){
            logger.info("Failed to send POST request. " + x);
        }

        return response;
    }

    public RestApiResponse sendGetApiRequest(int id){

        var request = HttpRequest.newBuilder(
                URI.create(restApiUrl + "/users/" + id))
                .GET()
                .build();

        RestApiResponse response = new RestApiResponse();
        try{
            var httpresponse = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            User user = new Gson().fromJson(httpresponse.body(), User.class);
            response.setStatusCode(httpresponse.statusCode());
            response.setSingleUserResponse(user);

        }catch (IOException | InterruptedException x){
            logger.info("Error while sending request. " + x);
        }

        return response;
    }

    private static HttpRequest.BodyPublisher buildRequestFromMap(Map<String, String> data) {
        String s = data.entrySet()
                .stream()
                .map(entry -> String.join(":",
                        "\"" + entry.getKey() + "\"",
                        "\"" + entry.getValue() + "\"")
                ).collect(Collectors.joining(","));

        return HttpRequest.BodyPublishers.ofString("{" + s + "}");
    }
}
