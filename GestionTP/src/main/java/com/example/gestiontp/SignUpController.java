package com.example.gestiontp;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import org.mindrot.jbcrypt.BCrypt;

import java.awt.event.ActionEvent;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class SignUpController {

    @FXML
    private TextField username;

    @FXML
    private PasswordField password;

    @FXML
    private Text errorMessage;

    //Database Tools
    private Connection connect;
    private PreparedStatement prepare;
    private ResultSet result;


    @FXML
    private void handleSignUp() {

        String usernameText = username.getText();
        String passwordText = password.getText();

        if (usernameText.isEmpty() || passwordText.isEmpty()) {
            System.out.println("error");
            errorMessage.setText("Veuillez remplir tous les champs !");
            return;
        }


        else{
            String hashedPassword = BCrypt.hashpw(passwordText, BCrypt.gensalt()); // Hash password

            //Check if the username exist in the DB
            String checkuser = "SELECT * FROM utilisateur WHERE Nom_utilisateur = ?";

            connect=Database.connectDB();

            try{
                prepare=connect.prepareStatement(checkuser);
                prepare.setString(1, usernameText); //Set username parameter
                result=prepare.executeQuery();

                if(result.next()){
                    errorMessage.setText(usernameText +"Username exist deja !");
                }
                else{
                    //Insert Data in the DB
                    String insertData = "INSERT INTO utilisateur (Nom_utilisateur, Mot_de_passe) VALUES(?, ?)";

                    prepare=connect.prepareStatement(insertData);
                    prepare.setString(1, usernameText);
                    prepare.setString(2, hashedPassword); // Store hashed password
                    prepare.executeUpdate();
                    errorMessage.setText("Inscription réussie !");


                }

            } catch (Exception e) {
                e.printStackTrace();
            }

        }

    }

    @FXML
    private void handleBack() throws IOException {
        Main.switchScene("Login.fxml");
    }


}
