package org.minnerar.model;

import java.util.Objects;

public class Item {

    private int id;
    private int type;
    private String name;
    private boolean completed;
    private String season;
    private String time;
    private String weather;
    private String location;
    private String description;
    private int achievementId;

    public Item(int id, int type, String name, boolean completed, String season,
                String time, String weather, String location, String description, int achievementId) {
        this.id = id;
        this.type = type;
        this.name = name;
        this.completed = completed;
        this.season = season;
        this.time = time;
        this.weather = weather;
        this.location = location;
        this.description = description;
        this.achievementId = achievementId;
    }

    public Item(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public Item(){};

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public String getSeason() {
        return season;
    }

    public void setSeason(String season) {
        this.season = season;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getWeather() {
        return weather;
    }

    public void setWeather(String weather) {
        this.weather = weather;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getAchievementId() {
        return achievementId;
    }

    public void setAchievementId(int achievementId) {
        this.achievementId = achievementId;
    }

    @Override
    public String toString() {
        return "Item{" +
                "id=" + id +
                ", type=" + type +
                ", name='" + name + '\'' +
                ", completed=" + completed +
                ", season='" + season + '\'' +
                ", time='" + time + '\'' +
                ", weather='" + weather + '\'' +
                ", location='" + location + '\'' +
                ", description='" + description + '\'' +
                ", achievementId=" + achievementId +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;
        return id == item.id && type == item.type && completed == item.completed &&
                achievementId == item.achievementId && Objects.equals(name, item.name) &&
                Objects.equals(season, item.season) && Objects.equals(time, item.time) &&
                Objects.equals(weather, item.weather) && Objects.equals(location, item.location) &&
                Objects.equals(description, item.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, type, name, completed, season, time, weather, location, description, achievementId);
    }
}
