package org.minnerar;

//import org.apache.commons.dbcp2.BasicDataSource;
//idk what that is, but it's red -- ASK SOMEONE
import org.minnerar.dao.AchievementDao;
import org.minnerar.dao.JdbcAchievementDao;
import org.minnerar.dao.JdbcItemDao;
import org.minnerar.dao.JdbcVillagerDao;
import org.minnerar.exception.DaoException;
import org.minnerar.model.Achievement;
import org.minnerar.model.Villager;
import org.minnerar.view.Menu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class StardewApplication {

    private static final String MAIN_MENU_OPTION_ACHIEVEMENTS = "List All Achievements";
    private static final String MAIN_MENU_OPTION_CHECK_PROGRESS = "Check All Achievement Progress";
    private static final String MAIN_MENU_OPTION_ADD_ACHIEVEMENT = "Add Achievement to Track";
    private static final String MAIN_MENU_OPTION_VILLAGER = "Villager Menu";
    private static final String MAIN_MENU_OPTION_ITEM = "Item Menu";
    private static final String MAIN_MENU_OPTION_CLASSIFICATION = "Classification Menu";
    private static final String MAIN_MENU_OPTION_EXIT = "Exit";
    private static final String[] MAIN_MENU_OPTIONS = new String[]{
            MAIN_MENU_OPTION_ACHIEVEMENTS,
            MAIN_MENU_OPTION_CHECK_PROGRESS,
            MAIN_MENU_OPTION_ADD_ACHIEVEMENT,
            MAIN_MENU_OPTION_VILLAGER,
            MAIN_MENU_OPTION_ITEM,
            MAIN_MENU_OPTION_CLASSIFICATION,
            MAIN_MENU_OPTION_EXIT
    };

    private static final String ACHIEVEMENT_MENU_OPTION_DESCRIPTION = "Description";
    private static final String ACHIEVEMENT_MENU_OPTION_UPDATE_PROGRESS = "Update Progress";
    private static final String ACHIEVEMENT_MENU_OPTION_DELETE_ACHIEVEMENT = "Delete Achievement from Tracking";
    private static final String ACHIEVEMENT_MENU_OPTION_RETURN_TO_MAIN = "Return to Main Menu";
    private static final String[] ACHIEVEMENT_MENU_OPTIONS = new String[]{
            ACHIEVEMENT_MENU_OPTION_DESCRIPTION,
            ACHIEVEMENT_MENU_OPTION_UPDATE_PROGRESS,
            ACHIEVEMENT_MENU_OPTION_DELETE_ACHIEVEMENT,
            ACHIEVEMENT_MENU_OPTION_RETURN_TO_MAIN
    };

    private static final String ACHIEVEMENT_LIST_MENU_OPTION_PRODUCE_AND_FORAGE = "Produce & Foraged Shipped";
    private static final String ACHIEVEMENT_LIST_MENU_OPTION_OBELISKS = "Obelisks on Farm";
    private static final String ACHIEVEMENT_LIST_MENU_OPTION_GOLDEN_CLOCK = "Golden Clock on Farm";
    private static final String ACHIEVEMENT_LIST_MENU_OPTION_MONSTER_SLAYER = "Monster Slayer Hero";
    private static final String ACHIEVEMENT_LIST_MENU_OPTION_GREAT_FRIENDS = "Great Friends";
    private static final String ACHIEVEMENT_LIST_MENU_OPTION_FARMER_LEVEL = "Farmer Level";
    private static final String ACHIEVEMENT_LIST_MENU_OPTION_STARDROP = "Found ALl Stardrops";
    private static final String ACHIEVEMENT_LIST_MENU_OPTION_COOKING = "All Cooking Recipes Made";
    private static final String ACHIEVEMENT_LIST_MENU_OPTION_CRAFTING = "All Crafting Recipes Made";
    private static final String ACHIEVEMENT_LIST_MENU_OPTION_FISH = "All Fish Caught";
    private static final String ACHIEVEMENT_LIST_MENU_OPTION_GOLDEN_WALNUTS = "All Golden Walnuts Found";
    private static final String[] ACHIEVEMENT_LIST_MENU_OPTIONS = new String[]{
            ACHIEVEMENT_LIST_MENU_OPTION_PRODUCE_AND_FORAGE,
            ACHIEVEMENT_LIST_MENU_OPTION_OBELISKS,
            ACHIEVEMENT_LIST_MENU_OPTION_GOLDEN_CLOCK,
            ACHIEVEMENT_LIST_MENU_OPTION_MONSTER_SLAYER,
            ACHIEVEMENT_LIST_MENU_OPTION_GREAT_FRIENDS,
            ACHIEVEMENT_LIST_MENU_OPTION_FARMER_LEVEL,
            ACHIEVEMENT_LIST_MENU_OPTION_STARDROP,
            ACHIEVEMENT_LIST_MENU_OPTION_COOKING,
            ACHIEVEMENT_LIST_MENU_OPTION_CRAFTING,
            ACHIEVEMENT_LIST_MENU_OPTION_FISH,
            ACHIEVEMENT_LIST_MENU_OPTION_GOLDEN_WALNUTS,
            ACHIEVEMENT_MENU_OPTION_RETURN_TO_MAIN
    };

//    private static final String ACHIEVEMENT_REQUIREMENTS_MENU_FRUIT = "Fruit";
//    private static final String ACHIEVEMENT_REQUIREMENTS_MENU_VEGETABLE = "Vegetable";
//    private static final String ACHIEVEMENT_REQUIREMENTS_MENU_COOKING = "Cooking";
//    private static final String ACHIEVEMENT_REQUIREMENTS_MENU_CRAFTING = "Crafting";
//    private static final String ACHIEVEMENT_REQUIREMENTS_MENU_FISH = "Fish";
//    private static final String ACHIEVEMENT_REQUIREMENTS_MENU_ENEMY = "Enemy";
//    private static final String ACHIEVEMENT_REQUIREMENTS_MENU_ANIMAL = "Animal Product";
//    private static final String ACHIEVEMENT_REQUIREMENTS_MENU_ARTISAN = "Artisan Product";
//    private static final String ACHIEVEMENT_REQUIREMENTS_MENU_VILLAGER = "Villager";
//    private static final String ACHIEVEMENT_REQUIREMENTS_MENU_MINERAL = "Mineral";
//    private static final String ACHIEVEMENT_REQUIREMENTS_MENU_ARTIFACT = "Artifact";
//    private static final String[] ACHIEVEMENT_REQUIREMENTS_MENU = new String[]{
//            ACHIEVEMENT_REQUIREMENTS_MENU_FRUIT,
//            ACHIEVEMENT_REQUIREMENTS_MENU_VEGETABLE,
//            ACHIEVEMENT_REQUIREMENTS_MENU_COOKING,
//            ACHIEVEMENT_REQUIREMENTS_MENU_CRAFTING,
//            ACHIEVEMENT_REQUIREMENTS_MENU_FISH,
//            ACHIEVEMENT_REQUIREMENTS_MENU_ENEMY,
//            ACHIEVEMENT_REQUIREMENTS_MENU_ANIMAL,
//            ACHIEVEMENT_REQUIREMENTS_MENU_ARTISAN,
//            ACHIEVEMENT_REQUIREMENTS_MENU_VILLAGER,
//            ACHIEVEMENT_REQUIREMENTS_MENU_MINERAL,
//            ACHIEVEMENT_REQUIREMENTS_MENU_ARTIFACT,
//            ACHIEVEMENT_MENU_OPTION_RETURN_TO_MAIN
//    };

    private static final String VILLAGER_DISPLAY_ALL = "Show All Villagers";
    private static final String VILLAGER_DISPLAY_MARRIAGE_CANDIDATES = "Show ALl Marriage Candidates";
    private static final String VILLAGER_SHOW_CURRENT_HEARTS = "Show Current Hearts with Villager";
    private static final String VILLAGER_SHOW_LOVED_GIFTS = "Show Loved Gifts of Villager";
    private static final String VILLAGER_ADD_VILLAGER = "Add a Villager";
    private static final String VILLAGER_UPDATE_VILLAGER = "Update a Villager";
    private static final String VILLAGER_DELETE_VILLAGER = "Delete a Villager";
    private static final String[] VILLAGER_DISPLAY_MENU = new String[] {
            VILLAGER_DISPLAY_ALL,
            VILLAGER_DISPLAY_MARRIAGE_CANDIDATES,
            VILLAGER_SHOW_CURRENT_HEARTS,
            VILLAGER_SHOW_LOVED_GIFTS,
            VILLAGER_ADD_VILLAGER,
            VILLAGER_UPDATE_VILLAGER,
            VILLAGER_DELETE_VILLAGER,
            ACHIEVEMENT_MENU_OPTION_RETURN_TO_MAIN
    } ;

    private static final String ITEM_DISPLAY_MENU_SHOW_ALL = "Show All Items";
    private static final String ITEM_DISPLAY_MENU_SHOW_MISSING = "Show Missing Items";
    private static final String ITEM_DISPLAY_MENU_COMPLETED = "Show Found Items";
    private static final String ITEM_DISPLAY_BY_CLASSIFICATION = "Show All Items by Classification";
    private static final String ITEM_DISPLAY_ADD_ITEM = "Add an Item";
    private static final String ITEM_DISPLAY_UPDATED_ITEM = "Update an Item";
    private static final String ITEM_DISPLAY_DELETE_ITEM = "Delete an Item";
    private static final String[] ITEM_DISPLAY_MENU = new String[]{
            ITEM_DISPLAY_MENU_SHOW_ALL,
            ITEM_DISPLAY_MENU_SHOW_MISSING,
            ITEM_DISPLAY_MENU_COMPLETED,
            ITEM_DISPLAY_BY_CLASSIFICATION,
            ITEM_DISPLAY_ADD_ITEM,
            ITEM_DISPLAY_UPDATED_ITEM,
            ITEM_DISPLAY_DELETE_ITEM,
            ACHIEVEMENT_MENU_OPTION_RETURN_TO_MAIN
    };

    private static final String CLASSIFICATION_DISPLAY_MENU_SHOW_ALL = "Show All Classifications";
    private static final String CLASSIFICATION_DISPLAY_MENU_ADD = "Add a Classification";
    private static final String CLASSIFICATION_DISPLAY_MENU_UPDATE = "Update a Classification";
    private static final String CLASSIFICATION_DISPLAY_MENU_DELETE = "Delete a Classification";
    private static final String[] CLASSIFICATION_DISPLAY_MENU = new String[] {
            CLASSIFICATION_DISPLAY_MENU_SHOW_ALL,
            CLASSIFICATION_DISPLAY_MENU_ADD,
            CLASSIFICATION_DISPLAY_MENU_UPDATE,
            CLASSIFICATION_DISPLAY_MENU_DELETE,
            ACHIEVEMENT_MENU_OPTION_RETURN_TO_MAIN
    };
//    private final DataSource dataSource;
//    private final JdbcTemplate template;

    private final JdbcAchievementDao achievementDao;
    private final JdbcItemDao itemDao;
    private final JdbcVillagerDao villagerDao;

    @Autowired
    public StardewApplication(JdbcAchievementDao achievementDao, JdbcItemDao itemDao, JdbcVillagerDao villagerDao) {
//        this.dataSource = dataSource;
        this.achievementDao = achievementDao;
        this.itemDao = itemDao;
        this.villagerDao = villagerDao;
    }

    public static void main(String[] args) {
        SpringApplication.run(StardewApplication.class, args);
    }

    private void initialize(DataSource dataSource) {
        // anything needed for setup of the app!
        // what do i even need to add here?
    }

    private void run() {
        System.out.println("Welcome to the Stardew Valley Perfection Tracker!");
        System.out.println("Please choose an option.");
        System.out.println("        ");

        Menu menu = new Menu(System.in, System.out);

        boolean running = true;
        while (running) {
            String choice = (String) Menu.getChoiceFromOptions(MAIN_MENU_OPTIONS);
            if (choice.equals(MAIN_MENU_OPTION_ACHIEVEMENTS)) {
                handleListAllAchievements(); // lists all the achievement names
            } else if (choice.equals(MAIN_MENU_OPTION_CHECK_PROGRESS)) {
                handleCheckProgress(); // lists all the achievements and the current progress for each one
            } else if (choice.equals(MAIN_MENU_OPTION_ADD_ACHIEVEMENT)) {
                handleAddAchievement(); // allows the user to add an achievement
            } else if (choice.equals(MAIN_MENU_OPTION_VILLAGER)) {
                handleVillager(); // handle the villager option in another menu
            } else if (choice.equals(MAIN_MENU_OPTION_ITEM)) {
                handleItem(); // handle the item option in another menu
            } else if (choice.equals(MAIN_MENU_OPTION_CLASSIFICATION)) {
                handleClassification(); // handle the classification option in another menu
            } else if (choice.equals(MAIN_MENU_OPTION_EXIT)) {
                running = false;
            }
        }
    }

    private void handleAchievements() {
        try {
            System.out.println("Achievements Menu Options");
            String choice = (String)Menu.getChoiceFromOptions(ACHIEVEMENT_MENU_OPTIONS);
            if(choice.equals(ACHIEVEMENT_MENU_OPTION_DESCRIPTION)) {
                handleAchievementDescription();
            } else if(choice.equals(ACHIEVEMENT_MENU_OPTION_UPDATE_PROGRESS)) {
                handleUpdateAchievementProgress(); // updates the progress for an achievement
            } else if(choice.equals(ACHIEVEMENT_MENU_OPTION_DELETE_ACHIEVEMENT)) {
                handleDeleteAchievement(); // deletes an achievement
            } else if(choice.equals(ACHIEVEMENT_MENU_OPTION_RETURN_TO_MAIN)) {
                // TODO: HOW DO I DO THIS?
            }
        } catch (DaoException e) {
            System.out.println("Error occurred: " + e.getMessage());
        }
    }

    // ALL ACHIEVEMENT METHODS
    private void handleListAllAchievements() {
        System.out.println("All Achievements");
        System.out.println("--------------------------");
        List<Achievement> allAchievements = achievementDao.getAchievements();
        listAchievements(allAchievements);
    }

    private void listAchievements(List<Achievement> achievements) {
        if(achievements.size() > 0) {
            for(Achievement achievement : achievements) {
                System.out.println(achievement.getAchievementName());
            }
        } else {
            System.out.println("\n*** No results ***");
        }
    }

    private void handleAchievementDescription() {
        System.out.println("All Achievement Descriptions");
        System.out.println("--------------------------");
        List<Achievement> achievements = achievementDao.getAchievements();
        if(achievements.size() > 0) {
            for(Achievement achievement : achievements) {
                System.out.println(achievement.getAchievementName() + ": " + achievement.getAchievementDescription());
            }
        } else {
            System.out.println("\n*** No results ***");
        }
    }

    private void handleCheckProgress() {
        System.out.println("Achievement Progress");
        System.out.println("--------------------------");
        List<Achievement> allAchievements = achievementDao.getAchievements();
        if (allAchievements.size() > 0) {
            for (Achievement achievement : allAchievements) {
                System.out.println(achievement + " Progress: " + achievement.getAchievementCurrent());
            }
        }
    }

    private void handleAddAchievement() {
        System.out.println("Add a New Achievement to Track");
        System.out.println("--------------------------");

        Achievement newAchievement = new Achievement();
        String achievementName = getUserInput("Enter the name of the new Achievement.");
        String achievementTotalNeeded = getUserInput("Enter the total needed to complete the Achievement.");
        String achievementCurrentProgress = getUserInput("Enter the current Achievement progress.");
        String achievementDescription = getUserInput("Enter the Achievement's Description.");

        newAchievement.setAchievementName(achievementName);;
        newAchievement.setAchievementTotalNeeded(Integer.parseInt(achievementTotalNeeded));
        newAchievement.setAchievementCurrent(Integer.parseInt(achievementCurrentProgress));
        newAchievement.setAchievementDescription(achievementDescription);
        System.out.println("\n" + newAchievement.getAchievementName() + " added to the list of Achievements!");
    }

    private Achievement getAchievementSelectionFromUser() {
        System.out.println("Choose an Achievement:");
        List<Achievement> allAchievements = achievementDao.getAchievements();
        return (Achievement)Menu.getChoiceFromOptions(allAchievements.toArray());
    }

    private void handleUpdateAchievementProgress() {
        Achievement selectedAchievement = getAchievementSelectionFromUser();
        String newAchievementName = getUserInput("Please enter the updated name of the Achievement. " +
                "\nLeave blank to skip.");
        String updatedProgress = getUserInput("Please enter the updated progress for this Achievement." +
                "\nLeave blank to skip.");
        if (!newAchievementName.equals("")) {
            selectedAchievement.setAchievementName(newAchievementName);
        }
        if (!updatedProgress.equals("")) {
            selectedAchievement.setAchievementCurrent(Integer.parseInt(updatedProgress));
        }
        selectedAchievement = achievementDao.updateAchievement(selectedAchievement);
        System.out.println("\n" + selectedAchievement.getAchievementName() + " has been updated!");
    }

    private void handleDeleteAchievement() {
        System.out.println("Delete an Achievement");
        Achievement achievementToDelete = getAchievementSelectionFromUser();

        achievementDao.deleteAchievementById(achievementToDelete.getAchievementId());
        System.out.println("\n" + achievementToDelete.getAchievementName() + " has been deleted!");
    }

    // ALL VILLAGER METHODS
    private void handleVillager() {
        try {
            System.out.println("Villager Menu Options");
            String choice = (String)Menu.getChoiceFromOptions(VILLAGER_DISPLAY_MENU);
            if(choice.equals(VILLAGER_DISPLAY_ALL)) {
                handleListAllVillagers(); // list out all the villagers
            } else if(choice.equals(VILLAGER_DISPLAY_MARRIAGE_CANDIDATES)) {
                listAllMarriageableVillagers(); // list out all marriage candidates
            } else if(choice.equals(VILLAGER_SHOW_CURRENT_HEARTS)) {
                listAllVillagerHearts(); // list the current heart amount with each villager
            } else if(choice.equals(VILLAGER_SHOW_LOVED_GIFTS)) {
                listAllVillagerLovedGifts(); // list all the villagers and their loved gifts
            } else if(choice.equals(VILLAGER_ADD_VILLAGER)) {
                handleAddVillager(); // add a villager
            } else if(choice.equals(VILLAGER_UPDATE_VILLAGER)) {
                handleUpdateVillager();
            } else if(choice.equals(VILLAGER_DELETE_VILLAGER)) {
                handleDeleteVillager();
            } else if(choice.equals(ACHIEVEMENT_MENU_OPTION_RETURN_TO_MAIN)) {
                // TODO: HOW DO I DO THIS?
            }
        } catch (DaoException e) {
            System.out.println("Error occurred: " + e.getMessage());
        }
    }

    private void handleListAllVillagers() {
        System.out.println("All Villagers");
        System.out.println("--------------------------");
        List<Villager> allVillagers = villagerDao.getVillagers();
        listAllVillagers(allVillagers);
    }

    private void listAllVillagers(List<Villager> villagers) {
        if(villagers.size() > 0) {
            for(Villager villager : villagers) {
                System.out.println(villager);
            }
        } else {
            System.out.println("\n*** No results ***");
        }
    }

    private void listAllMarriageableVillagers() {
        List<Villager> villagers = villagerDao.getVillagers();
        if(villagers.size() > 0) {
            for(Villager villager : villagers) {
                if (villager.isVillagerMarriageCandidate()) {
                    System.out.println(villager);
                }
            }
        } else {
            System.out.println("\n*** No results ***");
        }
    }

    private void listAllVillagerHearts() {
        List<Villager> villagers = villagerDao.getVillagers();
        if(villagers.size() > 0) {
            for(Villager villager : villagers) {
                System.out.println(villager.getVillagerName() + " current heart progress is: " + villager.getVillagerHeartCounter());
            }
        } else {
            System.out.println("\n*** No results ***");
        }
    }

    private void listAllVillagerLovedGifts() {
        List<Villager> villagers = villagerDao.getVillagers();
        if(villagers.size() > 0) {
            for(Villager villager : villagers) {
                System.out.println(villager.getVillagerName() + "'s loved gifts are: " + villager.getVillagerLovedGifts());
            }
        } else {
            System.out.println("\n*** No results ***");
        }
    }

    private Villager getVillagerSelectionFromUser() {
        System.out.println("Choose a Villager:");
        List<Villager> allVillagers = villagerDao.getVillagers();
        return (Villager) Menu.getChoiceFromOptions(allVillagers.toArray());
    }

    private void handleAddVillager() {
        System.out.println("Add a New Villager the list!");
        System.out.println("--------------------------");

        Villager newVillager = new Villager();
        String villagerName = getUserInput("Enter the name of the new Villager.");
        String marriageCandidate = getUserInput("Is the villager a marriage candidate? YES or NO");
        String birthday = getUserInput("Enter the new Villager's birthday.");
        String lovedGiftString = getUserInput("Enter up to 12 things the villager loves (minimum 2), separated by a comma.");
        String description = getUserInput("Enter a description for the villager.");

        newVillager.setVillagerName(villagerName);
        boolean status = false;
        if (marriageCandidate.equalsIgnoreCase("YES")) {
            status = true;
        }
        newVillager.setVillagerMarriageCandidate(status);
        newVillager.setVillagerBirthday(birthday);
        List<String> lovedGifts = new ArrayList<>(List.of(lovedGiftString.split(",")));
        newVillager.setVillagerLovedGifts(lovedGifts);
        newVillager.setVillagerDescription(description);
        System.out.println("\n" + newVillager.getVillagerName() + " added to the list of Villagers!");
    }

    private void handleUpdateVillager() {
        Villager selectedVillager = getVillagerSelectionFromUser();
        String newVillagerName = getUserInput("Please enter the updated name of the Villager. " +
                "\nLeave blank to skip.");
        String updatedMarriageCandidateStatus = getUserInput("Please enter the updated marriage candidate status for this villager." +
                "\nPlease enter yes or no, or leave blank to skip.");
        String updatedBirthday = getUserInput("Please enter the updated birthday for this Villager." +
                "\nLeave blank to skip.");
        String updatedLovedGifts = getUserInput("Please enter up to 12 loved gifts of the villager, separated by a comma." +
                "\nOr leave blank to skip.");
        String description = getUserInput("Please enter the updated description of the villager.");
        if (!newVillagerName.equals("")) {
            selectedVillager.setVillagerName(newVillagerName);
        }
        if (!updatedBirthday.equals("")) {
            selectedVillager.setVillagerBirthday(updatedBirthday);
        }
        if (!updatedMarriageCandidateStatus.equals("")) {
            if (updatedMarriageCandidateStatus.equalsIgnoreCase("yes")) {
                selectedVillager.setVillagerMarriageCandidate(true);
            } else if (updatedMarriageCandidateStatus.equalsIgnoreCase("no")) {
                selectedVillager.setVillagerMarriageCandidate(false);
            }
        }
        List<String> lovedGifts = new ArrayList<>(List.of(updatedLovedGifts.split(",")));
        selectedVillager.setVillagerLovedGifts(lovedGifts);
        if (!description.equals("")) {
            selectedVillager.setVillagerDescription(description);
        }
        selectedVillager = villagerDao.updateVillager(selectedVillager);
        System.out.println("\n" + selectedVillager.getVillagerName() + " has been updated!");
    }

    private void handleDeleteVillager() {
        System.out.println("Delete a Villager");
        Villager villagerToDelete = getVillagerSelectionFromUser();

        villagerDao.deleteVillager(villagerToDelete.getVillagerId());
        System.out.println("\n" + villagerToDelete.getVillagerName() + " has been deleted!");
    }

    // ALL ITEM METHODS
    private void handleItem() {
        try {
            System.out.println("Villager Menu Options");
            String choice = (String)Menu.getChoiceFromOptions(ITEM_DISPLAY_MENU);
            if(choice.equals(ITEM_DISPLAY_MENU_SHOW_ALL)) {

            } else if(choice.equals(ITEM_DISPLAY_MENU_SHOW_MISSING)) {

            } else if(choice.equals(ITEM_DISPLAY_MENU_COMPLETED)) {

            } else if(choice.equals(ITEM_DISPLAY_BY_CLASSIFICATION)) {

            } else if(choice.equals(ITEM_DISPLAY_ADD_ITEM)) {

            } else if(choice.equals(ITEM_DISPLAY_UPDATED_ITEM)) {

            } else if(choice.equals(ITEM_DISPLAY_DELETE_ITEM)) {

            } else if(choice.equals(ACHIEVEMENT_MENU_OPTION_RETURN_TO_MAIN)) {
                // TODO: HOW DO I DO THIS?
            }
        } catch (DaoException e) {
            System.out.println("Error occurred: " + e.getMessage());
        }
    }

    private void handleDisplayAllItems() {

    }

    private void handleDisplayMissingItems() {

    }

    private void handleDisplayCompletedItems() {

    }

    private void handleDisplayItemsByClassification() {

    }

    private void handleAddItem() {

    }

    private void handleUpdateItem() {

    }

    private void handleDeleteItem() {

    }

    // ALL CLASSIFICATION METHODS
    private void handleClassification() {
        try {
            System.out.println("Classification Menu Options");
            String choice = (String)Menu.getChoiceFromOptions(CLASSIFICATION_DISPLAY_MENU);
            if(choice.equals(CLASSIFICATION_DISPLAY_MENU_SHOW_ALL)) {

            } else if(choice.equals(CLASSIFICATION_DISPLAY_MENU_ADD)) {

            } else if(choice.equals(CLASSIFICATION_DISPLAY_MENU_UPDATE)) {

            } else if(choice.equals(CLASSIFICATION_DISPLAY_MENU_DELETE)) {

            } else if(choice.equals(ACHIEVEMENT_MENU_OPTION_RETURN_TO_MAIN)) {
                // TODO: HOW DO I DO THIS?
            }
        } catch (DaoException e) {
            System.out.println("Error occurred: " + e.getMessage());
        }
    }

    private void handleDisplayAllClassifications() {

    }


    private void handleListAllVillagers() {
        System.out.println("All Villagers");
        System.out.println("--------------------------");
        List<Villager> allVillagers = villagerDao.getVillagers();
        listAllVillagers(allVillagers);
    }

    private void listAllVillagers(List<Villager> villagers) {
        if(villagers.size() > 0) {
            for(Villager villager : villagers) {
                System.out.println(villager);
            }
        } else {
            System.out.println("\n*** No results ***");
        }
    }


    private void handleAddClassification() {

    }

    private void handleUpdateClassification() {

    }

    private void handleDeleteClassification() {

    }


    // HANDLE USER INPUT
    private String getUserInput(String prompt) {
        System.out.print(prompt + " >>> ");
        return new Scanner(System.in).nextLine();
    }
}