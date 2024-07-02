package com.minnerar.stardew.tracker.dao;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.minnerar.dao.JdbcClassificationDao;
import org.minnerar.model.Classification;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

public class JdbcClassificationDaoTest extends BaseDaoTest {

    private Classification TEST_CLASSIFICATION_ONE = new Classification(1, "Test Classification One");
    private Classification TEST_CLASSIFICATION_TWO = new Classification(2, "Test Classification Two");
    private Classification TEST_CLASSIFICATION_THREE = new Classification(3, "Test Classification Three");
    private Classification TEST_CLASSIFICATION_FOUR = new Classification(4, "Test Classification Four");

    private JdbcClassificationDao sut;
    private JdbcClassificationDao invalidConnectionDao;

    @Before
    public void setup() {
        sut = new JdbcClassificationDao(dataSource);
        // wont work without casting to JdbcTemplate
        invalidConnectionDao = new JdbcClassificationDao(invalidDataSource);
        // wont work without casting to JdbcTemplate
    }

    public Classification getClassificationByIdTestVerification(int id) {
        // get an Classification by the Classification id
        // return all Classification information
        Classification actualClassification = null;
        String sql = "SELECT * FROM classification WHERE classification_id = ?";
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, id);
        if (results.next()) {
            Classification mappedClassification = new Classification();
            mappedClassification.setClassificationId(results.getInt("classification_id"));
            mappedClassification.setClassificationName(results.getString("name"));
            actualClassification = mappedClassification;
        }
        return actualClassification;
    }

    public void assertClassificationsMatch(String message, Classification expected, Classification actual) {
        Assert.assertEquals(message, expected.getClassificationId(), actual.getClassificationId());
        Assert.assertEquals(message, expected.getClassificationName(), actual.getClassificationName());
    }

    @Test
    public void createClassificationTest() {
        // create a new Classification to track in the game
        // return the Classification object with the updated fields
        Classification newClassification = new Classification();
        newClassification.setClassificationId(15);
        newClassification.setClassificationName("Test Classification One");

        Classification created = sut.createClassification(newClassification);

        Assert.assertNotNull("createClassification returned a null Classification", created);
        Assert.assertTrue("createClassification did not return a Classification with id set.", created.getClassificationId() > 0);
        Assert.assertEquals("createClassification did not return an Classification with the correct name.", newClassification.getClassificationName(), created.getClassificationName());

        // verify value as saved to the database, retrieve it and compare values
        Classification retrieved = getClassificationByIdTestVerification(created.getClassificationId());
        Assert.assertNotNull("createClassification does not appear to have correctly created a new Classification. It could not be found by id.", retrieved);
        assertClassificationsMatch("createClassification does not appear to have correctly created a new Classification. The retrieved Classification is incorrect / incomplete.", created, retrieved);

    }

    @Test
    public void updateClassificationTest(){
        // removes an Classification from the database
        // returns the number of updated Classifications
        Classification existing = new Classification();
        existing.setClassificationId(TEST_CLASSIFICATION_TWO.getClassificationId());
        existing.setClassificationName("Test Classification Two");

        Classification updated = sut.updateClassification(existing);
        Assert.assertNotNull("updateClassification returned a null Classification.", updated);
        assertClassificationsMatch("updateClassification returned an incomplete / incorrect Classification.", updated, existing);

        Classification retrievedClassification = getClassificationByIdTestVerification(TEST_CLASSIFICATION_TWO.getClassificationId());
        assertClassificationsMatch("updateClassification does not appear to have properly updated the Classification.", updated, retrievedClassification);
    }

    @Test
    public void deleteClassificationByIdTest() {
        // deletes a classification from the database
        // returns the number of deleted Classifications
        int rowsAffected = sut.deleteClassificationById(TEST_CLASSIFICATION_FOUR.getClassificationId());
        Assert.assertEquals("deleteClassification did not return the correct number of rows affected.", 1, rowsAffected);
        Classification retrieved = getClassificationByIdTestVerification(TEST_CLASSIFICATION_FOUR.getClassificationId());
        Assert.assertNull("deleteClassification did not remove the Classification from the database.", retrieved);
    }

}
