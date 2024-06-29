package org.minnerar.model;

public class Crafting {

    private int id;
    private String name;
    private String[] ingredients;
    private String recipe;
    private String description;

    public int getCraftingId() {
        return id;
    }

    public void setCraftingId(int id) {
        this.id = id;
    }

    public String getCraftingName() {
        return name;
    }

    public void setCraftingName(String name) {
        this.name = name;
    }

    public String[] getCraftingIngredients() {
        return ingredients;
    }

    public void setCraftingIngredients(String[] ingredients) {
        this.ingredients = ingredients;
    }

    public String getCraftingRecipe() {
        return recipe;
    }

    public void setCraftingRecipe(String recipe) {
        this.recipe = recipe;
    }

    public String getCraftingDescription() {
        return description;
    }

    public void setCraftingDescription(String description) {
        this.description = description;
    }
}
