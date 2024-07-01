package org.minnerar.model;

import java.util.Objects;

public class Classification {

    private int id;
    private String name;

    public Classification(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Classification() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Classification that = (Classification) o;
        return id == that.id && Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
