package com.techelevator.controller;

import com.techelevator.dao.AchievementDao;
import com.techelevator.dao.UserDao;
import com.techelevator.exception.Achievement;
import com.techelevator.exception.DaoException;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@CrossOrigin
@RestController
// tells spring this is a rest controller
@RequestMapping(path = "/achievements")
// gives a path for this in the API
@PreAuthorize("isAuthenticated()")
// ensures user is authenticated
public class AchievementController {

    private final AchievementDao achievementDao;
    private final UserDao userDao;

    public AchievementController(AchievementDao achievementDao, UserDao userDao) {
        this.achievementDao = achievementDao;
        this.userDao = userDao;
    }

    // Achievements --> get all Achievements
    @GetMapping
    public List<Achievement> getAllAchievements() {
        return achievementDao.getAchievements();
    }

    // Achievements --> get Achievement by id
    @GetMapping(path = "/{id}")
    public Achievement getAchievementById(@PathVariable int id) {
        return achievementDao.getAchievementById(id);
    }

    // Achievements --> create Achievement ADD THIS TO GOOGLE DOC
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Achievement createAchievement(@RequestBody @Valid Achievement achievement) {
        return achievementDao.createAchievement(achievement);
    }

    // Achievements --> updated Achievement ADD THIS TO GOOGLE DOC
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Achievement updatedAchievement(@RequestBody @Valid Achievement achievement, @PathVariable int id) {
        try {
            Achievement updatedAchievement = achievementDao.getAchievementById(id);
            return achievementDao.updateAchievement(achievement);
        } catch (DaoException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Achievement Not Found");
        }
    }

    // Achievements --> delete Achievement ADD THIS TO GOOGLE DOC
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping(path = "/{id}")
    public int deleteAchievement(@PathVariable int id) {
        try {
            Achievement updatedAchievement = achievementDao.getAchievementById(id);
            return achievementDao.deleteAchievementById(id);
        } catch (DaoException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Achievement Not Found");
        }

    }

}
