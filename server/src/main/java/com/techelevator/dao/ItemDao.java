package com.techelevator.dao;


import com.techelevator.exception.Item;

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

    List<Item> getItemsByClassificationId(int classification);
    // get a list of items by classification
    // return all item names

    List<Item> getItemsByClassificationNameInItem(String name);
    // get a list of items by classification
    // return all item names

    List<Item> getItems();
    // @return all achievements from the database
    // if none in the database, return an empty list

    List<Item> getItemsByAchievementId(int id);
    // list all the items needed for an achievement
    // returns a list of items

    Item createItem(Item item);
    // creates a new item

    Item updateItem(Item item);
    // updates an item

    int deleteItem(int id);

}
