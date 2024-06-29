package org.minnerar.model;

import java.util.Arrays;
import java.util.Objects;

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

    @Override
    public String toString() {
        return "Villager{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", marriageCandidate=" + marriageCandidate +
                ", birthday='" + birthday + '\'' +
                ", heartCounter=" + heartCounter +
                ", lovedGifts=" + Arrays.toString(lovedGifts) +
                ", description='" + description + '\'' +
                '}';
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Villager villager = (Villager) o;
        return id == villager.id && marriageCandidate == villager.marriageCandidate &&
                heartCounter == villager.heartCounter && Objects.equals(name, villager.name) &&
                Objects.equals(birthday, villager.birthday) && Arrays.equals(lovedGifts, villager.lovedGifts) &&
                Objects.equals(description, villager.description);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(id, name, marriageCandidate, birthday, heartCounter, description);
        result = 31 * result + Arrays.hashCode(lovedGifts);
        return result;
    }
}
