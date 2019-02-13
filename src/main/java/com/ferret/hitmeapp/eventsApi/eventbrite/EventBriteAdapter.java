package com.ferret.hitmeapp.eventsApi.eventbrite;

import com.ferret.hitmeapp.eventsApi.ApiAdapter;
import com.ferret.hitmeapp.eventsApi.EventInterface;
import com.ferret.hitmeapp.util.EventPair;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;

public class EventBriteAdapter extends ApiAdapter implements EventInterface {
    private static String Uri = "https://www.eventbriteapi.com/v3/";
    private static String Key = "QGECTYGPITUNH2AOJ6NS"; //"IEKQDJQG4OBWT2MKDPTO";

    @Override
    public ArrayList<EventPair> getAllCategories() {
        String type = "categories";
        final String uri = Uri + type + "?token=" + Key;

        RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate.getForObject(uri, String.class);

        return categoriesJson(result);
    }

    protected String getApiName() {
        return "eventbrite";
    }

    protected String getJsonArrayTag() {
        return "categories";
    }

    @Override
    public Object getEventsByDistance(String latitude, String longitude, String radius) {
        return null;
    }

    @Override
    public Object getEventsByCategoryDistance(String latitude, String longitude, String radius, String categoryId) {
        return null;
    }
}
