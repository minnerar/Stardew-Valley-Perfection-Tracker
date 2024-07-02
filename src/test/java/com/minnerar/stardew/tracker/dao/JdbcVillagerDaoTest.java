package com.minnerar.stardew.tracker.dao;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.minnerar.dao.JdbcVillagerDao;
import org.minnerar.model.Villager;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;
import static java.lang.constant.ConstantDescs.NULL;

public class JdbcVillagerDaoTest extends BaseDaoTest {
    private Villager TEST_VILLAGER_ONE = new Villager(1, "Murphy", FALSE, "Winter 3", 0, "A good boy!", "Hamburger",
        "Fetch", "Treat", "Ball", "NULL", "NULL", "NULL", "NULL", "NULL",
        "NULL", "NULL", "NULL");
    private Villager TEST_VILLAGER_TWO = new Villager(2, "Mister Potato", FALSE, "Spring 1", 0, "A friendly cat!",
            "Mice", "Yarn", "Fish", "Sleep", "The Zoomies", "NULL", "NULL",
            "NULL", "NULL", "NULL", "NULL", "NULL");
    private Villager TEST_VILLAGER_THREE = new Villager(3, "Harry", TRUE, "Summer 31", 0,
            "The Boy who Lived","Magic", "Flying",             "Sirius", "Quidditch",
            "Chocolate Frogs", "Family", "Adventure", "Lupin", "Ron", "Hermione", "Pumpkin Juice", "Fluffy");
    private Villager TEST_VILLAGER_FOUR = new Villager(4, "Test Villager Four", TRUE, "Winter 28", 0, "An ordinary girl.", "Adventure",
            "Sewing", "Cooking", "Calcifer", "Howl", "Magic", "NULL", "NULL", "NULL", "NULL", "NULL", "NULL");

//    ('Murphy', FALSE, 'Winter 3', 'Hamburger', 'Fetch', 'Treat', 'Ball', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'A good boy!'),
//    ('Mister Potato', FALSE, 'Spring 1', 'Mice', 'Yarn', 'Fish', 'Sleep', 'The Zoomies', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'A friendly cat!'),
//    ('Harry', TRUE, 'Summer 31', 'Magic', 'Flying', 'Sirius', 'Quidditch', 'Chocolate Frogs',
//      'Family', 'Adventure', 'Lupin', 'Ron', 'Hermione', 'Pumpkin Juice', 'Fluffy', 'The boy who lived'),
//    ('Sophie', TRUE, 'Winter 28', 'Adventure', 'Sewing', 'Cooking', 'Calcifer', 'Howl', 'Magic', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'An ordinary girl.');
    private JdbcVillagerDao sut;
    private JdbcVillagerDao invalidConnectionDao;

    @Before
    public void setup() {
        sut = new JdbcVillagerDao((JdbcTemplate) dataSource);
        // wont work without casting to JdbcTemplate
        invalidConnectionDao = new JdbcVillagerDao((JdbcTemplate) invalidDataSource);
        // wont work without casting to JdbcTemplate
    }

