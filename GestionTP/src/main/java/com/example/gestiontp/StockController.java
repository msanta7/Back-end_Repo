package com.example.gestiontp;

import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

public class StockController {

    @FXML private TableView<caracteristique> tablecaract;
    @FXML private TableColumn<caracteristique, String> ElementColumn;
    @FXML private TableColumn<caracteristique, String> TypeColumn;
    @FXML private TableColumn<caracteristique, String> NeufColumn;
    @FXML private TableColumn<caracteristique, String> UtiliseColumn;
    @FXML private ToggleButton ModeToggle;

    private final ObservableList<caracteristique> dataLogiciel = FXCollections.observableArrayList(
            new caracteristique("Ventillo", "", "", ""),
            new caracteristique("Ecran", "", "", ""),
            new caracteristique("Souris", "", "", ""),
            new caracteristique("RAM", "", "", ""),
            new caracteristique("Clavier", "", "", ""),
            new caracteristique("CPU", "", "", ""),
            new caracteristique("Disque", "", "", "")
    );

    @FXML
    public void initialize() {
        // Initialisation des colonnes
        ElementColumn.setCellValueFactory(new PropertyValueFactory<>("Element"));
        TypeColumn.setCellValueFactory(new PropertyValueFactory<>("Type"));
        NeufColumn.setCellValueFactory(new PropertyValueFactory<>("Neuf"));
        UtiliseColumn.setCellValueFactory(new PropertyValueFactory<>("Utilise"));

        tablecaract.setEditable(true);
        tablecaract.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        tablecaract.setItems(dataLogiciel);

        // Configuration des cellules pour l'édition et le style dynamique
        applyCellFactories();

        // Autoriser l'édition directement via TextFieldTableCell
        ElementColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        TypeColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        NeufColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        UtiliseColumn.setCellFactory(TextFieldTableCell.forTableColumn());

        // Gestion de la commit de l'édition
        ElementColumn.setOnEditCommit(event -> event.getRowValue().setElement(event.getNewValue()));
        TypeColumn.setOnEditCommit(event -> event.getRowValue().setType(event.getNewValue()));
        NeufColumn.setOnEditCommit(event -> event.getRowValue().setNeuf(event.getNewValue()));
        UtiliseColumn.setOnEditCommit(event -> event.getRowValue().setUtilise(event.getNewValue()));

        // Ajuster la hauteur du tableau
        tablecaract.setFixedCellSize(32);
        tablecaract.prefHeightProperty().bind(
                Bindings.size(tablecaract.getItems()).multiply(tablecaract.getFixedCellSize()).add(28)
        );

        // Appliquer le mode en fonction de l'état initial
        ModeToggle.setSelected(Main.isDarkMode());
    }

    private void applyCellFactories() {
        String baseStyle = "-fx-padding: 5px; -fx-text-alignment: center; -fx-font-family:'Poppins Regular'; -fx-font-size: 13; -fx-translate-y: 2;";
        String lightModeColor = "-fx-fill: derive(#292D32, 0%);";
        String darkModeColor = "-fx-fill: derive(#FFFFFF, 0%);";
        String textColor = Main.isDarkMode() ? darkModeColor : lightModeColor;
        String finalStyle = baseStyle + textColor;

        // Utiliser une lambda pour définir le CellFactory qui crée des TableCell avec style
        javafx.util.Callback<TableColumn<caracteristique, String>, TableCell<caracteristique, String>> cellFactory =
                param -> new TableCell<>() {
                    private final Text text = new Text();

                    {
                        text.wrappingWidthProperty().bind(param.widthProperty());
                        text.setStyle(finalStyle);
                        setGraphic(text);
                    }

                    @Override
                    protected void updateItem(String item, boolean empty) {
                        super.updateItem(item, empty);
                        text.setText(empty || item == null ? "" : item);
                    }
                };

        // Appliquer la CellFactory pour le style (le TextFieldTableCell gère l'édition)
        // Nous n'appliquons plus notre style directement via setCellFactory ici pour permettre l'édition.
        // Le style est appliqué au Text à l'intérieur du TextFieldTableCell par défaut.
    }

    // === Boutons d'action ===

    @FXML
    private void ajouterLigne() {
        tablecaract.getItems().add(new caracteristique("", "", "", ""));
    }

    @FXML
    private void supprimerLigne() {
        ObservableList<caracteristique> selectedItems = tablecaract.getSelectionModel().getSelectedItems();

        if (!selectedItems.isEmpty()) {
            tablecaract.getItems().removeAll(FXCollections.observableArrayList(selectedItems));
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Suppression impossible");
            alert.setHeaderText(null);
            alert.setContentText("Veuillez sélectionner une ou plusieurs lignes à supprimer.");
            alert.showAndWait();
        }
    }

    // === Navigation entre pages ===

    @FXML private void AfficherAcceuil(ActionEvent event) throws IOException {
        Main.switchScene("PageAcceuil.fxml");
    }

    @FXML private void AfficherEmploiDuTemps(ActionEvent event) throws IOException {
        Main.switchScene("EmploiDuTemps.fxml");
    }

    @FXML private void AfficherStock(ActionEvent event) throws IOException {
        Main.switchScene("Stock.fxml");
    }

    @FXML private void AfficherLogging(ActionEvent event) throws IOException {
        Main.switchScene("Logging.fxml");
    }

    @FXML private void Deconnecter(ActionEvent event) throws IOException {
        Main.switchScene("Login.fxml");
    }

    @FXML private void openfiltrage(ActionEvent event) throws IOException {
        Stage popupWindow = Main.openPopupWindow2("FiltrageSalle.fxml", "filtrer", 550, 350);
        popupWindow.setX(40);
        popupWindow.setY(160);
    }

    // === Changement de thème ===

    @FXML
    private void changertheme() {
        Main.setDarkMode(ModeToggle.isSelected());

        applyCellFactories();
            }

    // === Classe interne modèle ===

    public static class caracteristique {
        private final SimpleStringProperty Element;
        private final SimpleStringProperty Typ;
        private final SimpleStringProperty Neuf;
        private final SimpleStringProperty Utilise;

        public caracteristique(String element, String type, String neuf, String utilise) {
            this.Element = new SimpleStringProperty(element);
            this.Typ = new SimpleStringProperty(type);
            this.Neuf = new SimpleStringProperty(neuf);
            this.Utilise = new SimpleStringProperty(utilise);
        }

        public String getElement() { return Element.get(); }
        public void setElement(String element) { this.Element.set(element); }

        public String getType() { return Typ.get(); }
        public void setType(String type) { this.Typ.set(type); }

        public String getNeuf() { return Neuf.get(); }
        public void setNeuf(String neuf) { this.Neuf.set(neuf); }

        public String getUtilise() { return Utilise.get(); }
        public void setUtilise(String utilise) { this.Utilise.set(utilise); }
    }
}