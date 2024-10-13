package com.techelevator.dao;


import com.techelevator.exception.Achievement;

import java.util.List;

public interface AchievementDao {

    Achievement getAchievementById(int id);
    // get an achievement by the achievement id
    // return all achievement information

    List<Achievement> getAchievements();
    // @return all achievements from the database
    // if none in the database, return an empty list

    Achievement createAchievement(Achievement achievement);
    // create a new Achievement to track in the game
    // return the achievement object with the updated fields

    Achievement updateAchievement(Achievement achievement);
    // removes an achievement from the database
    // returns the number of updated achievements

    int deleteAchievementById(int id);
    // deletes an achievement from the database
    // returns the number of deleted achievements

}
