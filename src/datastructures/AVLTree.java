package datastructures;

import model.Restaurant;

public class AVLTree {

    private class Node {
        Restaurant data;
        Node left, right;
        int height;

        Node(Restaurant data) {
            this.data = data;
            left = right = null;
            height = 1;
        }
    }

    private Node root;

    public AVLTree() {
        root = null;
    }

    private int height(Node N) {
        return (N == null) ? 0 : N.height;
    }

    private int getBalance(Node N) {
        return (N == null) ? 0 : height(N.left) - height(N.right);
    }

    private Node rightRotate(Node y) {
        Node x = y.left;
        Node T2 = x.right;

        x.right = y;
        y.left = T2;

        y.height = Math.max(height(y.left), height(y.right)) + 1;
        x.height = Math.max(height(x.left), height(x.right)) + 1;

        return x;
    }

    private Node leftRotate(Node x) {
        Node y = x.right;
        Node T2 = y.left;

        y.left = x;
        x.right = T2;

        x.height = Math.max(height(x.left), height(x.right)) + 1;
        y.height = Math.max(height(y.left), height(y.right)) + 1;

        return y;
    }

    public void insert(Restaurant r) {
        root = insertRec(root, r);
    }

    private Node insertRec(Node node, Restaurant r) {
        if (node == null) return new Node(r);

        if (r.rating < node.data.rating)
            node.left = insertRec(node.left, r);
        else if (r.rating > node.data.rating)
            node.right = insertRec(node.right, r);
        else
            return node; // ignore duplicate rating

        node.height = 1 + Math.max(height(node.left), height(node.right));

        int balance = getBalance(node);

        // Left Left
        if (balance > 1 && r.rating < node.left.data.rating) return rightRotate(node);

        // Right Right
        if (balance < -1 && r.rating > node.right.data.rating) return leftRotate(node);

        // Left Right
        if (balance > 1 && r.rating > node.left.data.rating) {
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }

        // Right Left
        if (balance < -1 && r.rating < node.right.data.rating) {
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }

        return node;
    }

    public void display() {
        System.out.println("Restaurants (Sorted by Rating):");
        displayRec(root);
    }

    private void displayRec(Node node) {
        if (node != null) {
            displayRec(node.left);
            node.data.display();
            System.out.println("------------------");
            displayRec(node.right);
        }
    }
}

