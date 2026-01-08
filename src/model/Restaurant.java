package model;

public class Restaurant {
    public int restId;
    public String name;
    public double rating;

    public Restaurant(int restId, String name, double rating) {
        this.restId = restId;
        this.name = name;
        this.rating = rating;
    }

    public void display() {
        System.out.println(restId + " | " + name + " | Rating: " + rating);
    }
}
