package com.techelevator.model;

import java.util.Objects;

public class Item {

    private int id;
    private int classification;
    private String name;
    private boolean completed;
    private String season;
    private String time;
    private String weather;
    private String location;
    private String description;
    private String imageURL;

    public Item(int id, String name, boolean completed, String season,
                String time, String weather, String location, String description, String imageURL) {
        this.id = id;
        this.classification = classification;
        this.name = name;
        this.completed = completed;
        this.season = season;
        this.time = time;
        this.weather = weather;
        this.location = location;
        this.description = description;
        this.imageURL = imageURL;
    }

    public Item(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public Item(){};

    public int getItemId() {
        return id;
    }

    public void setItemId(int id) {
        this.id = id;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public String getImageURL() {
        return imageURL;
    }

//    public int getItemClassification() {
//        return getItemClassification();
//    }
//
//    public void setItemClassification(int classification) {
//        this.classification = classification;
//    }

    public String getItemName() {
        return name;
    }

    public void setItemName(String name) {
        this.name = name;
    }

    public boolean isItemCompleted() {
        return completed;
    }

    public void setItemCompleted(boolean completed) {
        this.completed = completed;
    }

    public String getItemSeason() {
        return season;
    }

    public void setItemSeason(String season) {
        this.season = season;
    }

    public String getItemTime() {
        return time;
    }

    public void setItemTime(String time) {
        this.time = time;
    }

    public String getItemWeather() {
        return weather;
    }

    public void setItemWeather(String weather) {
        this.weather = weather;
    }

    public String getItemLocation() {
        return location;
    }

    public void setItemLocation(String location) {
        this.location = location;
    }

    public String getItemDescription() {
        return description;
    }

    public void setItemDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Item{" +
                "id=" + id +
                ", classification=" + classification +
                ", name='" + name + '\'' +
                ", completed=" + completed +
                ", season='" + season + '\'' +
                ", time='" + time + '\'' +
                ", weather='" + weather + '\'' +
                ", location='" + location + '\'' +
                ", description='" + description + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;
        return id == item.id && classification == item.classification && completed == item.completed &&
                Objects.equals(name, item.name) &&
                Objects.equals(season, item.season) && Objects.equals(time, item.time) &&
                Objects.equals(weather, item.weather) && Objects.equals(location, item.location) &&
                Objects.equals(description, item.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, classification, name, completed, season, time, weather, location, description);
    }
}
