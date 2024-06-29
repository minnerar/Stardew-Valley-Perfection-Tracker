package org.minnerar.model;

public class Mineral {

    private int id;
    private String name;
    private String location;
    private boolean donated;
    private String description;

    public int getMineralId() {
        return id;
    }

    public void setMineralId(int id) {
        this.id = id;
    }

    public String getMineralName() {
        return name;
    }

    public void setMineralName(String name) {
        this.name = name;
    }

    public String getMineralLocation() {
        return location;
    }

    public void setMineralLocation(String location) {
        this.location = location;
    }

    public boolean isMineralDonated() {
        return donated;
    }

    public void setMineralDonated(boolean donated) {
        this.donated = donated;
    }

    public String getMineralDescription() {
        return description;
    }

    public void setMineralDescription(String description) {
        this.description = description;
    }
}
