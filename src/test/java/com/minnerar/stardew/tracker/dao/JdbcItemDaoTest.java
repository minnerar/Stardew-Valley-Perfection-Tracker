package com.minnerar.stardew.tracker.dao;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.minnerar.dao.JdbcClassificationDao;
import org.minnerar.dao.JdbcItemDao;
import org.minnerar.model.Classification;
import org.minnerar.model.Item;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;

public class JdbcItemDaoTest extends BaseDaoTest {

    private Item TEST_ITEM_ONE = new Item(1, 4, "Test Item One", FALSE, "Spring", "Any", "Rain", "Forest", "Test Item 1 Description");
    private Item TEST_ITEM_TWO = new Item(2, 3, "Test Item Two", TRUE, "Summer", "Morning", "Sunny", "Mountain River", "Test Item 2 Description");
    private Item TEST_ITEM_THREE = new Item(3, 2, "Test Item Three", TRUE, "Fall", "Evening", "Windy", "Ocean", "Test Item 3 Description");
    private Item TEST_ITEM_FOUR = new Item(4, 1, "Test Item Four", FALSE, "Winter", "Afternoon", "Snow", "Mines", "Test Item 4 Description");

    private JdbcItemDao sut;
    private JdbcItemDao invalidConnectionDao;

    @Before
    public void setup() {
        sut = new JdbcItemDao((JdbcTemplate) dataSource);
        // wont work without casting to JdbcTemplate
        invalidConnectionDao = new JdbcItemDao((JdbcTemplate) invalidDataSource);
        // wont work without casting to JdbcTemplate
    }

    public Item getItemByIdTestVerification(int id) {
        // get an item by the item_id
        // return all item information
        Item actual = null;
        String sql = "SELECT * FROM item WHERE item_id = ?";
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, id);
        if (results.next()) {
            Item mapped = new Item();
            mapped.setItemId(results.getInt("item_id"));
            mapped.setItemName(results.getString("name"));
            mapped.setItemCompleted(results.getBoolean("completed"));
            mapped.setItemSeason(results.getString("season"));
            mapped.setItemTime(results.getString("time"));
            mapped.setItemWeather(results.getString("weather"));
            mapped.setItemLocation(results.getString("location"));
            mapped.setItemDescription(results.getString("description"));
            actual = mapped;
        }
        return actual;
    }

    public void assertItemsMatch(String message, Item expected, Item actual) {
        Assert.assertEquals(message, expected.getItemId(), actual.getItemId());
        Assert.assertEquals(message, expected.getItemName(), actual.getItemName());
        Assert.assertEquals(message, expected.isItemCompleted(), actual.isItemCompleted());
        Assert.assertEquals(message, expected.getItemSeason(), actual.getItemSeason());
        Assert.assertEquals(message, expected.getItemTime(), actual.getItemTime());
        Assert.assertEquals(message, expected.getItemWeather(), actual.getItemWeather());
        Assert.assertEquals(message, expected.getItemLocation(), actual.getItemLocation());
        Assert.assertEquals(message, expected.getItemDescription(), actual.getItemDescription());
    }

    @Test
    public void createItemTest() {
        // create a new Item to track in the game
        // return the Item object with the updated fields
        Item newItem = new Item();
        newItem.setItemId(1);
        newItem.setItemName("Test Item One");

        Item created = sut.createItem(newItem);

        Assert.assertNotNull("createItem returned a null Item", created);
        Assert.assertTrue("createItem did not return a Item with id set.", created.getItemId() > 0);
        Assert.assertEquals("createItem did not return an Item with the correct name.", newItem.getItemName(), created.getItemName());

        // verify value as saved to the database, retrieve it and compare values
        Item retrieved = getItemByIdTestVerification(created.getItemId());
        Assert.assertNotNull("createItem does not appear to have correctly created a new Item. It could not be found by id.", retrieved);
        assertItemsMatch("createItem does not appear to have correctly created a new Item. The retrieved Item is incorrect / incomplete.", created, retrieved);

    }

    @Test
    public void updateItemTest(){
        // removes an Item from the database
        // returns the number of updated Items
        // TEST_ITEM_THREE = new Item(3, 2, "Test Item Three", TRUE, "Fall", "Evening", "Windy", "Ocean", "Test Item 3 Description", 4);
        Item existing = new Item();
        existing.setItemId(TEST_ITEM_THREE.getItemId());
        existing.setItemName("Test Item Three");
        existing.setItemCompleted(TRUE);
        existing.setItemClassification(2);
        existing.setItemSeason("Fall");
        existing.setItemTime("Evening");
        existing.setItemWeather("Windy");
        existing.setItemLocation("Ocean");
        existing.setItemDescription("Test Item 3 Description");

        Item updated = sut.createItem(existing);
        Assert.assertNotNull("updateItem returned a null Item.", updated);
        assertItemsMatch("updateItem returned an incomplete / incorrect Item.", updated, existing);

        Item retrieved = getItemByIdTestVerification(TEST_ITEM_THREE.getItemId());
        assertItemsMatch("updateItem does not appear to have properly updated the Item.", updated, retrieved);
    }

    @Test
    public void deleteItemByIdTest() {
        // deletes a item from the database
        // returns the number of deleted items
        int rowsAffected = sut.deleteItem(TEST_ITEM_FOUR.getItemId());
        Assert.assertEquals("deleteItem did not return the correct number of rows affected.", 1, rowsAffected);
        Item retrieved = getItemByIdTestVerification(TEST_ITEM_FOUR.getItemId());
        Assert.assertNotNull("deleteItem did not remove the Item from the database.", retrieved);
    }

}
