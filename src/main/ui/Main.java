package ui;


import ui.gui.FoodTrackerGui;

import javax.swing.*;
import java.text.DecimalFormat;

public class Main extends JFrame {

    public static void main(String[] args) {
        FoodTrackerGui app = new FoodTrackerGui();
        app.setVisible(true);
        app.setSize(500, 310);
        app.setLocation(200, 100);
        app.setDefaultCloseOperation(3);

        DecimalFormat df = new DecimalFormat("#.00");
        System.out.println("decimal length is: " + df.format(2.3456).length());

        double d = 234.12413;
        String text = Double.toString(Math.abs(d));
        int integerPlaces = text.indexOf('.');
        int decimalPlaces = text.length() - integerPlaces - 1;
        System.out.println(decimalPlaces);
    }
}
