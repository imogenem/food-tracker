package ui.gui;

import exceptions.MoneyFormatException;
import model.FoodItem;
import model.FoodTracker;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.io.*;
import java.util.Iterator;
import javax.swing.*;


// adapted from https://www.youtube.com/watch?v=0KdG9DZgi6I&t=1269s
public class FoodTrackerGui extends JFrame {
    JTextArea foodTextArea = new JTextArea();
    JLabel nameLabel = new JLabel("Food Name: ");
    JTextField nameTextField = new JTextField(10);
    JLabel costLabel = new JLabel("Cost: ");
    JTextField costTextField = new JTextField(10);
    JButton addButton = new JButton("Add");
    JButton deleteButton = new JButton("Delete");
    JButton saveButton = new JButton("Save");
    FoodTracker myFoodTracker;

    private FoodItem item;

    public FoodTrackerGui() {
        runTracker();
    }

    public void runTracker() {
        loadGui();
        loadTracker();
    }

    public void loadGui() {
        myFoodTracker = new FoodTracker();
        JPanel flow1Panel = new JPanel(new FlowLayout(1));
        JPanel flow2Panel = new JPanel(new FlowLayout(1));
        JPanel gridPanel = new JPanel(new GridLayout(2, 1));
        flow1Panel.add(this.nameLabel);
        flow1Panel.add(this.nameTextField);
        flow1Panel.add(this.costLabel);
        flow1Panel.add(this.costTextField);
        flow2Panel.add(this.addButton);
        flow2Panel.add(this.deleteButton);
        flow2Panel.add(this.saveButton);
        gridPanel.add(flow1Panel);
        gridPanel.add(flow2Panel);
        this.add(this.foodTextArea, "Center");
        this.add(gridPanel, "South");
        linkActionToButton();
    }

    public void linkActionToButton() {
        this.addButton.addActionListener((event) -> {
            this.addItemGui();
        });
        this.deleteButton.addActionListener((event) -> {
            this.deleteItem();
        });
        this.saveButton.addActionListener((event) -> {
            this.saveTracker();
        });
    }

    private boolean isFoodItemInLinkedList(String name) {
        this.foodTextArea.setEditable(false);
        boolean inList = false;
        Iterator var3 = this.myFoodTracker.items.iterator();

        while (var3.hasNext()) {
            FoodItem item = (FoodItem) var3.next();
            if (item.getItemName().compareToIgnoreCase(name) == 0) {
                inList = true;
            }
        }
        return inList;
    }

    public boolean isCostInMoneyFormat(int cost) {

        return (costTextField.getText().indexOf(".") >= 0 &&
                costTextField.getText().indexOf(".") < costTextField.getText().length() - 2);
    }

    private void addItemGui() {
        this.foodTextArea.setText("");

        if (this.isFoodItemInLinkedList(this.nameTextField.getText())) {
            JOptionPane.showMessageDialog(this, "Error: food item is already in the database.");
        } else {

            Double cost = Double.parseDouble(costTextField.getText());
            FoodItem item = new FoodItem(cost, nameTextField.getText());
            myFoodTracker.addItem(item);

            System.out.println("full list " + myFoodTracker.displayInStockItems());

            this.displayAll();
            this.nameTextField.setText("");
            this.costTextField.setText("");
        }
    }

    private void deleteItem() {
        if (this.myFoodTracker.items.size() == 0) {
            JOptionPane.showMessageDialog(this, "Error: Database is empty.");
            System.out.println("reading database is empty");
        } else if (!this.isFoodItemInLinkedList(this.nameTextField.getText())) {
            JOptionPane.showMessageDialog(this, "Error: item is not in the database.");
            System.out.println("reading item not in list");
        } else {
            System.out.println("making it into the else");
            String currName = nameTextField.getText();
            myFoodTracker.removeItem(currName);
            System.out.println("adjusted list " + myFoodTracker.displayInStockItems());
        }
        this.displayAll();
        this.nameTextField.setText("");
        this.costTextField.setText("");
    }

    private void displayAll() {
        this.foodTextArea.setText("");
        Iterator var1 = this.myFoodTracker.items.iterator();

        while (var1.hasNext()) {
            FoodItem item = (FoodItem) var1.next();
            this.foodTextArea.append(item + "\n");
        }
    }

    // Adapted from <https://howtodoinjava.com/java/library/json-simple-read-write-json-examples/#maven>
    // MODIFIES: this
    // EFFECTS: saves current state of tracker to file
    public void saveTracker() {
        try {
            FileWriter file = new FileWriter("data/tracker.json");
            JSONArray foodList = new JSONArray();

            for (int i = 0; i < myFoodTracker.items.size(); i++) {

                JSONObject foodDetails = new JSONObject();
                JSONObject trackerObject = new JSONObject();
                foodDetails.put("itemName", myFoodTracker.items.get(i).getItemName());
                foodDetails.put("itemCost", myFoodTracker.items.get(i).getItemCost());

                trackerObject.put("item", foodDetails);

                foodList.add(trackerObject);

            }
            // Write JSON file
            file.write(foodList.toJSONString());
            file.flush();

            System.out.println("List saved");

            //close the writer
            file.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    // EFFECTS: returns the previous state of the tracker
    public void loadTracker() {
        JSONParser jsonParser = new JSONParser();

        try (FileReader reader = new FileReader("data/tracker.json")) {
            // READ JSON file
            Object foodItemJson = jsonParser.parse(reader);

            JSONArray foodList = (JSONArray) foodItemJson;
            System.out.println(foodList);

            // Iterate over food array to convert to JSON object
            foodList.forEach(emp -> parseFoodObject((JSONObject) emp));

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    private void parseFoodObject(JSONObject t) {
        //Get tracker object within list
        JSONObject foodDetails = (JSONObject) t.get("item");

        //Get items in tracker
        String name = (String) foodDetails.get("itemName");
        double cost = (Double) foodDetails.getOrDefault("itemCost", 0.0);
        myFoodTracker.addItem(new FoodItem(cost, name));
        this.foodTextArea.append(new FoodItem(cost, name) + "\n");

    }



}
