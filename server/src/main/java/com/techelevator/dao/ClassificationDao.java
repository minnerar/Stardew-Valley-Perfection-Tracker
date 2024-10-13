package com.techelevator.dao;

import com.techelevator.exception.Classification;

import java.util.List;

public interface ClassificationDao {

    Classification getClassificationById(int id);
    // get a classification by it's id

    List<Classification> getClassifications();
    // get a list of all the classifications

    Classification createClassification(Classification classification);
    // create a new classification type

    Classification updateClassification(Classification classification);
    // update a classification

    int deleteClassificationById(int id);
    // delete a classification by its id



}
