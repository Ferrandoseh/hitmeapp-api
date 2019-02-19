package com.ferret.hitmeapp.eventsApi;

import com.ferret.hitmeapp.util.CategoryPair;

import java.util.ArrayList;

public interface EventInterface {
    ArrayList<CategoryPair> getAllCategories();
    Object getEventsByDistance(String latitude, String longitude, String radius);
    Object getEventsByCategoryDistance(String latitude, String longitude, String radius, String categoryId);
}
