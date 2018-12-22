package com.ferret.hitmeapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.RestTemplate;

@Controller
public class EventsController extends DefaultController {
    private String type = "concierge";

    @GetMapping(value="/events/{latitude}/{longitude}/{radius}")
    public String getEventsByDistance(@PathVariable String latitude, @PathVariable String longitude,
                                      @PathVariable String radius) {
        final String uri = meetupUri + type + "?key=" + apiKey + "&sign=true&photo-host=public&" +
                "lon=" + longitude + "&radius=" + radius + "&lat=" + latitude;

        RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate.getForObject(uri, String.class);

        System.out.println(result);
        return "events";
    }

    @GetMapping(value="/events/{latitude}/{longitude}/{radius}/{categoryId}")
    public String getEventsByCategoryDistance(@PathVariable String latitude, @PathVariable String longitude,
                                              @PathVariable String radius, @PathVariable String categoryId) {
        final String uri = meetupUri + type + "?key=" + apiKey + "&sign=true&photo-host=public&" +
                "lon=" + longitude + "&category_id=" + categoryId + "&radius=" + radius + "&lat=" + latitude;


        RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate.getForObject(uri, String.class);

        System.out.println(result);
        return "events";
    }
}
