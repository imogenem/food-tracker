package model;

import exceptions.MoneyFormatException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import static org.junit.jupiter.api.Assertions.fail;

class FoodTrackerTest {

    FoodTracker itemList;
    BudgetTracker budgetTracker;


    @BeforeEach
    public void setup() {
        itemList = new FoodTracker();
        budgetTracker = new BudgetTracker();
    }

    @Test
    public void testCheckMoneyFormat() {
        FoodItem butter = new FoodItem(2.345, "Butter");
        itemList.addItem(butter);
        FoodItem bread = new FoodItem(2.34, "Bread");
        itemList.addItem(bread);
        FoodItem cake = new FoodItem(2.3, "Cake");
        itemList.addItem(cake);

        assertFalse(budgetTracker.isCostInMoneyFormat(butter));
        assertTrue(budgetTracker.isCostInMoneyFormat(bread));
        assertFalse(budgetTracker.isCostInMoneyFormat(cake));
    }

    @Test
    public void testAddItem() {
        FoodItem butter = new FoodItem(2.00, "Butter");
        itemList.addItem(butter);

        assertEquals(itemList.displayInStockItems().size(), 1);
        assertTrue(itemList.displayInStockItems().contains(butter));
    }

    @Test
    public void testRemoveItem() {
        FoodItem butter = new FoodItem(2.00, "Butter");
        itemList.addItem(butter);
        FoodItem bread = new FoodItem(5.00, "Bread");
        itemList.addItem(bread);
        itemList.removeItem("Bread");
        assertEquals(itemList.displayInStockItems().size(), 1);
        assertFalse(itemList.displayInStockItems().contains(bread));
        itemList.displayInStockItems();

    }

    @Test
    public void testDisplayCurrentItems() {
        FoodItem butter = new FoodItem(2.00, "Butter");

        itemList.addItem(butter);

        FoodItem bread = new FoodItem(5.00, "Bread");

        itemList.addItem(bread);

        assertEquals(itemList.displayInStockItems().size(), 2);
        assertTrue(itemList.displayInStockItems().contains(butter));
        assertTrue(itemList.displayInStockItems().contains(bread));
    }
    // Test methods adapted from BandMaster practice file

    @Test
    public void testDisplayCurrentItemsWhenListEmpty() {
        assertEquals(itemList.displayInStockItems().size(), 0);

    }

    @Test
    public void testGetTotalSpend() {
        FoodItem butter = new FoodItem(2.00, "Butter");

        FoodItem bread = new FoodItem(5.00, "Bread");
        itemList.addItem(bread);
        FoodItem milk = new FoodItem(4.00, "Milk");
        itemList.addItem(milk);

        itemList.addItem(butter);
        try {
            budgetTracker.addCostToBalance(butter);
        } catch (MoneyFormatException e) {
            // test passes
        }
        try {
            budgetTracker.addCostToBalance(bread);
        } catch (MoneyFormatException e) {
            // test passes
        }
        try {
            budgetTracker.addCostToBalance(milk);
        } catch (MoneyFormatException e) {
            // test passes
        }
        assertEquals(budgetTracker.totalSpend(), 2.00 + 5.00 + 4.00);
    }

    @Test
    public void testAddCostThrowException() {
        FoodItem butter = new FoodItem(2.345, "Butter");
        itemList.addItem(butter);
        try {
            budgetTracker.addCostToBalance(butter);
        } catch (MoneyFormatException e) {
            fail("Not properly formatted");
        }
        //assertEquals(budgetTracker.totalSpend(), 0);
    }

    @Test
    public void testSearchForItem() {
        FoodItem butter = new FoodItem(2.00, "Butter");
        itemList.addItem(butter);

        FoodItem bread = new FoodItem(5.00, "Bread");
        itemList.addItem(bread);

        FoodItem milk = new FoodItem(4.00, "Milk");
        itemList.addItem(milk);

        FoodItem cheese = new FoodItem(10.00, "Cheese");
        itemList.addItem(cheese);

        itemList.removeItem("Bread");
        itemList.searchItem("Butter");
        assertTrue(itemList.displayInStockItems().contains(butter));
        assertFalse(itemList.displayInStockItems().contains(bread));
        itemList.searchItem("Pasta");
        assertEquals(itemList.searchItem("Pasta"), "This item is not in stock.");

    }


}