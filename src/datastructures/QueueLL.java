package datastructures;

import model.Order;

public class QueueLL {

    private class Node {
        Order data;
        Node next;

        Node(Order data) {
            this.data = data;
            this.next = null;
        }
    }

    private Node front;
    private Node rear;

    public QueueLL() {
        front = rear = null;
    }

    public boolean isEmpty() {
        return front == null;
    }

    public void enqueue(Order order) {
        Node newNode = new Node(order);
        if (rear != null) {
            rear.next = newNode;
        }
        rear = newNode;
        if (front == null) front = newNode;
    }

    public Order dequeue() {
        if (isEmpty()) return null;
        Order data = front.data;
        front = front.next;
        if (front == null) rear = null;
        return data;
    }

    public void displayQueue() {
        if (isEmpty()) {
            System.out.println("Queue is empty.");
            return;
        }

        System.out.println("Current Orders in Queue:");
        Node temp = front;
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
        Node temp = front;
        while (temp != null) {
            count++;
            temp = temp.next;
        }
        return count;
    }
}
