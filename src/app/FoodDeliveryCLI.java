package app;

import services.*;
import model.*;
import utils.CLIHelper;

import java.util.ArrayList;
import java.util.List;

public class FoodDeliveryCLI {

    private static UserService userService = new UserService();
    private static MenuService menuService = new MenuService();
    private static RestaurantService restaurantService = new RestaurantService();
    private static OrderService orderService = new OrderService();
    private static DeliveryService deliveryService = new DeliveryService(6); // 6 nodes demo

    private static int loggedInUserId = -1; // -1 means no user logged in

    public static void main(String[] args) {
        initDemoData();

        boolean exit = false;
        while (!exit) {
            CLIHelper.printSection("Food Delivery CLI");
            System.out.println("1. Signup");
            System.out.println("2. Login");
            System.out.println("3. View Food Menu");
            System.out.println("4. Place Order");
            System.out.println("5. Undo Last Order");
            System.out.println("6. View Restaurants");
            System.out.println("7. View Delivery Network");
            System.out.println("8. Exit");

            int choice = CLIHelper.promptInt("Enter choice: ");

            switch (choice) {
                case 1 -> signup();
                case 2 -> login();
                case 3 -> menuService.displayMenu();
                case 4 -> placeOrder();
                case 5 -> {
                    orderService.undoLastOrder(); // void method
                    System.out.println("Last order undone successfully!");
                }
                case 6 -> restaurantService.displayRestaurants();
                case 7 -> viewDeliveryNetwork();
                case 8 -> exit = true;
                default -> System.out.println("Invalid choice!");
            }
        }

        System.out.println("Exiting Food Delivery CLI...");
    }

    // Demo Data
    private static void initDemoData() {
        // Users
        userService.signup(new User(101, "Ali", "03001234567"));
        userService.signup(new User(102, "Sara", "03007654321"));

        // Food Menu
        menuService.addFood(new FoodItem(1, "Burger", 250));
        menuService.addFood(new FoodItem(2, "Pizza", 500));
        menuService.addFood(new FoodItem(3, "Sandwich", 150));
        menuService.addFood(new FoodItem(4, "Fries", 120));

        // Restaurants
        restaurantService.addRestaurant(new Restaurant(201, "Burger King", 4.2));
        restaurantService.addRestaurant(new Restaurant(202, "Pizza Hut", 4.5));
        restaurantService.addRestaurant(new Restaurant(203, "KFC", 4.0));
        restaurantService.addRestaurant(new Restaurant(204, "Subway", 4.3));

        // Delivery Network Graph
        deliveryService.addRoute(0, 1);
        deliveryService.addRoute(0, 2);
        deliveryService.addRoute(1, 3);
        deliveryService.addRoute(2, 4);
        deliveryService.addRoute(3, 5);
    }

    // Signup CLI
    private static void signup() {
        int id = CLIHelper.promptInt("Enter User ID: ");
        String name = CLIHelper.promptString("Enter Name: ");
        String contact = CLIHelper.promptString("Enter Contact: ");
        userService.signup(new User(id, name, contact));
    }

    // Login CLI
    private static void login() {
        int id = CLIHelper.promptInt("Enter User ID: ");
        User user = userService.login(id); // returns User object
        if(user != null) {
            loggedInUserId = user.getUserId(); // fixed from getId()
            System.out.println("Login successful! Welcome " + user.getName());
        } else {
            System.out.println("User not found!");
        }
    }

    // Place Order CLI
    private static void placeOrder() {
        if(loggedInUserId == -1) {
            System.out.println("Please login first!");
            return;
        }

        int orderId = CLIHelper.promptInt("Enter Order ID: ");
        int foodId = CLIHelper.promptInt("Enter Food ID: ");
        int qty = CLIHelper.promptInt("Enter Quantity: ");
        boolean isExpress = CLIHelper.promptYesNo("Is it an express order?");

        FoodItem item = menuService.searchFoodById(foodId);
        if(item == null) {
            System.out.println("Food item not found!");
            return;
        }

        List<Order.OrderItem> orderItems = new ArrayList<>();
        orderItems.add(new Order.OrderItem(item.getName(), qty, item.getPrice()));
        double total = qty * item.getPrice();

        Order order = new Order(orderId, loggedInUserId, orderItems, total, isExpress);
        orderService.placeOrder(order);

        System.out.println("Order placed successfully!");
    }

    // View Delivery Network CLI
    private static void viewDeliveryNetwork() {
        deliveryService.displayNetwork();
        System.out.println("\nBFS Traversal from Node 0:");
        deliveryService.bfsRoute(0);
        System.out.println("\nDFS Traversal from Node 0:");
        deliveryService.dfsRoute(0);
    }
}
