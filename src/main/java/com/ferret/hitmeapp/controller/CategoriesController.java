package com.ferret.hitmeapp.controller;

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

    @GetMapping(value="/categories")
    public String getAllCategories() {
        final String uri = meetupUri + type + "?key=" + apiKey + "&sign=true&photo-host=public&page=20";

        RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate.getForObject(uri, String.class);

        System.out.println(result);

        return "categories";
    }
}
