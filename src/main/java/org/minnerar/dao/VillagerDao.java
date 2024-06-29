package org.minnerar.dao;

import org.minnerar.model.Achievement;
import org.minnerar.model.Villager;

import java.util.List;

public interface VillagerDao {

    Villager getVillagerById(int id);
    // get a villager by the villager id
    // return all villager information

    List<Villager> getVillagers();
    // @return all villagers from the database
    // if none in the database, return an empty list

    Villager getMarriageableVillagers();
    // get all the marriage candidates
    // return the villagers where marriage candidate = true

}
