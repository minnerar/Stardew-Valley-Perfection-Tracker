package org.minnerar.model;

public class Artifact {

    private int id;
    private String name;
    private String location;
    private boolean donated;
    private String description;

    public int getArtifactId() {
        return id;
    }

    public void setArtifactId(int id) {
        this.id = id;
    }

    public String getArtifactName() {
        return name;
    }

    public void setArtifactName(String name) {
        this.name = name;
    }

    public String getArtifactLocation() {
        return location;
    }

    public void setArtifactLocation(String location) {
        this.location = location;
    }

    public boolean isArtifactDonated() {
        return donated;
    }

    public void setArtifactDonated(boolean donated) {
        this.donated = donated;
    }

    public String getArtifactDescription() {
        return description;
    }

    public void setArtifactDescription(String description) {
        this.description = description;
    }
}
