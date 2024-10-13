package com.techelevator.controller;

import com.techelevator.dao.ClassificationDao;
import com.techelevator.dao.UserDao;
import com.techelevator.dao.ItemDao;
import com.techelevator.exception.Classification;
import com.techelevator.exception.DaoException;
import com.techelevator.exception.Item;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin
@RestController
// tells spring this is a rest controller
@RequestMapping(path = "/items")
// gives a path for this in the API
@PreAuthorize("isAuthenticated()")
// ensures user is authenticated
public class ItemController {

    private final ItemDao itemDao;
    private final UserDao userDao;
    private final ClassificationDao classificationDao;

    public ItemController(ItemDao ItemDao, UserDao userDao, ClassificationDao classificationDao) {
        this.itemDao = ItemDao;
        this.userDao = userDao;
        this.classificationDao = classificationDao;
    }

    // Items --> get all Items
    @GetMapping(path = "")
    @ResponseStatus(HttpStatus.OK)
    public List<Item> getAllItems(@RequestParam(defaultValue = "") String season, @RequestParam(defaultValue = "") String classification) {
//        if (season.equals("") && classification.equals("")) {
//            return itemDao.getItems();
//        }
        if (!season.equals("")) {
            return itemDao.getItemsBySeason(season);
        }
        if (!classification.equals("")) {
            return itemDao.getItemsByClassificationNameInItem(classification);
        }
        return itemDao.getItems();
    }

    // Items --> get Item by id
    @GetMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Item getItemById(@PathVariable int id) {
        return itemDao.getItemById(id);
    }

    // Items --> get items by season OR classification
//    @GetMapping(path = "/{seasonOrClassification}")
//    @ResponseStatus(HttpStatus.OK)
//    public List<Item> getItemsBySeasonOrClassification(@PathVariable String seasonOrClassification) {
//        // to get classification id, then items by classification
//        List<Classification> classificationList = classificationDao.getClassifications();
//        int id = 0;
//        for (Classification classification : classificationList) {
//            if (classification.getClassificationName().equalsIgnoreCase(seasonOrClassification)) {
//                id = classification.getClassificationId();
//                return itemDao.getItemsByClassificationId(id);
//            }
//        }
//        // to get items by season
//        return itemDao.getItemsBySeason(seasonOrClassification);

//        List<Item> itemsList = itemDao.getItems();
//        List<Item> itemListToReturn = new ArrayList<>();
//        for (Item item : itemsList) {
//            if (item.getItemSeason().equalsIgnoreCase(seasonOrClassification)) {
//                itemListToReturn.add(item);
//            }
//        }
//
//        return itemListToReturn;}


//    // Items --> get items by season
//    @GetMapping(path = "/{season}")
//    @ResponseStatus(HttpStatus.OK)
//    public List<Item> getItemsBySeason(@PathVariable String season) {
//        return itemDao.getItemsBySeason(season);
//    }
//
//    // Items --> get items by classification
//    @GetMapping(path = "/{classification}")
//    @ResponseStatus(HttpStatus.OK)
//    public List<Item> getItemsByClassification(@PathVariable String classificationName) {
//        List<Classification> classificationList = classificationDao.getClassifications();
//        int id = 0;
//        for (Classification classification : classificationList) {
//            if (classification.getClassificationName().equalsIgnoreCase(classificationName)) {
//                id = classification.getClassificationId();
//            }
//        }
//        return itemDao.getItemsByClassificationId(id);
//    }

    // Items --> create an Item
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Item createItem(@RequestBody @Valid Item item) {
        return itemDao.createItem(item);
    }

    // Items --> updated an Item
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping(path = "/{id}")
    public Item updatedItem(@RequestBody @Valid Item item, @PathVariable int id) {
        try {
            Item updatedItem = itemDao.getItemById(id);
            return itemDao.updateItem(item);
        } catch (DaoException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Item Not Found");
        }
    }

    // Items --> delete an Item
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public int deleteItem(@PathVariable int id) {
        try {
            Item updatedItem = itemDao.getItemById(id);
            return itemDao.deleteItem(id);
        } catch (DaoException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Item Not Found");
        }
    }

}
