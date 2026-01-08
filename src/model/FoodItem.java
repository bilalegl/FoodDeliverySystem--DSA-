package model;

public class FoodItem {
    private int itemId;
    private String name;
    private double price;

    public FoodItem(int itemId, String name, double price) {
        this.itemId = itemId;
        this.name = name;
        this.price = price;
    }

    // ✅ Getters for JavaFX TableView
    public int getItemId() { return itemId; }
    public String getName() { return name; }
    public double getPrice() { return price; }

    // Optional: display method
    public void display() {
        System.out.println(itemId + " - " + name + " Rs." + price);
    }
}
