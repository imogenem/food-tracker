package model;


import java.util.LinkedList;

public class FoodTracker {


    public LinkedList<FoodItem> items;

    public FoodTracker() {
        this.items = new LinkedList<>();
    }


    // MODIFIES: items
    // EFFECTS: adds an item to food tracker
    public void addItem(FoodItem i) {
        items.add(i);
    }

    // MODIFIES: items
    // EFFECTS: removes an item from food tracker
    public void removeItem(String name) {
        for (FoodItem item : items) {
            if (name.equals(item.getItemName())) {
                items.remove(item);
                return;
            }
        }
    }

    // EFFECTS: returns an item if available
    public String searchItem(String name) {
        for (FoodItem item : items) {
            if (items.contains(name)) {
                return item.getItemName();
            }
        }
        return "This item is not in stock.";
    }

    // EFFECTS: returns list of all items in stock
    public LinkedList<FoodItem> displayInStockItems() {
        return items;
    }

}
