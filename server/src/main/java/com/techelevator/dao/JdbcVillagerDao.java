package com.techelevator.dao;

import com.techelevator.exception.DaoException;
import com.techelevator.exception.Villager;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcVillagerDao implements VillagerDao {

    private final JdbcTemplate template;

    public JdbcVillagerDao(DataSource dataSource) {
        this.template = new JdbcTemplate(dataSource);
    }

//    public JdbcVillagerDao(JdbcTemplate template) {
//        this.template = template;
//    }

    @Override
    public Villager getVillagerById(int id) {
        Villager villager = new Villager();
        String sql = "SELECT * FROM villager WHERE villager_id = ?";
        try {
            SqlRowSet rowSet = template.queryForRowSet(sql, id);
            if (rowSet.next()) {
                villager = mapRowToVillager(rowSet);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database.", e);
        }
        return villager;
    }

    @Override
    public Villager getVillagerByName(String name) {
        Villager villager = new Villager();
        String sql = "SELECT * FROM villager WHERE name = ?";
        try {
            SqlRowSet rowSet = template.queryForRowSet(sql, name);
            if (rowSet.next()) {
                villager = mapRowToVillager(rowSet);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database.", e);
        }
        return villager;
    }

    @Override
    public List<Villager> getVillagers() {
        List<Villager> villagers = new ArrayList<>();
        String sql = "SELECT * FROM villager";
        try {
            SqlRowSet results = template.queryForRowSet(sql);
            while (results.next()) {
                villagers.add(mapRowToVillager(results));
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database.", e);
        }
        return villagers;
    }

    @Override
    public Villager getMarriageableVillagers(Boolean marriageable) {
        Villager villager = new Villager();
        String sql = "SELECT * FROM villager WHERE marriage_candidate = ?";
        try {
            SqlRowSet rowSet = template.queryForRowSet(sql, marriageable);
            if (rowSet.next()) {
                villager = mapRowToVillager(rowSet);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database.", e);
        }
        return villager;
    }

    @Override
    public Villager createVillager(Villager villager) {
        Villager newVillager = null;
        String sql = "INSERT INTO villager (villager_id, name, marriage_candidate, birthday, loved1, loved2, loved3, loved4," +
                "loved5, loved6, loved7, loved8, loved9, loved10, loved11, loved12, description, imageURL) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, " +
                "?, ?, ?, ?, ?, ?, ?, ?, ?, ?) RETURNING villager_id";
        List<String> lovedList = villager.getVillagerLovedGifts();
        try {
            int villagerId = template.queryForObject(sql, int.class, villager.getVillagerId(), villager.getVillagerName(), villager.isVillagerMarriageCandidate(),
                    villager.getVillagerBirthday(), lovedList.get(0), lovedList.get(1), lovedList.get(2), lovedList.get(3), lovedList.get(4),
                    lovedList.get(5), lovedList.get(6), lovedList.get(7), lovedList.get(8), lovedList.get(9), lovedList.get(10),
                    lovedList.get(11), villager.getVillagerDescription(), villager.getImageURL());
            newVillager = getVillagerById(villagerId);
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (
                DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        } catch (NullPointerException e) {
            throw new DaoException("Null pointer exception", e);
        }
        return newVillager;
    }

    @Override
    public Villager updateVillager(Villager villager) {
        Villager updatedVillager = null;
        String sql = "UPDATE villager SET villager_id = ?, name = ?, marriage_candidate = ?, birthday = ?, loved1 = ?, loved2 = ?, loved3 = ?," +
                "loved4 = ?, loved5 = ?, loved6 = ?, loved7 = ?, loved8 = ?, loved9 = ?, loved10 = ?," +
                "loved11 = ?, loved12 = ?, description = ?, heartCounter = ? WHERE villager_id = ?";
        List<String> loved = villager.getVillagerLovedGifts();
        // added loved gifts to a new List<String> so that they can be pulled and added to the updated villager
        try {
            int rowsAffected = template.update(sql, villager.getVillagerId(), villager.getVillagerName(), villager.isVillagerMarriageCandidate(),
                    villager.getVillagerBirthday(), loved.get(0), loved.get(1), loved.get(2), loved.get(3), loved.get(4), loved.get(5),
                    loved.get(6),loved.get(7),loved.get(8),loved.get(9),loved.get(10),loved.get(11), villager.getVillagerDescription(), villager.getVillagerHeartCounter(), villager.getVillagerId());
            if (rowsAffected == 0) {
                throw new DaoException("Zero rows affected, expected at least one.");
            }
            updatedVillager = getVillagerById(villager.getVillagerId());
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        } catch (NullPointerException e) {
            throw new DaoException("Null pointer exception", e);
        }
        return updatedVillager;
    }

    @Override
    public int deleteVillager(int id) {
        int numberOfRows = 0;
        String deleteVillagerSql = "DELETE FROM villager WHERE villager_id = ?";

        try {
            numberOfRows = template.update(deleteVillagerSql, id);
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }
        return numberOfRows;
    }

    private Villager mapRowToVillager(SqlRowSet results) {
        Villager villager = new Villager();
        villager.setVillagerId(results.getInt("villager_id"));
        villager.setVillagerName(results.getString("name"));
        villager.setVillagerBirthday(results.getString("birthday"));
        villager.setVillagerMarriageCandidate(Boolean.parseBoolean(results.getString("marriage_candidate")));
        List<String> lovedGifts = new ArrayList<>();
            // add all the loved gifts into an array, loved3 to loved12 can be null
            lovedGifts.add(results.getString("loved1"));
            lovedGifts.add(results.getString("loved2"));
            lovedGifts.add(results.getString("loved3"));
            lovedGifts.add(results.getString("loved4"));
            lovedGifts.add(results.getString("loved5"));
            lovedGifts.add(results.getString("loved6"));
            lovedGifts.add(results.getString("loved7"));
            lovedGifts.add(results.getString("loved8"));
            lovedGifts.add(results.getString("loved9"));
            lovedGifts.add(results.getString("loved10"));
            lovedGifts.add(results.getString("loved11"));
            lovedGifts.add(results.getString("loved12"));
        villager.setVillagerLovedGifts(lovedGifts);
        villager.setVillagerDescription(results.getString("description"));
        villager.setImageURL(results.getString("imageURL"));
        villager.setVillagerHeartCounter(results.getInt("heartCounter"));
        return villager;
    }
}
