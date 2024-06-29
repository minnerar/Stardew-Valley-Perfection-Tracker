package org.minnerar.dao;

import org.minnerar.exception.DaoException;
import org.minnerar.model.Achievement;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import java.util.ArrayList;
import java.util.List;

public class JdbcAchievementDao implements AchievementDao {

    private final JdbcTemplate template;

    public JdbcAchievementDao(JdbcTemplate template) {
        this.template = template;
    }

    @Override
    public Achievement getAchievementById(int id) {
        Achievement achievement = null;
        // set up empty achievement object

        String sql = "SELECT * FROM achievement WHERE achievement_id = ?";

        try {
            SqlRowSet rowSet = template.queryForRowSet(sql);
            if (rowSet.next()) {
                achievement = mapRowToAchievement(rowSet);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database.", e);
        }
        return achievement;
    }

    @Override
    public List<Achievement> getAchievements() {
        List<Achievement> achievements = new ArrayList<>();

        String sql = "SELECT achievement_id, name, description FROM achievement";

        try {
            SqlRowSet results = template.queryForRowSet(sql);
            while (results.next()) {
                achievements.add(mapRowToAchievement(results));
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database.", e);
        }

        return achievements;
    }

    @Override
    public Achievement createAchievement(Achievement achievement) {
        Achievement newAchievement = null;

        String sql = "INSERT INTO achievement (name, description) VALUES (?, ?) RETURNING achievement_id";

        try {
            int achievementId = template.queryForObject(sql, int.class, achievement.getAchievementName());
            newAchievement = getAchievementById(achievementId);
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (
                DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        } catch (NullPointerException e) {
            throw new DaoException("Null pointer exception", e);
        }

        return newAchievement;
    }

    @Override
    public Achievement updateAchievement(Achievement achievement) {
        Achievement updatedAchievement = null;

        String sql = "INSERT INTO achievement (name, description) VALUES (?, ?) RETURNING achievement_id";

        try {
            int rowsAffected = template.update(sql, achievement.getAchievementName(), achievement.getAchievementId());
            if (rowsAffected == 0) {
                throw new DaoException("Zero rows affected, expected at least one.");
            }
            updatedAchievement = getAchievementById(achievement.getAchievementId());
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        } catch (NullPointerException e) {
            throw new DaoException("Null pointer exception", e);
        }

        return updatedAchievement;
    }

    @Override
    public int deleteAchievementById(int id) {
        int numberOfRows = 0;

        String deleteAchievementSql = "DELETE FROM achievement WHERE achievement_id = ?";
        String updateAchievement = "UPDATE item SET achievement_id = 0 WHERE achievement_id = ?";

        try {
            template.update(updateAchievement, id);
            numberOfRows = template.update(deleteAchievementSql, id);
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }
        return numberOfRows;
    }

    private Achievement mapRowToAchievement(SqlRowSet results) {
        Achievement achievement = new Achievement();
        achievement.setAchievementId(results.getInt("achievement_id"));
        achievement.setAchievementName(results.getString("name"));
        achievement.setAchievementCurrent(results.getInt("current"));
        achievement.setAchievementTotalNeeded(results.getInt("total_needed"));
        achievement.setAchievementDescription(results.getString("description"));
        return achievement;
    }

}
