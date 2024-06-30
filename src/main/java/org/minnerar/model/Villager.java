package org.minnerar.model;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class Villager {

    private int id;
    private String name;
    private boolean marriageCandidate;
    private String birthday;
    private int heartCounter;
    private List<String> lovedGifts;
    private String description;
    private String loved1;
    private String loved2;
    private String loved3;
    private String loved4;
    private String loved5;
    private String loved6;
    private String loved7;
    private String loved8;
    private String loved9;
    private String loved10;
    private String loved11;
    private String loved12;


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

    public List<String> getVillagerLovedGifts() {
        return lovedGifts;
    }

    public void setVillagerLovedGifts(List<String> lovedGifts) {
        lovedGifts.add(loved1);
        lovedGifts.add(loved2);
        lovedGifts.add(loved3);
        lovedGifts.add(loved4);
        lovedGifts.add(loved5);
        lovedGifts.add(loved6);
        lovedGifts.add(loved7);
        lovedGifts.add(loved8);
        lovedGifts.add(loved9);
        lovedGifts.add(loved10);
        lovedGifts.add(loved11);
        lovedGifts.add(loved12);
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
                ", lovedGifts=" + lovedGifts +
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
                Objects.equals(birthday, villager.birthday) && Objects.equals(lovedGifts, villager.lovedGifts) &&
                Objects.equals(description, villager.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, marriageCandidate, birthday, heartCounter, lovedGifts, description);
    }
}
