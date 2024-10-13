package com.techelevator.model;

import java.util.Objects;

public class Classification {

    private int classificationId;
    private String name;

    public Classification(int id, String name) {
        this.classificationId = id;
        this.name = name;
    }

    public Classification() {

    }

    public int getClassificationId() {
        return classificationId;
    }

    public void setClassificationId(int id) {
        this.classificationId = id;
    }

    public String getClassificationName() {
        return name;
    }

    public void setClassificationName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Classification{" +
                "id=" + classificationId +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Classification that = (Classification) o;
        return classificationId == that.classificationId && Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(classificationId, name);
    }
}
