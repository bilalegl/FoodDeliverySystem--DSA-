package services;

import model.Order;
import java.util.ArrayList;
import java.util.List;

public class OrderService {

    private List<Order> orders;

    public OrderService() {
        orders = new ArrayList<>();
    }

    // Place a new order
    public void placeOrder(Order order) {
        orders.add(order);
        System.out.println("Order placed successfully!");
        System.out.println("Order details:");
        order.display(); // now works because Order has display()
    }

    // Undo last order
    public boolean undoLastOrder() {
        if (orders.isEmpty()) {
            System.out.println("No orders to undo.");
            return false;
        }
        Order last = orders.remove(orders.size() - 1);
        System.out.println("Last order removed:");
        last.display(); // works now
        return true;
    }

    // Show all orders
    public void showOrders() {
        if (orders.isEmpty()) {
            System.out.println("No orders found.");
            return;
        }
        System.out.println("All Orders:");
        for (Order order : orders) {
            order.display(); // works now
        }
    }

    public List<Order> getAllOrders() {
        return orders;
    }
}
