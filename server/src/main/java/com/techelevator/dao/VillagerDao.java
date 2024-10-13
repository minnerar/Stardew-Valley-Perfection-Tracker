package com.techelevator.dao;

import com.techelevator.exception.Villager;

import java.util.List;

public interface VillagerDao {

    Villager getVillagerById(int id);
    // get a villager by the villager id
    // return all villager information

    Villager getVillagerByName(String name);
    // get a villager by their name
    // return all villager information

    List<Villager> getVillagers();
    // @return all villagers from the database
    // if none in the database, return an empty list

    Villager getMarriageableVillagers(Boolean marriageable);
    // get all the marriage candidates
    // return the villagers where marriage candidate = true

    Villager createVillager(Villager villager);
    // create a new villager
    // returns the new villager information

    Villager updateVillager(Villager villager);
    // updates an existing villager
    // returns the updated villager information

    int deleteVillager(int id);
    // deletes a villager
    // returns the number deleted

}
