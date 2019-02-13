package com.ferret.hitmeapp.manager;

public class EventManager {
    public static final EventManager EVENT_MANAGER = new EventManager();

    private EventManager() {}

    public static EventManager getInstance() {
        return EVENT_MANAGER;
    }

    public static String getJson() {
        return "TODO";
        //TODO: create the functionality
    }
}
