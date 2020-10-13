# Food Tracker

## Application Overview

This application will serve as a simple food tracker to help 
manage available food items. A current list of items will be 
valuable in meal planning, and the application will be able to
report on total expenditures.

*This application will assume the following:*
- the total expenditures will include the original purchase price of removed items
- listed prices include taxes

As I am a student, I would find this project useful in helping track 
my overall food expenditures. This application could also be used
by restaurants managing a large inventory of food.

## User Stories

As a user, I want to be able to:

- add and remove items from my current available items
- display current items available
- look up an item to see if it is available
- generate a food expenditure balance

## Phase 2 User Stories

- As a user, I want to be able to save my food tracker to file
- As a user, I want to be able to load my food tracker from file when the program starts

## Phase 3: Instructions for Grader

- You can generate the first required event running Main to load the GUI console, typing in the food item name and cost (with dollar format decimal signs), and hitting "Add" to add an item to the food tracker list.
- You can generate the second required event typing the food item name in the GUI console and hitting "Delete."
- You can locate my visual component by running Main 
- You can save the state of the application by hitting the save button in the GUI console (button removed, could not implement)
- You can reload the state of the application by closing the GUI console and re-running Main

## Phase 4: Task 2

I am choosing option 1, to make my program more robust by throwing an exception when the new price of an item entered
is not in a proper money format (checking the decimal places is exactly 2). If the price 23.367 is entered, then an exception 
will be thrown and the user will be notified that the number was not formatted correctly. 

## Phase 4: Task 3

1) To improve cohesion in the FoodTracker class, I removed some methods so it only functions to maintain the state of 
list of food item objects in the list of food items.
I removed totalSpend() and addCostToBalance() methods to a new class, BudgetTracker.
2) To reduce semantic coupling between the BudgetTracker and FoodTracker application, 
I altered the implementation of addItem in FoodTracker and addCostToBalance in BudgetTracker.
addItem was still responsible for adding the cost of the new item added to the total balance 
that is now updated in addCostToBalance in BudgetTracker. 