    public Villager getVillagerByIdTestVerification(int id) {
        // get an Villager by the Villager id
        // return all Villager information
        Villager actual = null;
        String sql = "SELECT * FROM Villager WHERE villager = ?";
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, id);
        if (results.next()) {
            Villager mapped = new Villager();
            mapped.setVillagerId(results.getInt("villager_id"));
            mapped.setVillagerName(results.getString("name"));
            mapped.setVillagerMarriageCandidate(results.getBoolean("marriage_candidate"));
            mapped.setVillagerBirthday(results.getString("birthday"));
            List<String> loved = new ArrayList<>();
                loved.add(results.getString("loved1"));
                loved.add(results.getString("loved2"));
                loved.add(results.getString("loved3"));
                loved.add(results.getString("loved4"));
                loved.add(results.getString("loved5"));
                loved.add(results.getString("loved6"));
                loved.add(results.getString("loved7"));
                loved.add(results.getString("loved8"));
                loved.add(results.getString("loved9"));
                loved.add(results.getString("loved10"));
                loved.add(results.getString("loved11"));
                loved.add(results.getString("loved12"));
            mapped.setVillagerLovedGifts(loved);
            mapped.setVillagerDescription(results.getString("description"));
            actual = mapped;
        }
        return actual;
    }

    public void assertVillagersMatch(String message, Villager expected, Villager actual) {
        Assert.assertEquals(message, expected.getVillagerId(), actual.getVillagerId());
        Assert.assertEquals(message, expected.getVillagerName(), actual.getVillagerName());
        Assert.assertEquals(message, expected.getVillagerBirthday(), actual.getVillagerBirthday());
        Assert.assertEquals(message, expected.isVillagerMarriageCandidate(), actual.isVillagerMarriageCandidate());
        Assert.assertEquals(message, expected.getVillagerLovedGifts(), actual.getVillagerLovedGifts());
        Assert.assertEquals(message, expected.getVillagerDescription(), actual.getVillagerDescription());
    }

    @Test
    public void createVillagerTest() {
        // create a new Villager to track in the game
        // return the Villager object with the updated fields
        Villager newVillager = new Villager();
        newVillager.setVillagerId(1);
        newVillager.setVillagerName("Test Villager One");

        Villager created = sut.createVillager(newVillager);

        Assert.assertNotNull("createVillager returned a null Villager", created);
        Assert.assertTrue("createVillager did not return a Villager with id set.", created.getVillagerId() > 0);
        Assert.assertEquals("createVillager did not return an Villager with the correct name.", newVillager.getVillagerName(), created.getVillagerName());

        // verify value as saved to the database, retrieve it and compare values
        Villager retrieved = getVillagerByIdTestVerification(created.getVillagerId());
        Assert.assertNotNull("createVillager does not appear to have correctly created a new Villager. It could not be found by id.", retrieved);
        assertVillagersMatch("createVillager does not appear to have correctly created a new Villager. The retrieved Villager is incorrect / incomplete.", created, retrieved);

    }

    @Test
    public void updateVillagerTest(){
        //    ('Sophie', TRUE, 'Winter 28', 'Adventure', 'Sewing', 'Cooking', 'Calcifer', 'Howl', 'Magic', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'An ordinary girl.');
        Villager existing = new Villager();
        existing.setVillagerId(TEST_VILLAGER_TWO.getVillagerId());
        existing.setVillagerName("Sophie");
        existing.setVillagerMarriageCandidate(TRUE);
        List<String> lovedGifts = new ArrayList<>();
            lovedGifts.add("Adventure");
            lovedGifts.add("Sewing");
            lovedGifts.add("Cooking");
            lovedGifts.add("Calcifer");
            lovedGifts.add("Howl");
            lovedGifts.add("Magic");
        existing.setVillagerLovedGifts(lovedGifts);
        existing.setVillagerBirthday("Winter 28");
        existing.setVillagerDescription("An ordinary girl.");

        Villager updated = sut.createVillager(existing);
        Assert.assertNotNull("updateVillager returned a null Villager.", updated);
        assertVillagersMatch("updateVillager returned an incomplete / incorrect Villager.", updated, existing);

        Villager retrieved = getVillagerByIdTestVerification(TEST_VILLAGER_FOUR.getVillagerId());
        assertVillagersMatch("updateVillager does not appear to have properly updated the Villager.", updated, retrieved);
    }

    @Test
    public void deleteVillagerByIdTest() {
        // deletes a villager from the database
        // returns the number of deleted villagers
        int rowsAffected = sut.deleteVillager(TEST_VILLAGER_THREE.getVillagerId());
        Assert.assertEquals("deleteVillager did not return the correct number of rows affected.", 1, rowsAffected);
        Villager retrieved = getVillagerByIdTestVerification(TEST_VILLAGER_THREE.getVillagerId());
        Assert.assertNotNull("deleteVillager did not remove the Villager from the database.", retrieved);
    }

}
