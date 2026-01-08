package datastructures;

import model.FoodItem;
import java.util.ArrayList;

public class BST {

    private class Node {
        FoodItem data;
        Node left, right;

        Node(FoodItem data) {
            this.data = data;
            left = right = null;
        }
    }

    private Node root;

    public BST() {
        root = null;
    }

    public void insert(FoodItem item) {
        root = insertRec(root, item);
    }

    private Node insertRec(Node root, FoodItem item) {
        if (root == null) return new Node(item);

        // ✅ Use getters instead of direct field access
        if (item.getName().compareToIgnoreCase(root.data.getName()) < 0) {
            root.left = insertRec(root.left, item);
        } else if (item.getName().compareToIgnoreCase(root.data.getName()) > 0) {
            root.right = insertRec(root.right, item);
        } else {
            System.out.println("Food item already exists.");
        }
        return root;
    }

    public FoodItem search(String name) {
        return searchRec(root, name);
    }

    private FoodItem searchRec(Node root, String name) {
        if (root == null) return null;
        if (name.equalsIgnoreCase(root.data.getName())) return root.data;
        if (name.compareToIgnoreCase(root.data.getName()) < 0) return searchRec(root.left, name);
        return searchRec(root.right, name);
    }

    public void display() {
        System.out.println("Food Menu (Sorted by Name):");
        displayRec(root);
    }

    private void displayRec(Node root) {
        if (root != null) {
            displayRec(root.left);
            root.data.display();
            System.out.println("------------------");
            displayRec(root.right);
        }
    }

    // ✅ Method to get all items as ArrayList
    public ArrayList<FoodItem> getAllItems() {
        ArrayList<FoodItem> list = new ArrayList<>();
        inOrder(root, list);
        return list;
    }

    // helper for in-order traversal
    private void inOrder(Node node, ArrayList<FoodItem> list) {
        if (node == null) return;
        inOrder(node.left, list);
        list.add(node.data);
        inOrder(node.right, list);
    }
}
