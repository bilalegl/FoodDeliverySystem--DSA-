package model;

import java.util.List;

public class Order {
    private int orderId;
    private int userId;
    private List<OrderItem> items;
    private double total;
    private boolean isExpress;

    public Order(int orderId, int userId, List<OrderItem> items, double total, boolean isExpress) {
        this.orderId = orderId;
        this.userId = userId;
        this.items = items;
        this.total = total;
        this.isExpress = isExpress;
    }

    public int getOrderId() {
        return orderId;
    }

    public int getUserId() {
        return userId;
    }

    public List<OrderItem> getItems() {
        return items;
    }

    public double getTotal() {
        return total;
    }

    public boolean isExpress() {
        return isExpress;
    }

    // New display method
    public void display() {
        System.out.println("Order ID: " + orderId);
        System.out.println("User ID: " + userId);
        System.out.println("Express: " + (isExpress ? "Yes" : "No"));
        System.out.println("Items:");
        if (items != null && !items.isEmpty()) {
            for (OrderItem item : items) {
                System.out.println("  - " + item.getName() + " x" + item.getQuantity() + " = " + item.getPrice());
            }
        } else {
            System.out.println("  No items in order.");
        }
        System.out.println("Total: " + total);
        System.out.println("-------------------------");
    }

    // Inner class
    public static class OrderItem {
        private String name;
        private int quantity;
        private double price;

        public OrderItem(String name, int quantity, double price) {
            this.name = name;
            this.quantity = quantity;
            this.price = price;
        }

        public String getName() { return name; }
        public int getQuantity() { return quantity; }
        public double getPrice() { return price; }
    }
}
