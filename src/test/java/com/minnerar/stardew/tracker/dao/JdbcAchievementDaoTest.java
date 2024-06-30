package com.minnerar.stardew.tracker.dao;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.minnerar.dao.JdbcAchievementDao;
import org.minnerar.model.Achievement;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import java.util.List;

public class JdbcAchievementDaoTest extends BaseDaoTest {

    private JdbcAchievementDao sut;
    private JdbcAchievementDao invalidConnectionDao;

    @Before
    public void setup() {
        sut = new JdbcAchievementDao(dataSource);
        invalidConnectionDao = new JdbcAchievementDao(invalidDataSource);
    }
    @Test
    public void getAchievementByIdTest(int id) {
        // get an achievement by the achievement id
        // return all achievement information

    }

    @Test
    private void getAchievementsTest() {
        // @return all achievements from the database
        // if none in the database, return an empty list

    }

    @Test
    private void createAchievementTest(Achievement achievement) {
        // create a new Achievement to track in the game
        // return the achievement object with the updated fields

    }

    @Test
    private void updateAchievementTest(Achievement achievement){
        // removes an achievement from the database
        // returns the number of updated achievements

    }

    @Test
    private void deleteAchievementByIdTest(int id) {
        // deletes an achievement from the database
        // returns the number of deleted achievements

    }

    @Test
    private void mapRowToAchievementTest(SqlRowSet results) {

    }

}
