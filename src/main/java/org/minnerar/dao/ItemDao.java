package org.minnerar.dao;

import org.minnerar.model.Achievement;
import org.minnerar.model.Item;

import java.util.List;

public interface ItemDao {

    Item getItemById(int id);
    // get an item by the item id
    // return all item information

    Item getItemByName(String name);
    // get an item by the name
    // return all item information

    List<Item> getItemsBySeason(String season);
    // get a list of items by season
    // return all item names

    List<Item> getItems();
    // @return all achievements from the database
    // if none in the database, return an empty list

    List<Item> getItemsByAchievementId(int id);
    // list all the items needed for an achievement
    // returns a list of items

}
