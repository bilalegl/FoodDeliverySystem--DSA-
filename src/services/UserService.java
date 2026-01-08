package services;

import datastructures.HashTable;
import model.User;

public class UserService {

    private HashTable users;

    public UserService() {
        users = new HashTable(10); // size of hash table
    }

    // Signup
    public void signup(User user) {
        if (users.search(user.getUserId()) != null) {
            System.out.println("User already exists!");
        } else {
            users.insert(user);
            System.out.println("Signup successful!");
        }
    }

    // Login
    public User login(int userId) {
        User user = users.search(userId);
        if (user == null) {
            System.out.println("User not found!");
        } else {
            System.out.println("Welcome " + user.getName());
        }
        return user;
    }

    // Display all users
    public void displayUsers() {
        System.out.println("=== Registered Users ===");
        users.display();
    }

    // CLI-friendly search
    public User searchUser(int userId) {
        return users.search(userId);
    }
}
