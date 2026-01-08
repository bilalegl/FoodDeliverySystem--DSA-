package utils;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;

public class CSSHelper {

    // Foodie button
    public static void styleButton(Button btn) {
        btn.setStyle(
            "-fx-background-color: #FF7043;" +
            "-fx-text-fill: white;" +
            "-fx-font-size: 16px;" +
            "-fx-font-weight: bold;" +
            "-fx-padding: 12 24;" +
            "-fx-background-radius: 30;"
        );

        btn.setOnMouseEntered(e -> btn.setStyle(
            "-fx-background-color: #E64A19;" +
            "-fx-text-fill: white;" +
            "-fx-font-size: 16px;" +
            "-fx-font-weight: bold;" +
            "-fx-padding: 12 24;" +
            "-fx-background-radius: 30;"
        ));

        btn.setOnMouseExited(e -> styleButton(btn));
    }

    // Foodie title
    public static void styleLabel(Label lbl, int fontSize) {
        lbl.setStyle(
            "-fx-text-fill: #D84315;" +
            "-fx-font-size: " + fontSize + "px;" +
            "-fx-font-weight: bold;"
        );
    }

    // Card-style panel
    public static void stylePanel(Region panel) {
        panel.setStyle(
            "-fx-background-color: white;" +
            "-fx-padding: 20;" +
            "-fx-background-radius: 15;" +
            "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.15), 12, 0, 0, 4);"
        );
    }
}
