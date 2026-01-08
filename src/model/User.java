package model;

public class User {
    private int userId;
    private String name;
    private String phone;

    public User(int userId, String name, String phone) {
        this.userId = userId;
        this.name = name;
        this.phone = phone;
    }

    // Getters
    public int getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    // Display user info (CLI friendly)
    public void display() {
        System.out.println("User ID: " + userId);
        System.out.println("Name   : " + name);
        System.out.println("Phone  : " + phone);
    }
}
