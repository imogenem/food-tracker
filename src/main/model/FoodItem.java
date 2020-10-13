package model;

// Adapted from Lab 5 coding
public class FoodItem {
    private double cost;
    private String name;

    // EFFECTS: item has cost, description, and is not in stock
    public FoodItem(double cost, String name) {
        this.cost = cost;
        this.name = name;
    }

    public String getItemName() {
        return name;
    }

    @Override
    public String toString() {
        return name;
    }

    public double getItemCost() {
        return cost;
    }


}

