package com.ferret.hitmeapp.eventsApi.meetup;

import com.ferret.hitmeapp.eventsApi.ApiAdapter;
import com.ferret.hitmeapp.eventsApi.EventInterface;
import com.ferret.hitmeapp.util.CategoryPair;
import com.ferret.hitmeapp.util.Event;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Date;

public class MeetupAdapter extends ApiAdapter implements EventInterface {
    private static String Uri = "https://api.meetup.com/2/";
    private static String Key = "7d555759c312c5e6655f5028503637";

    @Override
    public ArrayList<CategoryPair> getAllCategories() {
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
    public ArrayList<Event> getEventsByDistance(String latitude, String longitude, String radius) {

        String type = "concierge";
        final String uri = Uri + type + "?key=" + Key + "&sign=true&photo-host=public&" +
                "lon=" + longitude + "&radius=" + radius + "&lat=" + latitude;

        RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate.getForObject(uri, String.class);

        return eventMUJson(result);
    }

    private ArrayList<Event> eventMUJson(String resultAPI) {
        ArrayList<Event> result = new ArrayList<>();

        try {
            JSONObject eventsAPI = new JSONObject(resultAPI);
            JSONArray jsonArray = new JSONArray( eventsAPI.getString("results") );

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject object = jsonArray.getJSONObject(i);

                String name = object.getString("name");
                String description = "";
                if( object.has("description") )
                    description = object.getString("description");
                String url = object.getString("event_url");
                String img = "";
                if( object.has("photo_url") )
                    img = object.getString("photo_url");
                int date = object.getInt("time");
                Date startTime = new Date(date);

                Date endTime = new Date(date + object.getInt("duration"));

                JSONObject objectVenue = new JSONObject( object.getString("venue"));
                String latitude = objectVenue.getString("lat");
                String longitude = objectVenue.getString("lon");


                Event e = new Event(name, description, url, img, startTime.toString(),
                        endTime.toString(), latitude, longitude);
                result.add(e);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return result;
    }

    @Override
    public ArrayList<Event> getEventsByCategoryDistance(String latitude, String longitude, String radius, String categoryId) {

        String type = "concierge";
        final String uri = Uri + type + "?key=" + Key + "&sign=true&photo-host=public&" +
                "lon=" + longitude + "&category_id=" + categoryId + "&radius=" + radius + "&lat=" + latitude;


        RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate.getForObject(uri, String.class);

        System.out.println(result);
        return null;
    }

}
