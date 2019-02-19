package com.ferret.hitmeapp.controller;

import com.ferret.hitmeapp.manager.CategoryManager;
import com.ferret.hitmeapp.manager.EventManager;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class DomainController {
    private CategoryManager categoriesManager;
    private EventManager eventManager;

    public DomainController () {
        categoriesManager = CategoryManager.getInstance();
        eventManager = EventManager.getInstance();
    }

    @GetMapping(value="/categories")
    public void getAllCategories() {
        System.out.println( CategoryManager.getJson() );
        //TODO: Add json file to be sent to the APP
    }

    @GetMapping(value="/events/{latitude}/{longitude}/{radius}")
    public @ResponseBody void getEventsByDistance(@PathVariable String latitude, @PathVariable String longitude,
                                    @PathVariable String radius) {
        System.out.println( EventManager.getJsonEventsByDistance(latitude, longitude, radius) );
        //TODO: Add json file to be sent to the APP
    }

    @GetMapping(value="/events/{latitude}/{longitude}/{radius}/{categoryId}")
    public  @ResponseBody void getEventsByCategoryDistance(@PathVariable String latitude, @PathVariable String longitude,
                                            @PathVariable String radius, @PathVariable String categoryId) {
        System.out.println( EventManager.getJsonEventsByCategoryDistance(latitude, longitude, radius, categoryId) );
        //TODO: Add json file to be sent to the APP
    }

}
