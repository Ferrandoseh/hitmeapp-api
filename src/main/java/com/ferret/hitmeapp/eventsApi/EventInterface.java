package com.ferret.hitmeapp.eventsApi;

import com.ferret.hitmeapp.util.EventPair;

import java.util.ArrayList;

public interface EventInterface {
    static String Uri = null;
    static String Key = null;

    public ArrayList<EventPair> getAllCategories();
    public Object getEventsByDistance(String latitude, String longitude, String radius);
    public Object getEventsByCategoryDistance(String latitude, String longitude, String radius, String categoryId);
}
