package datastructures;

import model.User;

public class HashTable {

    private class Node {
        User data;
        Node next;

        Node(User data) {
            this.data = data;
            this.next = null;
        }
    }

    private Node[] table;
    private int capacity;

    public HashTable(int capacity) {
        this.capacity = capacity;
        table = new Node[capacity];
    }

    private int hash(int userId) {
        return userId % capacity;
    }

    public void insert(User user) {
        int index = hash(user.getUserId());
        Node newNode = new Node(user);
        newNode.next = table[index];
        table[index] = newNode;
        System.out.println("User inserted successfully.");
    }

    public User search(int userId) {
        int index = hash(userId);
        Node current = table[index];
        while (current != null) {
            if (current.data.getUserId() == userId) {
                return current.data;
            }
            current = current.next;
        }
        return null;
    }

    public boolean delete(int userId) {
        int index = hash(userId);
        Node current = table[index];
        Node prev = null;

        while (current != null) {
            if (current.data.getUserId() == userId) {
                if (prev == null) table[index] = current.next;
                else prev.next = current.next;
                System.out.println("User deleted successfully.");
                return true;
            }
            prev = current;
            current = current.next;
        }

        System.out.println("User not found.");
        return false;
    }

    public void display() {
        for (int i = 0; i < capacity; i++) {
            Node current = table[i];
            if (current != null) {
                System.out.println("Bucket " + i + ":");
                while (current != null) {
                    current.data.display();
                    System.out.println("------------------");
                    current = current.next;
                }
            }
        }
    }
}
