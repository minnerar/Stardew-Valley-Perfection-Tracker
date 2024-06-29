package org.minnerar.model;

public class Cooking {

    private int id;
    private String name;
    private String[] ingredients;
    private String buff;
    private String buffDuration;
    private String recipeLocation;
    private String description;

    public int getCookingId() {
        return id;
    }

    public void setCookingId(int id) {
        this.id = id;
    }

    public String getCookingName() {
        return name;
    }

    public void setCookingName(String name) {
        this.name = name;
    }

    public String[] getCookingIngredients() {
        return ingredients;
    }

    public void setCookingIngredients(String[] ingredients) {
        this.ingredients = ingredients;
    }

    public String getCookingBuff() {
        return buff;
    }

    public void setCookingBuff(String buff) {
        this.buff = buff;
    }

    public String getCookingBuffDuration() {
        return buffDuration;
    }

    public void setCookingBuffDuration(String buffDuration) {
        this.buffDuration = buffDuration;
    }

    public String getCookingRecipeLocation() {
        return recipeLocation;
    }

    public void setCookingRecipeLocation(String recipeLocation) {
        this.recipeLocation = recipeLocation;
    }

    public String getCookingDescription() {
        return description;
    }

    public void setCookingDescription(String description) {
        this.description = description;
    }
}
