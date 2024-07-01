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

    private Achievement TEST_ACHIEVEMENT_ONE = new Achievement(1, "Test Achievement One", 100, 0, "Test Achievement 1 description");
    private Achievement TEST_ACHIEVEMENT_TWO = new Achievement(2, "Test Achievement Two", 1, 0, "Test Achievement 2 description");
    private Achievement TEST_ACHIEVEMENT_THREE = new Achievement(3, "Test Achievement Three", 0, 0, "Test Achievement 3 description");
    private Achievement TEST_ACHIEVEMENT_FOUR = new Achievement(4, "Test Achievement Four", 50, 0, "Test Achievement 4");

    @Before
    public void setup() {
        sut = new JdbcAchievementDao(dataSource);
        invalidConnectionDao = new JdbcAchievementDao(invalidDataSource);
    }
    @Test
    public void getAchievementByIdTest(int id) {
        // get an achievement by the achievement id
        // return all achievement information
        Achievement achievement = null;
        String sql = "SELECT * FROM achievement WHERE achievement_id = ?";
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, id);


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

    public void assertAchievementsMatch(String message, Achievement expected, Achievement actual) {
        Assert.assertEquals(message, expected.getAchievementId(), actual.getAchievementId());
        Assert.assertEquals(message, expected.getAchievementCurrent(), actual.getAchievementCurrent());
        Assert.assertEquals(message, expected.getAchievementDescription(), actual.getAchievementDescription());
        Assert.assertEquals(message, expected.getAchievementName(), actual.getAchievementName());
        Assert.assertEquals(message, expected.getAchievementTotalNeeded(), actual.getAchievementTotalNeeded());
        Assert.assertEquals(message, (double)expected.getAchievementProgress(), (double)actual.getAchievementProgress(), 0.0);
    }

}
