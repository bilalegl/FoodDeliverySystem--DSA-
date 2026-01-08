package services;

import datastructures.BST;
import model.FoodItem;
import java.util.ArrayList;

public class MenuService {

    private BST menu;

    public MenuService() {
        menu = new BST();
    }

    // Add new food item
    public void addFood(FoodItem item) {
        menu.insert(item);
        System.out.println(item.getName() + " added to menu.");
    }

    // Search food by name
    public FoodItem searchFood(String name) {
        return menu.search(name);
    }

    // Search food by ID
    public FoodItem searchFoodById(int id) {
        for(FoodItem item : getAllItems()) {
            if(item.getItemId() == id) {
                return item;
            }
        }
        return null;
    }

    // Display full menu
    public void displayMenu() {
        menu.display();
    }

    // Return all food items as a list (for GUI TableView)
    public ArrayList<FoodItem> getAllItems() {
        return menu.getAllItems(); // BST method
    }

    // GUI placeholder
    public void displayMenuGUI() {
        System.out.println("Displaying menu in GUI (to be implemented).");
    }
}
