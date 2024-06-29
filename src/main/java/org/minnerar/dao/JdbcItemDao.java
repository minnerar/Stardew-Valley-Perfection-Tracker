package org.minnerar.dao;

import org.minnerar.model.Item;

import java.util.List;

public class JdbcItemDao implements ItemDao {
    @Override
    public Item getItemById(int id) {
        return null;
    }

    @Override
    public Item getItemByName(String name) {
        return null;
    }

    @Override
    public List<Item> getItemsBySeason(String season) {
        return null;
    }

    @Override
    public List<Item> getItems() {
        return null;
    }

    @Override
    public List<Item> getItemsByAchievementId(int id) {
        return null;
    }

    @Override
    public Item createItem() {
        return null;
    }

    @Override
    public Item updateItem() {
        return null;
    }

    @Override
    public int deleteItem() {
        return 0;
    }
}
