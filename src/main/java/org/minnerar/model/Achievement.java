package org.minnerar.model;

public class Achievement {

    private int id;
    private String name;
    private int totalNeeded;
    private int current;
    private String description;
    private double progress;

    public Achievement() {}

    public Achievement(int id, String name, int totalNeeded, int current, String description) {
        this.id = id;
        this.name = name;
        this.progress = getAchievementProgress(current, totalNeeded);
        this.totalNeeded = totalNeeded;
        this.current = current;
        this.description = description;
    }

    public int getAchievementId() {
        return id;
    }

    public void setAchievementId(int id) {
        this.id = id;
    }

    public String getAchievementName() {
        return name;
    }

    public void setAchievementName(String name) {
        this.name = name;
    }

    public int getAchievementTotalNeeded() {
        return totalNeeded;
    }

    public void setAchievementTotalNeeded(int totalNeeded) {
        this.totalNeeded = totalNeeded;
    }

    public int getAchievementCurrent() {
        return current;
    }

    public void setAchievementCurrent(int current) {
        this.current = current;
    }

    public String getAchievementDescription() {
        return description;
    }

    public void setAchievementDescription(String description) {
        this.description = description;
    }

    private double getAchievementProgress(int current, int totalNeeded) {
        return ((double)current / totalNeeded);
    }

}
