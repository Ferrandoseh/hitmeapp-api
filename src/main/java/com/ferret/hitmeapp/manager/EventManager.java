package com.ferret.hitmeapp.manager;

import com.ferret.hitmeapp.eventsApi.eventbrite.EventBriteAdapter;
import com.ferret.hitmeapp.eventsApi.meetup.MeetupAdapter;
import com.ferret.hitmeapp.util.Event;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class EventManager {
    public static final EventManager EVENT_MANAGER = new EventManager();
    private static MeetupAdapter meetupAdapter = new MeetupAdapter();
    private static EventBriteAdapter eventbriteAdapter = new EventBriteAdapter();

    private EventManager() {}

    public static EventManager getInstance() {
        return EVENT_MANAGER;
    }

    public static JSONArray getJsonEventsByDistance(String latitude, String longitude, String radius) {
        ArrayList<Event> allEvents = eventbriteAdapter.getEventsByDistance(latitude, longitude, radius);
        allEvents.addAll(meetupAdapter.getEventsByDistance(latitude, longitude, radius));
        return createJson(allEvents);
    }

    public static JSONArray getJsonEventsByCategoryDistance(String latitude, String longitude, String radius, String categoryId) {
        ArrayList<Event> allEvents = eventbriteAdapter.getEventsByCategoryDistance(latitude, longitude, radius, categoryId);
        allEvents.addAll(meetupAdapter.getEventsByCategoryDistance(latitude, longitude, radius, categoryId));
        return createJson(allEvents);
    }

    private static JSONArray createJson(ArrayList<Event> events) {
        JSONArray eventsArray = new JSONArray();

        try {

            for (int i = 0; i < events.size(); i++) {

                JSONObject item = new JSONObject();
                item.put("name", events.get(i).getName());
                item.put("description", events.get(i).getDescription());
                item.put("url", events.get(i).getUrl());
                item.put("img", events.get(i).getImg());
                item.put("latitude", events.get(i).getLatitude());
                item.put("longitude", events.get(i).getLongitude());
                eventsArray.put(item);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return eventsArray;
    }
}
