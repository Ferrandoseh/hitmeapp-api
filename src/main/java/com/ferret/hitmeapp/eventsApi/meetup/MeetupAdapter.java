package com.ferret.hitmeapp.eventsApi.meetup;

import com.ferret.hitmeapp.eventsApi.ApiAdapter;
import com.ferret.hitmeapp.eventsApi.EventInterface;
import com.ferret.hitmeapp.util.EventPair;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;

public class MeetupAdapter extends ApiAdapter implements EventInterface {
    private static String Uri = "https://api.meetup.com/2/";
    private static String Key = "7d555759c312c5e6655f5028503637";

    @Override
    public ArrayList<EventPair> getAllCategories() {
        String type = "categories";
        final String uri = Uri + type + "?key=" + Key + "&sign=true&photo-host=public&page=20";

        String result = restTemplate.getForObject(uri, String.class);

        return categoriesJson(result);
    }

    protected String getApiName() {
        return "meetup";
    }

    protected String getJsonArrayTag() {
        return "results";
    }

    @Override
    @GetMapping(value="/events/{latitude}/{longitude}/{radius}")
    public String getEventsByDistance(@PathVariable String latitude, @PathVariable String longitude,
                                      @PathVariable String radius) {

        String type = "concierge";
        final String uri = Uri + type + "?key=" + Key + "&sign=true&photo-host=public&" +
                "lon=" + longitude + "&radius=" + radius + "&lat=" + latitude;

        RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate.getForObject(uri, String.class);

        System.out.println(result);
        return "events";
    }

    @Override
    @GetMapping(value="/events/{latitude}/{longitude}/{radius}/{categoryId}")
    public String getEventsByCategoryDistance(@PathVariable String latitude, @PathVariable String longitude,
                                              @PathVariable String radius, @PathVariable String categoryId) {

        String type = "concierge";
        final String uri = Uri + type + "?key=" + Key + "&sign=true&photo-host=public&" +
                "lon=" + longitude + "&category_id=" + categoryId + "&radius=" + radius + "&lat=" + latitude;


        RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate.getForObject(uri, String.class);

        System.out.println(result);
        return "events";
    }

}
