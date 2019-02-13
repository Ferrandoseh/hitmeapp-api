package com.ferret.hitmeapp.util;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.regex.Pattern;

public class CategoryMatcher {
    private int idMeetup;
    private int idEventbrite;
    private String name;
    private static ArrayList<CategoryMatcher> Categories = new ArrayList<>();
    private static ArrayList<String> names = new ArrayList<>();
    private static ArrayList<String[]> keys = new ArrayList<>();
    private static String[] category;

    public CategoryMatcher() {
    }

    public CategoryMatcher(int idMeetup, int idEventbrite, String realName) {
        set(idMeetup, idEventbrite);
        this.name = realName;
    }

    public static void setUp() {
        Categories = new ArrayList<>();

        keys = new ArrayList<>();
        category = new String[] {"art"};
        keys.add(category);
        category = new String[] {"book", "read"};
        keys.add(category);
        category = new String[] {"business"};
        keys.add(category);
        category = new String[] {"car", "motor", "boat", "air"};
        keys.add(category);
        category = new String[] {"community", "environment", "culture"};
        keys.add(category);
        category = new String[] {"danc", "perform", "visual arts"};
        keys.add(category);
        category = new String[] {"education", "learn"};
        keys.add(category);
        category = new String[] {"fashion", "beauty"};
        keys.add(category);
        category = new String[] {"sport", "fitness"};
        keys.add(category);
        category = new String[] {"food", "drink", "cook"};
        keys.add(category);
        category = new String[] {"games", "gaming"};
        keys.add(category);
        category = new String[] {"govern", "politic"};
        keys.add(category);
        category = new String[] {"wellbeing", "health", "wellness"};
        keys.add(category);
        category = new String[] {"hobb", "interest"};
        keys.add(category);
        category = new String[] {"travel", "outdoor", "language", "identity"};
        keys.add(category);
        category = new String[] {"lifestyle"};
        keys.add(category);
        category = new String[] {"movie", "film", "media"};
        keys.add(category);
        category = new String[] {"music", "song"};
        keys.add(category);
        category = new String[] {"technology", "science"};
        keys.add(category);
        category = new String[] {"charity"};
        keys.add(category);
        category = new String[] {"religion"};
        keys.add(category);

        names = new ArrayList<String>() {};
        names.add("Art & Culture");
        names.add("Book Clubs");
        names.add("Career & Business");
        names.add("Cars & Motorcycles");
        names.add("Community & Environment");
        names.add("Dancing");
        names.add("Education & Learning");
        names.add("Fashion & Beauty");
        names.add("Sports & Fitness");
        names.add("Food & Drink");
        names.add("Games");
        names.add("Movements & Politics");
        names.add("Health & Wellbeing");
        names.add("Hobbies & Crafts");
        names.add("Language, Travel & Ethnic Identity");
        names.add("Lifestyle");
        names.add("Movies & Film");
        names.add("Music");
        names.add("Science & Technology");
        names.add("Charity & Causes");
        names.add("Religion & Spirituality");
    }

    public static ArrayList<CategoryMatcher> getCategories() {
        return Categories;
    }

    public static EventPair findCategory(String name) {
        EventPair pair = new EventPair(-1, name);
        int keysSize = keys.size();
        for(int i = 0; i < keysSize && pair.getId() == -1; i++) {
            String[] categoryTags = keys.get(i);
            int length = categoryTags.length;
            for(int j = 0; j < length && pair.getId() == -1; j++) {
                if( Pattern.compile(Pattern.quote(categoryTags[j]), Pattern.CASE_INSENSITIVE).matcher(name).find() ) {
                    pair.put(i, names.get(i));
                }
            }
        }
        return pair;
    }

    public static void put(int idMeetup, int idEventbrite, int categoryFound, String realName) {
        int size = Categories.size();
        int found = -1;
        for(int i = 0; i < size && found == -1 && categoryFound != -1; i++) {
            if( Categories.get(i).getName().equals( names.get(categoryFound) ) )
                found = i;
        }

        if( found != -1 )
            Categories.get(found).set(idMeetup, idEventbrite);
        else {
            Categories.add(new CategoryMatcher(idMeetup, idEventbrite, realName));
        }
    }

    private void set(int idMeetup, int idEventbrite) {
        if( idMeetup != -1)
            this.idMeetup = idMeetup;
        if( idEventbrite != -1)
            this.idEventbrite = idEventbrite;
    }

    public String get() {
        return "<" + name + ", " + idMeetup +", " + idEventbrite + ">";
    }

    public String getName() {
        return this.name;
    }
}
