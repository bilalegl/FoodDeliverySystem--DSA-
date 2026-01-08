package datastructures;

import model.Order;

public class StackLL {

    private class Node {
        Order data;
        Node next;

        Node(Order data) {
            this.data = data;
            this.next = null;
        }
    }

    private Node top;

    public StackLL() {
        top = null;
    }

    public boolean isEmpty() {
        return top == null;
    }

    public void push(Order order) {
        Node newNode = new Node(order);
        newNode.next = top;
        top = newNode;
    }

    public Order pop() {
        if (isEmpty()) return null;
        Order data = top.data;
        top = top.next;
        return data;
    }

    public void displayStack() {
        if (isEmpty()) {
            System.out.println("Stack is empty.");
            return;
        }

        System.out.println("Current Orders in Stack:");
        Node temp = top;
        while (temp != null) {
            Order o = temp.data;
            System.out.println("OrderID: " + o.getOrderId() +
                    ", UserID: " + o.getUserId() +
                    ", Total: " + o.getTotal() +
                    ", Express: " + o.isExpress());
            temp = temp.next;
        }
    }

    public int size() {
        int count = 0;
        Node temp = top;
        while (temp != null) {
            count++;
            temp = temp.next;
        }
        return count;
    }
}
