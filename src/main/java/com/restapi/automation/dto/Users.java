package com.restapi.automation.dto;

import lombok.Data;

import java.util.ArrayList;

@Data
public class Users {
    private ArrayList<Link> links;
    private ArrayList<User> content;
    private Page page;
}
