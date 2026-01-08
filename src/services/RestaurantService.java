package services;

import datastructures.AVLTree;
import model.Restaurant;

public class RestaurantService {

    private AVLTree restaurants;

    public RestaurantService() {
        restaurants = new AVLTree();
    }

    // Add new restaurant
    public void addRestaurant(Restaurant r) {
        restaurants.insert(r);
        System.out.println(r.name + " added with rating " + r.rating);
    }

    // Display all restaurants sorted by rating
    public void displayRestaurants() {
        restaurants.display();
    }

    // Search restaurant (by rating exact match, for demo)
    public void searchByRating(double rating) {
        System.out.println("Searching restaurants with rating " + rating + "...");
        // AVL search not implemented separately here
    }
}
