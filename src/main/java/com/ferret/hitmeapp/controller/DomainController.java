package com.ferret.hitmeapp.controller;

import com.ferret.hitmeapp.manager.CategoryManager;
import com.ferret.hitmeapp.manager.EventManager;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

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

    @GetMapping(value="/events")
    public void getAllEvents() {
        System.out.println( EventManager.getJson() );
        //TODO: Add json file to be sent to the APP
    }

}
