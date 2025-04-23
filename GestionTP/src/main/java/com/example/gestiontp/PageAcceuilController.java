package com.example.gestiontp;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import java.io.IOException;

public class PageAcceuilController {

    @FXML private AnchorPane Dashboard;
    @FXML private Hyperlink A21Link;
    @FXML private Hyperlink A22Link;
    @FXML private Hyperlink A23Link;
    @FXML private Hyperlink A24Link;
    @FXML private Hyperlink A25Link;
    @FXML private Hyperlink A31Link;
    @FXML private Hyperlink A32Link;
    @FXML private Hyperlink A33Link;
    @FXML private Hyperlink A34Link;
    @FXML private Hyperlink UnixLink;
    @FXML private Hyperlink A41Link;
    @FXML private Hyperlink A42Link;
    @FXML private Hyperlink A43Link;
    @FXML private Hyperlink emploidutempsLink;
    @FXML private Hyperlink stockLink;
    @FXML private Hyperlink loggingLink;
    @FXML private Hyperlink deconnecterLink;
    @FXML private ToggleButton ModeToggle;

    @FXML
    private void CodeCouleur(ActionEvent event) {
        /*Back-end*/
    }

    @FXML
    private void ouvrirSalle(ActionEvent event) throws IOException {
/*Back-end*/
        Stage popupStage = Main.openPopupWindow("/com/example/gestiontp/CaracteristiquesSalle.fxml", "Détails", 500, 600);
        popupStage.setX(100);
        popupStage.setY(40);
    }

    @FXML
    private void AfficherAcceuil(ActionEvent event) throws IOException {
        Main.switchScene("Acceuil.fxml");
    }

    @FXML
    private void AfficherEmploiDuTemps(ActionEvent event) throws IOException {
        Main.switchScene("EmploiDuTemps.fxml");
    }

    @FXML
    private void AfficherStock(ActionEvent event) throws IOException {
        Main.switchScene("Stock.fxml");
    }

    @FXML
    private void AfficherLogging(ActionEvent event) throws IOException {
        Main.switchScene("Logging.fxml");
    }

    @FXML
    private void Deconnecter(ActionEvent event) throws IOException {
        Main.switchScene("Login.fxml");
    }

    @FXML
    private void changertheme() {
        Main.setDarkMode(ModeToggle.isSelected());
    }

    public void openfiltrage(ActionEvent event) throws IOException {
        Stage popupWindow = Main.openPopupWindow2("FiltrageSalle.fxml", "filtrer", 550, 350);
        popupWindow.setX(40);
        popupWindow.setY(160);
    }
}