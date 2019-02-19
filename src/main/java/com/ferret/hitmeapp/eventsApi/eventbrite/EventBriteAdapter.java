package com.ferret.hitmeapp.eventsApi.eventbrite;

import com.ferret.hitmeapp.eventsApi.ApiAdapter;
import com.ferret.hitmeapp.eventsApi.EventInterface;
import com.ferret.hitmeapp.util.CategoryPair;
import com.ferret.hitmeapp.util.Event;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;

public class EventBriteAdapter extends ApiAdapter implements EventInterface {
    private static String Uri = "https://www.eventbriteapi.com/v3/";
    private static String Key = "QGECTYGPITUNH2AOJ6NS"; //"IEKQDJQG4OBWT2MKDPTO";

    @Override
    public ArrayList<CategoryPair> getAllCategories() {
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
    public ArrayList<Event> getEventsByDistance(String latitude, String longitude, String radius) {
        String type = "events/search/";
        final String uri = Uri + type + "?token=" + Key + "&location.within=" + radius + "mi&location.latitude=" +
                latitude + "&location.longitude=" + longitude;

        RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate.getForObject(uri, String.class);

        return eventEBJson(result);
    }

    private ArrayList<Event> eventEBJson(String resultAPI) {
        ArrayList<Event> result = new ArrayList<>();

        try {
            JSONObject eventsAPI = new JSONObject(resultAPI);
            JSONArray jsonArray = new JSONArray( eventsAPI.getString("events") );

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject object = jsonArray.getJSONObject(i);

                JSONObject objectName = new JSONObject( object.getString("name") );
                String name = objectName.getString("text");

                JSONObject objectDescription = new JSONObject( object.getString("name") );
                String description = objectDescription.getString("text");

                String url = object.getString("url");

                JSONObject objectImg = new JSONObject( object.getString("logo") );
                String img = objectImg.getString("url");

                JSONObject objectEndTime = new JSONObject( object.getString("start") );
                String endTime = objectEndTime.getString("local");

                JSONObject objectStartTime = new JSONObject( object.getString("end") );
                String startTime = objectStartTime.getString("local");


                String venueId = object.getString("venue_id");
                ArrayList<String> coordinates = getCoordinates(venueId);

                Event e = new Event(name, description, url, img, startTime,
                        endTime, coordinates.get(0), coordinates.get(1));
                result.add(e);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return result;
    }


    public ArrayList<String> getCoordinates(String venue_id) {
        String type = "venues/";
        final String uri = Uri + type + venue_id + "?token=" + Key;

        RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate.getForObject(uri, String.class);


        ArrayList<String> coordinates = new ArrayList<>();
        try {
            JSONObject venueAPI = new JSONObject(result);
            coordinates.add(venueAPI.getString("latitude"));
            coordinates.add(venueAPI.getString("longitude"));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return coordinates;
    }

    @Override
    public ArrayList<Event> getEventsByCategoryDistance(String latitude, String longitude, String radius, String categoryId) {
        String type = "events/search/";
        final String uri = Uri + type + "?token=" + Key + "&location.within=" + radius + "mi&location.latitude=" +
                latitude + "&location.longitude=" + longitude + "&categories=" + categoryId;


        RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate.getForObject(uri, String.class);

        return eventEBJson(result);
    }
}
