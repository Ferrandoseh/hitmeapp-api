package com.ferret.hitmeapp.eventsApi;

import com.ferret.hitmeapp.util.CategoryPair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;

public abstract class ApiAdapter {
    protected RestTemplate restTemplate = new RestTemplate();

    protected ArrayList<CategoryPair> categoriesJson(String resultAPI)
    {
        ArrayList<CategoryPair> result = new ArrayList<>();

        JSONObject categoriesObj = new JSONObject();
        JSONArray categoriesArray = new JSONArray();

        try {
            JSONObject categoriesAPI = new JSONObject(resultAPI);
            JSONArray jsonArray = new JSONArray( categoriesAPI.getString(getJsonArrayTag()) );

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject object = jsonArray.getJSONObject(i);

                CategoryPair pair = new CategoryPair(object.getInt("id"), object.getString("name"));
                result.add(pair);

                //System.out.println( i + " - " + result.get(i).get() );
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
