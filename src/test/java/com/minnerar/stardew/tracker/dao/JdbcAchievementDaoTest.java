package com.minnerar.stardew.tracker.dao;

import org.checkerframework.checker.units.qual.A;
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

    public Achievement getAchievementByIdTestVerification(int id) {
        // get an achievement by the achievement id
        // return all achievement information
        Achievement actualAchievement = null;
        String sql = "SELECT * FROM achievement WHERE achievement_id = ?";
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, id);
        if (results.next()) {
            Achievement mappedAchievement = new Achievement();
            mappedAchievement.setAchievementId(results.getInt("achievement_id"));
            mappedAchievement.setAchievementName(results.getString("name"));
            mappedAchievement.setAchievementCurrent(results.getInt("progress"));
            mappedAchievement.setAchievementTotalNeeded(results.getInt("total_needed"));
//            mappedAchievement.setAchievementProgress((double)(results.getInt("current")*100)/results.getInt("total_needed"));
            mappedAchievement.setAchievementDescription(results.getString("description"));
            actualAchievement = mappedAchievement;
        }
        return actualAchievement;
    }

    @Test
    public void createAchievementTest() {
        // create a new Achievement to track in the game
        // return the achievement object with the updated fields
        Achievement newAchievement = new Achievement();
        newAchievement.setAchievementId(1);
        newAchievement.setAchievementName("Test Achievement One");
        newAchievement.setAchievementCurrent(0);
        newAchievement.setAchievementTotalNeeded(100);
        newAchievement.setAchievementDescription("Test Achievement 1 description");

        Achievement createdAchievement = sut.createAchievement(newAchievement);

        Assert.assertNotNull("createAchievement returned a null employee", createdAchievement);
        Assert.assertTrue("createAchievement did not return an employee with id set.", createdAchievement.getAchievementId() > 0);
        Assert.assertEquals("createAchievement did not return an achievement with the correct name.", newAchievement.getAchievementName(), createdAchievement.getAchievementName());
        Assert.assertEquals("createAchievement did not return an achievement with the correct current amount.", newAchievement.getAchievementCurrent(), createdAchievement.getAchievementCurrent());
        Assert.assertEquals("createAchievement did not return an achievement with the correct total needed amount.", newAchievement.getAchievementTotalNeeded(), createdAchievement.getAchievementTotalNeeded());
        Assert.assertEquals("createAchievement did not return an achievement with the correct description.", newAchievement.getAchievementDescription(), createdAchievement.getAchievementDescription());

        // verify value as saved to the database, retrieve it and compare values
        Achievement retrievedAchievement = getAchievementByIdTestVerification(createdAchievement.getAchievementId());
        Assert.assertNotNull("createAchievement does not appear to have correctly created a new Achievement. It could not be found by id.", retrievedAchievement);
        assertAchievementsMatch("createAchievement does not appear to have correctly created a new Achievement. The retrieved achievement is incorrect / incomplete.", createdAchievement, retrievedAchievement);

    }

    @Test
    public void updateAchievementTest(){
        // removes an achievement from the database
        // returns the number of updated achievements
        Achievement existingAchievement = new Achievement();
        existingAchievement.setAchievementId(TEST_ACHIEVEMENT_TWO.getAchievementId());
        existingAchievement.setAchievementName("Test Achievement One");
        existingAchievement.setAchievementCurrent(0);
        existingAchievement.setAchievementTotalNeeded(100);
        existingAchievement.setAchievementDescription("Test Achievement 1 description");

        Achievement updatedAchievement = sut.updateAchievement((existingAchievement));
        Assert.assertNotNull("updateAchievement returned a null Achievement.", updatedAchievement);
        assertAchievementsMatch("updateAchievement returned an incomplete / incorrect Achievement.", updatedAchievement, existingAchievement);

        Achievement retrievedAchievement = getAchievementByIdTestVerification(TEST_ACHIEVEMENT_TWO.getAchievementId());
        assertAchievementsMatch("updateAchievement does not appear to have properly updated the Achievement.", updatedAchievement, retrievedAchievement);

    }

    @Test
    public void deleteAchievementByIdTest() {
        // deletes an achievement from the database
        // returns the number of deleted achievements
        int rowsAffected = sut.deleteAchievementById(TEST_ACHIEVEMENT_THREE.getAchievementId());
        Assert.assertEquals("deleteAchievement did not return the correct number of rows affected.", 1, rowsAffected);
        Achievement retrievedAchievement = getAchievementByIdTestVerification(TEST_ACHIEVEMENT_THREE.getAchievementId());
        Assert.assertNotNull("deleteAchievement did not remove the employee from the database.", retrievedAchievement);
    }

    public void assertAchievementsMatch(String message, Achievement expected, Achievement actual) {
        Assert.assertEquals(message, expected.getAchievementId(), actual.getAchievementId());
        Assert.assertEquals(message, expected.getAchievementCurrent(), actual.getAchievementCurrent());
        Assert.assertEquals(message, expected.getAchievementDescription(), actual.getAchievementDescription());
        Assert.assertEquals(message, expected.getAchievementName(), actual.getAchievementName());
        Assert.assertEquals(message, expected.getAchievementTotalNeeded(), actual.getAchievementTotalNeeded());
    }

}
