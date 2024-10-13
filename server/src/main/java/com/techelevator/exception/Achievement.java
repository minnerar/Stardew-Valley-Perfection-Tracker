package com.techelevator.exception;

import java.util.Objects;

public class Achievement {
    private String name;
    private int totalNeeded;
    private double current;
    private String description;
    private int progress;
    private int achievementId;
    private String imageURL;

    public Achievement() {}

    public Achievement(int achievementId, String name, int totalNeeded, int progress, String description, String imageURL) {
        this.achievementId = achievementId;
        this.name = name;
        this.progress = progress;
        this.totalNeeded = totalNeeded;
        this.current = (double)getAchievementProgress() / getAchievementTotalNeeded();
        this.description = description;
        this.imageURL = imageURL;
    }

    public int getAchievementId() {
        return achievementId;
    }

    public void setAchievementId(int id) {
        this.achievementId = id;
    }

    public String getAchievementName() {
        return name;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public String getImageURL() {
        return imageURL;
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

    public double getAchievementCurrent() {
        return current;
    }

    public void setAchievementCurrent(int progress, int totalNeeded) {
        this.current = ((double)(progress*100.00) / totalNeeded);
    }

    public String getAchievementDescription() {
        return description;
    }

    public void setAchievementDescription(String description) {
        this.description = description;
    }

    public int getAchievementProgress() {
        return progress;
    }

    public void setAchievementProgress(int progress) {
        this.progress = progress;
    }

    @Override
    public String toString() {
        return "Achievement{" +
                "id=" + achievementId +
                ", name='" + name + '\'' +
                ", totalNeeded=" + totalNeeded +
                ", current=" + current +
                ", description='" + description + '\'' +
                ", progress=" + progress +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Achievement that = (Achievement) o;
        return achievementId == that.achievementId && totalNeeded == that.totalNeeded &&
                current == that.current && Double.compare(that.progress, progress) == 0 &&
                Objects.equals(name, that.name) && Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(achievementId, name, totalNeeded, current, description, progress);
    }
}
