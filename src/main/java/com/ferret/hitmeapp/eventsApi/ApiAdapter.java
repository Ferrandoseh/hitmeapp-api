package com.ferret.hitmeapp.eventsApi;

import com.ferret.hitmeapp.util.EventPair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;

public abstract class ApiAdapter {
    protected RestTemplate restTemplate = new RestTemplate();

    protected ArrayList<EventPair> categoriesJson(String resultAPI)
    {
        ArrayList< EventPair > result = new ArrayList<>();

        JSONObject categoriesObj = new JSONObject();
        JSONArray categoriesArray = new JSONArray();

        try {
            JSONObject categoriesAPI = new JSONObject(resultAPI);
            JSONArray jsonArray = new JSONArray( categoriesAPI.getString(getJsonArrayTag()) );

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject object = jsonArray.getJSONObject(i);

                EventPair pair = new EventPair(object.getInt("id"), object.getString("name"));
                result.add(pair);
            }

            categoriesObj.put(getApiName(), categoriesArray);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return result;
    }

    protected abstract String getJsonArrayTag();

    protected abstract String getApiName();
}
