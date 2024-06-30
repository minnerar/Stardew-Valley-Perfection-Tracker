package org.minnerar.model;

import java.util.Objects;

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
        this.progress = getAchievementProgress();
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

    public double getAchievementProgress() {
        return ((double)(getAchievementCurrent()*100.00) / getAchievementTotalNeeded());
    }

    public void setAchievementProgress(double progress) {
        this.progress = progress;
    }

    @Override
    public String toString() {
        return "Achievement{" +
                "id=" + id +
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
        return id == that.id && totalNeeded == that.totalNeeded &&
                current == that.current && Double.compare(that.progress, progress) == 0 &&
                Objects.equals(name, that.name) && Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, totalNeeded, current, description, progress);
    }
}
