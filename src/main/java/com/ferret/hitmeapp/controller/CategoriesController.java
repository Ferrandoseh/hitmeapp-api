package com.ferret.hitmeapp.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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
        System.out.println( getAllCategoriesMeetUp().toString() );
        System.out.println( getAllCategoriesEventBrite().toString() );

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
    private JSONObject getAllCategoriesMeetUp() {
        final String uri = meetupUri + type + "?key=" + key_MeetUp + "&sign=true&photo-host=public&page=20";

        String result = restTemplate.getForObject(uri, String.class);

        return categoriesJsonMeetUp(result);
    }

    private JSONObject categoriesJsonMeetUp(String resultAPI)
    {
        JSONObject categoriesObj = new JSONObject();
        JSONArray categoriesArray = new JSONArray();

        try {
            JSONObject categoriesAPI = new JSONObject(resultAPI);
            JSONArray jsonArray = new JSONArray( categoriesAPI.getString("results") );

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject object = jsonArray.getJSONObject(i);

                JSONObject item = new JSONObject();
                item.put("id", object.getInt("id"));
                item.put("name", object.getString("name"));
                categoriesArray.put(item);
            }

            categoriesObj.put("meetup", categoriesArray);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return categoriesObj;
    }

    // EventBrite API
    private JSONObject getAllCategoriesEventBrite() {
        final String uri = eventbriteUri + type + "?token=" + OAuth_EventBrite;

        RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate.getForObject(uri, String.class);

        return categoriesJsonEventBrite(result);
    }

    private JSONObject categoriesJsonEventBrite(String resultAPI)
    {
        JSONObject categoriesObj = new JSONObject();
        JSONArray categoriesArray = new JSONArray();

        try {
            JSONObject categoriesAPI = new JSONObject(resultAPI);
            JSONArray jsonArray = new JSONArray( categoriesAPI.getString("categories") );

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject object = jsonArray.getJSONObject(i);

                JSONObject item = new JSONObject();
                item.put("id", object.getInt("id"));
                item.put("name", object.getString("name"));
                categoriesArray.put(item);
            }

            categoriesObj.put("eventbrite", categoriesArray);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return categoriesObj;
    }
}
