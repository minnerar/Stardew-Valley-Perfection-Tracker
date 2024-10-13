package com.techelevator.dao;


import com.techelevator.exception.Classification;
import com.techelevator.exception.DaoException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcClassificationDao implements ClassificationDao {

    private final JdbcTemplate template;

    public JdbcClassificationDao(DataSource dataSource) {
        this.template = new JdbcTemplate(dataSource);
    }

//    public JdbcClassificationDao(JdbcTemplate template) {
//        this.template = template;
//    }

    @Override
    public Classification getClassificationById(int id) {
        Classification classification = new Classification();
        String sql = "SELECT * FROM classification WHERE classification_id = ?";
        try {
            SqlRowSet rowSet = template.queryForRowSet(sql, id);
            if (rowSet.next()) {
                classification = mapRowToClassification(rowSet);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database.", e);
        }
        return classification;
    }

    @Override
    public List<Classification> getClassifications() {
        List<Classification> classifications = new ArrayList<>();
        String sql = "SELECT * FROM classification";
        try {
            SqlRowSet results = template.queryForRowSet(sql);
            while (results.next()) {
                classifications.add(mapRowToClassification(results));
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database.", e);
        }
        return classifications;
    }

    @Override
    public Classification createClassification(Classification classification) {
        Classification newClassification = null;
        String sql = "INSERT INTO classification (classification_id, name) VALUES (?, ?) RETURNING classification_id";
        try {
            int classificationId = template.queryForObject(sql, int.class, classification.getClassificationId(), classification.getClassificationName());
            newClassification = getClassificationById(classificationId);
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        } catch (NullPointerException e) {
            throw new DaoException("Null pointer exception", e);
        }
        return newClassification;
    }

    @Override
    public Classification updateClassification(Classification classification) {
        Classification updatedClassification = null;
        String sql = "UPDATE classification SET classification_id = ?, name = ? WHERE classification_id = ?";
        try {
            int rowsAffected = template.update(sql, classification.getClassificationId(), classification.getClassificationName(), classification.getClassificationId());
            if (rowsAffected == 0) {
                throw new DaoException("Zero rows affected, expected at least one.");
            }
            updatedClassification = getClassificationById(classification.getClassificationId());
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        } catch (NullPointerException e) {
            throw new DaoException("Null pointer exception", e);
        }
        return updatedClassification;
    }

    @Override
    public int deleteClassificationById(int id) {
        int numberOfRows = 0;
        String updateClassificationSql = "UPDATE item_classification SET classification_id = 0 WHERE classification_id = ?";
        String deleteClassificationSql = "DELETE FROM classification WHERE classification_id = ?";

        try {
            template.update(updateClassificationSql, id);
            numberOfRows = template.update(deleteClassificationSql, id);
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }
        return numberOfRows;
    }

    private Classification mapRowToClassification(SqlRowSet results) {
        Classification classification = new Classification();
        classification.setClassificationId(results.getInt("classification_id"));
        classification.setClassificationName(results.getString("name"));
        return classification;
    }
}
