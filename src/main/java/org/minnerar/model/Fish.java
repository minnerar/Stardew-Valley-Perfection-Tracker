package org.minnerar.model;

public class Fish {

    private int id;
    private String name;
    private String method;
    private String location;
    private String time;
    private String season;
    private String weather;
    private String description;
    private boolean caught;

    public boolean isFishCaught() {
        return caught;
    }

    public void setFishCaught(boolean caught) {
        this.caught = caught;
    }

    public int getFishId() {
        return id;
    }

    public void setFishId(int id) {
        this.id = id;
    }

    public String getFishName() {
        return name;
    }

    public void setFishName(String name) {
        this.name = name;
    }

    public String getFishMethod() {
        return method;
    }

    public void setFishMethod(String method) {
        this.method = method;
    }

    public String getFishLocation() {
        return location;
    }

    public void setFishLocation(String location) {
        this.location = location;
    }

    public String getFishTime() {
        return time;
    }

    public void setFishTime(String time) {
        this.time = time;
    }

    public String getFishSeason() {
        return season;
    }

    public void setFishSeason(String season) {
        this.season = season;
    }

    public String getFishWeather() {
        return weather;
    }

    public void setFishWeather(String weather) {
        this.weather = weather;
    }

    public String getFishDescription() {
        return description;
    }

    public void setFishDescription(String description) {
        this.description = description;
    }
}
