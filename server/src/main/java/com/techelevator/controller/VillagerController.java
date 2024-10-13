package com.techelevator.controller;

import com.techelevator.dao.UserDao;
import com.techelevator.dao.VillagerDao;
import com.techelevator.exception.Achievement;
import com.techelevator.exception.DaoException;
import com.techelevator.exception.Villager;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@CrossOrigin
@RestController
// tells spring this is a rest controller
@RequestMapping(path = "/villagers")
// gives a path for this in the API
@PreAuthorize("isAuthenticated()")
// ensures user is authenticated
public class VillagerController {

    private final VillagerDao villagerDao;
    private final UserDao userDao;

    public VillagerController(VillagerDao villagerDao, UserDao userDao) {
        this.villagerDao = villagerDao;
        this.userDao = userDao;
    }

    // villagers --> get all villagers
    @GetMapping
    public List<Villager> getAllVillagers() {
        return villagerDao.getVillagers();
    }

    // villagers --> get villager by id
    @GetMapping(path = "/{id}")
    public Villager getVillagerById(@PathVariable int id) {
        return villagerDao.getVillagerById(id);
    }

    // villagers --> update a villager
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping(path = "/{id}")
    public Villager updatedVillager(@RequestBody @Valid Villager villager, @PathVariable int id) {
        try {
            Villager updatedVillager = villagerDao.updateVillager(villager);
            return villagerDao.updateVillager(villager);
        } catch (DaoException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Villager Not Found");
        }
    }

}
