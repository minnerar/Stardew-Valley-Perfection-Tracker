package org.minnerar.model;

public class Villager {

    private int id;
    private String name;
    private boolean marriageCandidate;
    private String birthday;
    private int heartCounter;
    private String[] lovedGifts;
    private String description;

    public int getVillagerId() {
        return id;
    }

    public void setVillagerId(int id) {
        this.id = id;
    }

    public String getVillagerName() {
        return name;
    }

    public void setVillagerName(String name) {
        this.name = name;
    }

    public boolean isVillagerMarriageCandidate() {
        return marriageCandidate;
    }

    public void setVillagerMarriageCandidate(boolean marriageCandidate) {
        this.marriageCandidate = marriageCandidate;
    }

    public String getVillagerBirthday() {
        return birthday;
    }

    public void setVillagerBirthday(String birthday) {
        this.birthday = birthday;
    }

    public int getVillagerHeartCounter() {
        return heartCounter;
    }

    public void setVillagerHeartCounter(int heartCounter) {
        this.heartCounter = heartCounter;
    }

    public String[] getVillagerLovedGifts() {
        return lovedGifts;
    }

    public void setVillagerLovedGifts(String[] lovedGifts) {
        this.lovedGifts = lovedGifts;
    }

    public String getVillagerDescription() {
        return description;
    }

    public void setVillagerDescription(String description) {
        this.description = description;
    }
}
