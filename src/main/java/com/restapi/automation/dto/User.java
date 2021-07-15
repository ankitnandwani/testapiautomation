package com.restapi.automation.dto;

import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;

@Data
@Builder
public class User {
    private int id;
    private String dayOfBirth;
    private String email;
    private String firstName;
    private String lastName;
    private ArrayList<Link> links;
}
