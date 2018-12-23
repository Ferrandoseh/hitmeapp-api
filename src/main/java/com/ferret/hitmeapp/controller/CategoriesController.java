package com.ferret.hitmeapp.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@Controller
public class CategoriesController extends DefaultController {
    private String type = "categories";

    @GetMapping
    public String getIndex() {
        return "index";
    }

    @RequestMapping(value = "/")
    @ResponseBody
    public String home() {

        return "<a href=\"/auth/salesforce\">login</a>";
    }
    @GetMapping(value="/categories")
    public String getAllCategories() {
        //getAllCategoriesMeetup();
        getAllCategoriesEventBrite();

        return "categories";
    }

    //Trying to get OAuth token, it should be post:
    // https://www.eventbrite.com/platform/api/#/introduction/authentication/getting-a-token
    @RequestMapping(value = "/test")
    public String getToken() {
        String uri1 = "https://www.eventbrite.com/oauth/authorize?response_type=code&client_id=DKJ5G4DSQZCAIIIZKH";

        String uri2 = "https://www.eventbriteapi.com/v3/users/me/?token=QGECTYGPITUNH2AOJ6NS";
        String uri0 = "https://www.eventbrite.com/oauth/token";

        String content = "code=QGECTYGPITUNH2AOJ6NS&client_secret=QDZLDFYDAKNUZOM4XE2XWJS7VVNCHWX6SPUCKRHZO4WQSLTFQI&client_id=DKJ5G4DSQZCAIIIZKH&grant_type=authorization_code";
        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<String> postResponse = restTemplate.postForEntity(uri1, content, String.class);
        System.out.println("Response for Post Request: " + postResponse.getBody());


        return "categories";
    }

    // Meetup API
    private void getAllCategoriesMeetup() {
        final String uri = meetupUri + type + "?key=" + key_MeetUp + "&sign=true&photo-host=public&page=20";

        RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate.getForObject(uri, String.class);

        System.out.println(result);
    }

    // EventBrite API
    private void getAllCategoriesEventBrite() {
        final String uri = eventbriteUri + type + "?token=" + OAuth_EventBrite;

        RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate.getForObject(uri, String.class);

        System.out.println(result);
    }
}
