package model;

import exceptions.MoneyFormatException;

public class BudgetTracker {

    public double balance;
    private FoodTracker itemList;

    public BudgetTracker() {
        itemList = new FoodTracker();
        this.balance = 0;
    }

    //     adapted from https://stackoverflow.com/questions/6264576/number-of-decimal-digits-in-a-double
    public boolean isCostInMoneyFormat(FoodItem item) {

        String text = Double.toString(Math.abs(item.getItemCost()));
        int integerPlaces = text.indexOf('.');
        int decimalPlaces = text.length() - integerPlaces - 1;

        if (decimalPlaces > 2 && decimalPlaces < 2) {
            return false;
        }
        return true;

    }

    //return (decimalPlaces <= 2);

    // MODIFIES: this
    // EFFECTS: add item cost to total cost
    public double addCostToBalance(FoodItem item) throws MoneyFormatException {
        if (!isCostInMoneyFormat(item)) {
            throw new MoneyFormatException();
        } else {
            itemList.addItem(item);
            balance += item.getItemCost();
        }
        return balance;
    }


    // EFFECTS: returns the total spend per month
    public double totalSpend() {
        return balance;
    }


}

//return (df.format(cost).length() <= 1);

//    public boolean isCostInMoneyFormat(double cost) {
//        DecimalFormat df = new DecimalFormat("#.00");
//         int decimalLength = (df.format(cost).length());
//        return (decimalLength == 2);
//    }

//(BigDecimal.valueOf(cost).scale() > 2);