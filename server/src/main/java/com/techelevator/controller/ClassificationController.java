package com.techelevator.controller;

import com.techelevator.dao.UserDao;
import com.techelevator.dao.ClassificationDao;
import com.techelevator.exception.Achievement;
import com.techelevator.exception.Classification;
import com.techelevator.exception.DaoException;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@CrossOrigin
@RestController
// tells spring this is a rest controller
@RequestMapping(path = "/classifications")
// gives a path for this in the API
@PreAuthorize("isAuthenticated()")
// ensures user is authenticated
public class ClassificationController {

    private final ClassificationDao classificationDao;
    private final UserDao userDao;

    public ClassificationController(ClassificationDao ClassificationDao, UserDao userDao) {
        this.classificationDao = ClassificationDao;
        this.userDao = userDao;
    }

    // Classifications --> get all Classifications
    @GetMapping
    public List<Classification> getAllClassifications() {
        return classificationDao.getClassifications();
    }

    // Classifications --> create Classification ADD THIS TO GOOGLE DOC
    @PreAuthorize("hasRole('ADMIN')")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public Classification createClassification(@RequestBody @Valid Classification Classification) {
        return classificationDao.createClassification(Classification);
    }

    // Classifications --> updated Classification ADD THIS TO GOOGLE DOC
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Classification updatedClassification(@RequestBody @Valid Classification Classification, @PathVariable int id) {
        try {
            Classification updatedClassification = classificationDao.getClassificationById(id);
            return classificationDao.updateClassification(Classification);
        } catch (DaoException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Classification Not Found");
        }
    }

    // Classifications --> delete Classification ADD THIS TO GOOGLE DOC
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public int deleteClassification(@PathVariable int id) {
        try {
            Classification updatedClassification = classificationDao.getClassificationById(id);
            return classificationDao.deleteClassificationById(id);
        } catch (DaoException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Classification Not Found");
        }
    }

}
