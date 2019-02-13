package com.ferret.hitmeapp.manager;

import com.ferret.hitmeapp.eventsApi.eventbrite.EventBriteAdapter;
import com.ferret.hitmeapp.eventsApi.meetup.MeetupAdapter;
import com.ferret.hitmeapp.util.CategoryMatcher;
import com.ferret.hitmeapp.util.CategoryPair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class CategoryManager extends ApiManager{
    public static final CategoryManager CATEGORY_MANAGER = new CategoryManager();
    private static MeetupAdapter meetupAdapter = new MeetupAdapter();
    private static EventBriteAdapter eventbriteAdapter = new EventBriteAdapter();

    private CategoryManager() {
        CategoryMatcher.setUp(); // Shouldn't be here
    }

    public static CategoryManager getInstance() {
        return CATEGORY_MANAGER;
    }

    public static JSONArray getJson() {
        return createJson( mergeCategories() );
    }

    public static ArrayList<CategoryMatcher> mergeCategories() {
        ArrayList<CategoryPair> meetupCategories = meetupAdapter.getAllCategories();
        ArrayList<CategoryPair> eventbriteCategories = eventbriteAdapter.getAllCategories();

        fillCategories(meetupCategories, meetupCategories.size(), true);
        fillCategories(eventbriteCategories, eventbriteCategories.size(), false);

        return CategoryMatcher.getCategories();
    }

    private static void fillCategories(ArrayList<CategoryPair> arrayCategories, int categoriesSize, boolean api1) {
        for(int i = 0; i < categoriesSize; i++) {
            CategoryPair pair = arrayCategories.get(i);
            CategoryPair foundPair = CategoryMatcher.findCategory(pair.getName());
            int categoryFound = foundPair.getId();
            String name = foundPair.getName();
            if(api1) CategoryMatcher.put(pair.getId(), -1, categoryFound, name);
            else CategoryMatcher.put(-1, pair.getId(), categoryFound, name);
        }
    }

    private static JSONArray createJson(ArrayList<CategoryMatcher> arrayCategories) {
        JSONArray categoriesArray = new JSONArray();

        try {

            for (int i = 0; i < arrayCategories.size(); i++) {

                JSONObject item = new JSONObject();
                item.put("idMeetup", arrayCategories.get(i).getIdMeetup());
                item.put("idEventBrite", arrayCategories.get(i).getIdEventbrite());
                item.put("name", arrayCategories.get(i).getName());
                categoriesArray.put(item);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return categoriesArray;
    }
}


