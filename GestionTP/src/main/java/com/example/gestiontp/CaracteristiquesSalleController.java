package com.example.gestiontp;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;

import java.util.HashSet;
import java.util.Set;

public class CaracteristiquesSalleController {

    @FXML private Button matlabbutton;
    @FXML private Button javaJdkbutton;
    @FXML private Button devcbutton;
    @FXML private Button logiciel4button;
    @FXML private Button logiciel5button;
    @FXML private Button logiciel6button;
    @FXML private Button logiciel7button;
    @FXML private Button logiciel8button;
    @FXML private Button logiciel9button; // Added button
    @FXML private Button logiciel10button; // Added button

    private final Set<Button> logicielButtons = new HashSet<>();
    private final Color defaultColor = Color.TRANSPARENT; // Or any default color you have
    private final Color clickedColor = Color.LIGHTBLUE; // Or any color you want on click
    private final Set<Button> selectedLogicielButtons = new HashSet<>();

    @FXML
    public void initialize() {
        // Add all the logiciel buttons to the set
        logicielButtons.add(matlabbutton);
        logicielButtons.add(javaJdkbutton);
        logicielButtons.add(devcbutton);
        logicielButtons.add(logiciel4button);
        logicielButtons.add(logiciel5button);
        logicielButtons.add(logiciel6button);
        logicielButtons.add(logiciel7button);
        logicielButtons.add(logiciel8button);
        logicielButtons.add(logiciel9button);
        logicielButtons.add(logiciel10button);


        // Initialize the background color of the buttons
        for (Button button : logicielButtons) {
            button.setStyle(String.format("-fx-background-color: #C3CED3;",
                    (int) (defaultColor.getRed() * 255),
                    (int) (defaultColor.getGreen() * 255),
                    (int) (defaultColor.getBlue() * 255)));
        }
    }

    @FXML
    private void ChoisirlogicielButton(ActionEvent event) {
        Button clickedButton = (Button) event.getSource();

        if (selectedLogicielButtons.contains(clickedButton)) {
            // If the button is already selected, deselect it
            selectedLogicielButtons.remove(clickedButton);
            clickedButton.setStyle(String.format("-fx-background-color: #012A4A;",
                    (int) (defaultColor.getRed() * 255),
                    (int) (defaultColor.getGreen() * 255),
                    (int) (defaultColor.getBlue() * 255)));
        } else {
            // If the button is not selected, select it
            selectedLogicielButtons.add(clickedButton);
            clickedButton.setStyle(String.format("-fx-background-color: #012A4A ;",
                    (int) (clickedColor.getRed() * 255),
                    (int) (clickedColor.getGreen() * 255),
                    (int) (clickedColor.getBlue() * 255)));
        }

        System.out.println("Selected logiciels: " + selectedLogicielButtons.stream().map(Button::getText).toList());

        /*Back-end : enregistrer les informations entrees
        + automatiser le calcul de la capacite de la salle
        */
    }


    @FXML
    private void closewindow(ActionEvent event) {
        // Your close window logic
    }

    @FXML
    private void openEquipement(ActionEvent actionEvent) {
        // Your open equipment logic
    }

    // You might have other @FXML methods, keep them.
}