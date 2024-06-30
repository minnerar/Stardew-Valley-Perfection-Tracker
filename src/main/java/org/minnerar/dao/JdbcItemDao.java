package org.minnerar.dao;

import org.minnerar.exception.DaoException;
import org.minnerar.model.Achievement;
import org.minnerar.model.Item;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcItemDao implements ItemDao {

    private final JdbcTemplate template;

    public JdbcItemDao(JdbcTemplate template) {
        this.template = template;
    }
    @Override
    public Item getItemById(int id) {
        Item item = null;
        String sql = "SELECT * FROM item WHERE item_id = ?";
        try {
            SqlRowSet rowSet = template.queryForRowSet(sql, id);
            if (rowSet.next()) {
                item = mapRowToItem(rowSet);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database.", e);
        }
        return item;
    }

    @Override
    public Item getItemByName(String name) {
        Item item = null;
        String sql = "SELECT * FROM item WHERE name = ?";
        try {
            SqlRowSet rowSet = template.queryForRowSet(sql, name);
            if (rowSet.next()) {
                item = mapRowToItem(rowSet);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database.", e);
        }
        return item;
    }

    @Override
    public List<Item> getItemsBySeason(String season) {
        List<Item> items = new ArrayList<>();
        String sql = "SELECT item_id, name, description FROM item WHERE season = ?";
        try {
            SqlRowSet results = template.queryForRowSet(sql, season);
            while (results.next()) {
                items.add(mapRowToItem(results));
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database.", e);
        }
        return items;
    }

    @Override
    public List<Item> getItemsByClassificationId(int classification) {
        List<Item> items = new ArrayList<>();
        String sql = "SELECT item_id, name, description FROM item WHERE classification = ?";
        try {
            SqlRowSet results = template.queryForRowSet(sql, classification);
            while (results.next()) {
                items.add(mapRowToItem(results));
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database.", e);
        }
        return items;
    }

//    @Override
//    public List<Item> getItemsByClassificationName(String name) {
//        List<Item> items = new ArrayList<>();
//        String sql = "SELECT item_id, name, description FROM item WHERE classification = ?";
//        try {
//            SqlRowSet results = template.queryForRowSet(sql, name);
//            while (results.next()) {
//                items.add(mapRowToItem(results));
//            }
//        } catch (CannotGetJdbcConnectionException e) {
//            throw new DaoException("Unable to connect to server or database.", e);
//        }
//        return items;
//    }

    @Override
    public List<Item> getItems() {
        List<Item> items = new ArrayList<>();
        String sql = "SELECT * FROM item";
        try {
            SqlRowSet results = template.queryForRowSet(sql);
            while (results.next()) {
                items.add(mapRowToItem(results));
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database.", e);
        }
        return items;
    }

    @Override
    public List<Item> getItemsByAchievementId(int id) {
        List<Item> items = new ArrayList<>();
        String sql = "SELECT item_id, name, description FROM item WHERE achievement_id = ?";
        try {
            SqlRowSet results = template.queryForRowSet(sql, id);
            while (results.next()) {
                items.add(mapRowToItem(results));
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database.", e);
        }
        return items;
    }

    @Override
    public Item createItem(Item item) {
        Item newItem = null;
        String sql = "INSERT INTO item (item_id, classification_id, name, completed, " +
                "season, time, weather, location, description, achievement_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?) RETURNING item_id";
        try {
            int itemId = template.queryForObject(sql, int.class, item.getItemId(), item.getItemClassification(), item.getItemName(),
                    item.isItemCompleted(), item.getItemSeason(), item.getItemTime(), item.getItemWeather(),
                    item.getItemLocation(), item.getItemDescription(), item.getItemAchievementId());
            newItem = getItemById(itemId);
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        } catch (NullPointerException e) {
            throw new DaoException("Null pointer exception", e);
        }
        return newItem;
    }

    @Override
    public Item updateItem(Item item) {
        Item updatedItem = null;
        String sql = "UPDATE item SET item_id = ?, classification_id = ?, name = ?, completed = ?, season = ?, " +
                "time = ?, weather = ?, location = ?, description = ?, achievement_id = ? WHERE item_id = ?";
        try {
            int rowsAffected = template.update(sql, item.getItemId(), item.getItemClassification(), item.getItemName(),
                    item.isItemCompleted(), item.getItemSeason(), item.getItemTime(), item.getItemWeather(),
                    item.getItemLocation(), item.getItemDescription(), item.getItemAchievementId(), item.getItemId());
            if (rowsAffected == 0) {
                throw new DaoException("Zero rows affected, expected at least one.");
            }
            updatedItem = getItemById(item.getItemId());
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        } catch (NullPointerException e) {
            throw new DaoException("Null pointer exception", e);
        }
        return updatedItem;
    }

    @Override
    public int deleteItem(int id) {
        int numberOfRows = 0;

        String deleteItemSql = "DELETE FROM item WHERE item_id = ?";
        String updateItemClassification = "UPDATE item_classification SET item_id = 0 WHERE item_id = ?";
        String updateItemAchievement = "UPDATE achievement_item SET item_id = 0 WHERE item_id = ?";

        try {
            template.update(updateItemClassification, id);
            template.update(updateItemAchievement, id);
            numberOfRows = template.update(deleteItemSql, id);
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }
        return numberOfRows;
    }

    private Item mapRowToItem(SqlRowSet results) {
        Item item = new Item();
        item.setItemId(results.getInt("item_id"));
        item.setItemName(results.getString("name"));
        item.setItemCompleted(results.getBoolean("completed"));
        item.setItemSeason(results.getString("season"));
        item.setItemTime(results.getString("time"));
        item.setItemWeather(results.getString("weather"));
        item.setItemLocation(results.getString("location"));
        item.setItemDescription(results.getString("description"));
        item.setItemClassification(results.getInt("classification_id"));
        return item;
    }

}
