package org.minnerar.view;

import org.minnerar.model.Classification;
import org.minnerar.model.Item;
import org.minnerar.model.Villager;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.List;
import java.util.Scanner;

public class Menu {

    private static PrintWriter out;
    private static Scanner in;

    public Menu(InputStream input, OutputStream output) {
        this.out = new PrintWriter(output);
        this.in = new Scanner(input);
    }

    public static Object getChoiceFromOptions(Object[] options) {
        Object choice = null;
        while (choice == null) {
            displayMenuOptions(options);
            choice = getChoiceFromUserInput(options);
        }
        return choice;
    }

    private static Object getChoiceFromUserInput(Object[] options) {
        Object choice = null;
        String userInput = in.next();
        try {
            int selectedOption = Integer.valueOf(userInput);
            if (selectedOption <= options.length) {
                choice = options[selectedOption - 1];
            }
        } catch(NumberFormatException e) {
        }
        if(choice == null) {
            out.println("\n*** "+userInput+" is not a valid option ***\n");
        }
        return choice;
    }

    private static void displayMenuOptions(Object[] options) {
        out.println();
        for(int i = 0; i < options.length; i++) {
            int optionNum = i+1;
            out.println(optionNum+") "+options[i]);
        }
        out.print("\nPlease choose an option >>> ");
        out.flush();
    }

    public static Villager getVillagerChoiceFromOptions(List<Villager> villagers) {
        Villager villager = null;
        while (villager == null) {
            displayVillagerMenuOptions(villagers);
            villager = getVillagerChoiceFromUserInput(villagers);
        }
        return villager;
    }

    private static Villager getVillagerChoiceFromUserInput(List<Villager> villagers) {
        Villager villager = null;
        String userInput = in.next();
        try {
            int selectedVillager = Integer.valueOf(userInput);
            if (selectedVillager <= villagers.size()) {
                villager = villagers.get(selectedVillager - 1);
            }
        } catch(NumberFormatException e) {
        }
        if(villager == null) {
            out.println("\n*** "+userInput+" is not a valid option ***\n");
        }
        return villager;
    }

    private static void displayVillagerMenuOptions(List<Villager> villagers) {
        out.println();
        for(int i = 0; i < villagers.size(); i++) {
            int optionNum = i+1;
            out.println(optionNum+") "+ villagers.get(i).getVillagerName());
        }
        out.print("\nPlease choose an option >>> ");
        out.flush();
    }


    public static Item getItemChoiceFromOptions(List<Item> items) {
        Item item = null;
        while (item == null) {
            displayItemsMenuOptions(items);
            item = getItemChoiceFromUserInput(items);
        }
        return item;
    }

    private static Item getItemChoiceFromUserInput(List<Item> items) {
        Item item = null;
        String userInput = in.next();
        try {
            int selectedItem = Integer.valueOf(userInput);
            if (selectedItem <= items.size()) {
                item = items.get(selectedItem - 1);
            }
        } catch(NumberFormatException e) {
        }
        if(item == null) {
            out.println("\n*** "+userInput+" is not a valid option ***\n");
        }
        return item;
    }

    private static void displayItemsMenuOptions(List<Item> items) {
        out.println();
        for(int i = 0; i < items.size(); i++) {
            int optionNum = i+1;
            out.println(optionNum+") "+ items.get(i).getItemName());
        }
        out.print("\nPlease choose an option >>> ");
        out.flush();
    }



    public static Classification getClassificationChoiceFromOptions(List<Classification> classifications) {
        Classification classification = null;
        while (classification == null) {
            displayClassificationMenuOptions(classifications);
            classification = getClassificationChoiceFromUserInput(classifications);
        }
        return classification;
    }

    private static Classification getClassificationChoiceFromUserInput(List<Classification> items) {
        Classification classification = null;
        String userInput = in.next();
        try {
            int selectedClassification = Integer.valueOf(userInput);
            if (selectedClassification <= items.size()) {
                classification = items.get(selectedClassification - 1);
            }
        } catch(NumberFormatException e) {
        }
        if(classification == null) {
            out.println("\n*** "+userInput+" is not a valid option ***\n");
        }
        return classification;
    }

    private static void displayClassificationMenuOptions(List<Classification> classifications) {
        out.println();
        for(int i = 0; i < classifications.size(); i++) {
            int optionNum = i+1;
            out.println(optionNum+") "+ classifications.get(i).getClassificationName());
        }
        out.print("\nPlease choose an option >>> ");
        out.flush();
    }

}