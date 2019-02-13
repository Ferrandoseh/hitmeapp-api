package com.ferret.hitmeapp.util;

public class EventPair {
    private int id;
    private String name;

    public EventPair(int id, String name) {
        put(id, name);
    }

    public void put(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public String get() {
        return "<" + id + ", " + name + ">";
    }

    public int getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }
}
