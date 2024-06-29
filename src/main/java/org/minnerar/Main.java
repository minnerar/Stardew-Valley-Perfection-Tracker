package org.minnerar;

import org.apache.commons.dbcp2.BasicDataSource;
import org.minnerar.view.Menu;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;
import javax.sql.DataSource;

public class Main {

    private static final String MAIN_MENU_OPTION_ACHIEVEMENTS = "Achievements";
    private static final String MAIN_MENU_OPTION_CHECK_PROGRESS = "Check Progress";
    private static final String MAIN_MENU_OPTION_ADD_ACHIEVEMENT = "Add Achievement to Track";
    private static final String MAIN_MENU_OPTION_EXIT = "Exit";
    private static final String[] MAIN_MENU_OPTIONS = new String[] {
            MAIN_MENU_OPTION_ACHIEVEMENTS,
            MAIN_MENU_OPTION_CHECK_PROGRESS,
            MAIN_MENU_OPTION_ADD_ACHIEVEMENT,
            MAIN_MENU_OPTION_EXIT
    };

    private static final String ACHIEVEMENT_MENU_OPTION_DESCRIPTION = "Description";
    private static final String ACHIEVEMENT_MENU_OPTION_REQUIREMENTS = "Requirements";
    private static final String ACHIEVEMENT_MENU_OPTION_UPDATE_PROGRESS = "Update Progress";
    private static final String ACHIEVEMENT_MENU_OPTION_DELETE_ACHIEVEMENT = "Delete Achievement from Tracking";
    private static final String ACHIEVEMENT_MENU_OPTION_RETURN_TO_MAIN = "Return to Main Menu";
    private static final String [] ACHIEVEMENT_MENU_OPTIONS = new String[] {
            ACHIEVEMENT_MENU_OPTION_DESCRIPTION,
            ACHIEVEMENT_MENU_OPTION_REQUIREMENTS,
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
    private static final String[] ACHIEVEMENT_LIST_MENU_OPTIONS = new String[] {
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

    private static final String ACHIEVEMENT_REQUIREMENTS_MENU_FRUIT = "Fruit";
    private static final String ACHIEVEMENT_REQUIREMENTS_MENU_VEGETABLE = "Vegetable";
    private static final String ACHIEVEMENT_REQUIREMENTS_MENU_COOKING = "Cooking";
    private static final String ACHIEVEMENT_REQUIREMENTS_MENU_CRAFTING = "Crafting";
    private static final String ACHIEVEMENT_REQUIREMENTS_MENU_FISH = "Fish";
    private static final String ACHIEVEMENT_REQUIREMENTS_MENU_ENEMY = "Enemy";
    private static final String ACHIEVEMENT_REQUIREMENTS_MENU_ANIMAL = "Animal Product";
    private static final String ACHIEVEMENT_REQUIREMENTS_MENU_ARTISAN = "Artisan Product";
    private static final String ACHIEVEMENT_REQUIREMENTS_MENU_VILLAGER = "Villager";
    private static final String ACHIEVEMENT_REQUIREMENTS_MENU_MINERAL = "Mineral";
    private static final String ACHIEVEMENT_REQUIREMENTS_MENU_ARTIFACT = "Artifact";
    private static final String[] ACHIEVEMENT_REQUIREMENTS_MENU = new String[] {
            ACHIEVEMENT_REQUIREMENTS_MENU_FRUIT,
            ACHIEVEMENT_REQUIREMENTS_MENU_VEGETABLE,
            ACHIEVEMENT_REQUIREMENTS_MENU_COOKING,
            ACHIEVEMENT_REQUIREMENTS_MENU_CRAFTING,
            ACHIEVEMENT_REQUIREMENTS_MENU_FISH,
            ACHIEVEMENT_REQUIREMENTS_MENU_ENEMY,
            ACHIEVEMENT_REQUIREMENTS_MENU_ANIMAL,
            ACHIEVEMENT_REQUIREMENTS_MENU_ARTISAN,
            ACHIEVEMENT_REQUIREMENTS_MENU_VILLAGER,
            ACHIEVEMENT_REQUIREMENTS_MENU_MINERAL,
            ACHIEVEMENT_REQUIREMENTS_MENU_ARTIFACT,
            ACHIEVEMENT_MENU_OPTION_RETURN_TO_MAIN
    };

    private static final String ITEM_DISPLAY_MENU_SHOW_ALL = "Show All Requirements";
    private static final String ITEM_DISPLAY_MENU_SHOW_MISSING = "Show Missing Requirements";
    private static final String ITEM_DISPLAY_MENU_COMPLETED = "Show Completed Requirements";
    private static final String [] ITEM_DISPLAY_MENU = new String[] {
            ITEM_DISPLAY_MENU_SHOW_ALL,
            ITEM_DISPLAY_MENU_SHOW_MISSING,
            ITEM_DISPLAY_MENU_COMPLETED,
            ACHIEVEMENT_MENU_OPTION_RETURN_TO_MAIN
    };


    public static void main(String[] args) {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setUrl("jdbc:postgresql://localhost:5432/EmployeeProjects");
        dataSource.setUsername("postgres");
        dataSource.setPassword("postgres1");

        run();
    }

    private void initialize() {
        // anything needed for setup of the app!
    }

    private void run() {
        System.out.println("Welcome to the Stardew Valley Perfection Tracker!");
        System.out.println("Please choose an option.");
        System.out.println("        ");

        Menu menu = new Menu(System.in, System.out);

        boolean running = true;
        while(running) {
            String choice = (String)menu.getChoiceFromOptions(MAIN_MENU_OPTIONS);
            if(choice.equals(MAIN_MENU_OPTION_ACHIEVEMENTS)) {
                // create a handle achievements method
            } else if(choice.equals(MAIN_MENU_OPTION_CHECK_PROGRESS)) {
                // create a handle check progress method
            } else if(choice.equals(MAIN_MENU_OPTION_ADD_ACHIEVEMENT)) {
                // create a handle add achievement method
            } else if(choice.equals(MAIN_MENU_OPTION_EXIT)) {
                running = false;
            }
        }

    }



}