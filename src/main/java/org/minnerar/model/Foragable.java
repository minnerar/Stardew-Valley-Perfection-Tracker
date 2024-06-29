package org.minnerar.model;

public class Foragable {

    private int id;
    private String name;
    private String season;
    private String location;
    private String description;
    private boolean found;

    public boolean isForagableFound() {
        return found;
    }

    public void setForagableFound(boolean found) {
        this.found = found;
    }

    public int getForagableId() {
        return id;
    }

    public void setForagableId(int id) {
        this.id = id;
    }

    public String getForagableName() {
        return name;
    }

    public void setForagableName(String name) {
        this.name = name;
    }

    public String getForagableSeason() {
        return season;
    }

    public void setForagableSeason(String season) {
        this.season = season;
    }

    public String getForagableLocation() {
        return location;
    }

    public void setForagableLocation(String location) {
        this.location = location;
    }

    public String getForagableDescription() {
        return description;
    }

    public void setForagableDescription(String description) {
        this.description = description;
    }
}
