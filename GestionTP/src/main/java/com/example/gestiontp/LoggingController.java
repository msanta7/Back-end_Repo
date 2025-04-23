package com.example.gestiontp;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Stage;
import javafx.scene.Cursor;
import javafx.fxml.Initializable;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableView;
import javafx.collections.ListChangeListener;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoggingController implements Initializable {

    @FXML
    private ScrollPane mainScrollPane;

    @FXML
    private VBox contentVBox;

    // Table Logiciel
    @FXML private TableView<PanneLogiciel> tableLogiciel;
    @FXML private TableColumn<PanneLogiciel, String> dateColumn;
    @FXML private TableColumn<PanneLogiciel, String> salleColumn;
    @FXML private TableColumn<PanneLogiciel, String> detailColumn;
    @FXML private TableColumn<PanneLogiciel, String> degreColumn;
    @FXML private TableColumn<PanneLogiciel, String> maintenanceColumn;
    @FXML private TableColumn<PanneLogiciel, String> impressionColumnLogiciel;

    // Table Matériel
    @FXML private TableView<PanneMateriel> tableMateriel;
    @FXML private TableColumn<PanneMateriel, String> dateMateriel;
    @FXML private TableColumn<PanneMateriel, String> salleMateriel;
    @FXML private TableColumn<PanneMateriel, String> detailMateriel;
    @FXML private TableColumn<PanneMateriel, String> degreMateriel;
    @FXML private TableColumn<PanneMateriel, String> maintenanceMateriel;
    @FXML private TableColumn<PanneMateriel, String> impressionColumnmateriel;

    @FXML private ToggleButton ModeToggle;

    // Fixed cell height for calculations
    private static final double CELL_HEIGHT = 30.0;
    private static final double HEADER_HEIGHT = 25.0;
    private static final double TABLE_PADDING = 5.0;

    @FXML
    private void initialize() {
        // Force the content to be larger than the viewport to ensure scrolling works
        contentVBox.setMinHeight(1200);

        // Make sure the ScrollPane can scroll
        mainScrollPane.setFitToWidth(true);
        mainScrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        mainScrollPane.setPannable(true);

        // Set up table height adjustment when rows are added
        //tableLogiciel.setFixedCellSize(25);
        //tableMateriel.setFixedCellSize(25);

        // Add listeners to update table heights when rows change
        /*tableLogiciel.getItems().addListener((ListChangeListener<Object>) c -> {
            updateTableHeight(tableLogiciel);
        });

        tableMateriel.getItems().addListener((ListChangeListener<Object>) c -> {
            updateTableHeight(tableMateriel);
        });*/

        // Apply CSS to remove focus borders
        //String css = getClass().getResource("/path/to/style.css").toExternalForm();
        //Dashboard.getScene().getStylesheets().add(css);

        // Disable focus traversal for containers
        mainScrollPane.setFocusTraversable(false);
        contentVBox.setFocusTraversable(false);

        // Disable focus highlighting for tables
        //tableLogiciel.setFocusTraversable(false);
        //tableMateriel.setFocusTraversable(false);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initializeTableLogiciel();
        initializeTableMateriel();

        // Appliquer le style
        URL styleUrl = getClass().getResource("/com/example/gestiontp/Style.css");
        if (styleUrl != null) {
            String stylePath = styleUrl.toExternalForm();
            tableLogiciel.getStylesheets().add(stylePath);
            tableMateriel.getStylesheets().add(stylePath);
        } else {
            System.err.println("Could not load Style.css");
        }
    }

    private void initializeTableLogiciel() {
        // Association des colonnes
        dateColumn.setCellValueFactory(cell -> cell.getValue().date);
        salleColumn.setCellValueFactory(cell -> cell.getValue().salle);
        detailColumn.setCellValueFactory(cell -> cell.getValue().detail);
        degreColumn.setCellValueFactory(cell -> cell.getValue().degre);
        maintenanceColumn.setCellValueFactory(cell -> cell.getValue().maintenance);
        impressionColumnLogiciel.setCellValueFactory(cell -> cell.getValue().impression);

        // Édition
        tableLogiciel.setEditable(true);
        dateColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        salleColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        detailColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        degreColumn.setCellFactory(TextFieldTableCell.forTableColumn());

        // CheckBox pour maintenance
        maintenanceColumn.setCellFactory(col -> createCheckBoxCellLogiciel());

        // Hyperlink pour impression
        impressionColumnLogiciel.setCellFactory(col -> createHyperlinkCellLogiciel());

        // Sauvegarde des modifications
        dateColumn.setOnEditCommit(e -> e.getRowValue().setDate(e.getNewValue()));
        salleColumn.setOnEditCommit(e -> e.getRowValue().setSalle(e.getNewValue()));
        detailColumn.setOnEditCommit(e -> e.getRowValue().setDetail(e.getNewValue()));
        degreColumn.setOnEditCommit(e -> e.getRowValue().setDegre(e.getNewValue()));

        // Données initiales
        ObservableList<PanneLogiciel> data = FXCollections.observableArrayList(
                new PanneLogiciel("", "", "", "", "", "imprimer"),
                new PanneLogiciel("", "", "", "", "", "imprimer"),
                new PanneLogiciel("", "", "", "", "", "imprimer"),
                new PanneLogiciel("", "", "", "", "", "imprimer")
        );
        tableLogiciel.setItems(data);

        tableLogiciel.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        tableLogiciel.setFixedCellSize(CELL_HEIGHT);

        // Update table height based on number of rows
        updateTableHeight(tableLogiciel, data.size());
    }

    private void initializeTableMateriel() {
        dateMateriel.setCellValueFactory(cell -> cell.getValue().date);
        salleMateriel.setCellValueFactory(cell -> cell.getValue().salle);
        detailMateriel.setCellValueFactory(cell -> cell.getValue().detail);
        degreMateriel.setCellValueFactory(cell -> cell.getValue().degre);
        maintenanceMateriel.setCellValueFactory(cell -> cell.getValue().maintenance);
        impressionColumnmateriel.setCellValueFactory(cell -> cell.getValue().impression);

        tableMateriel.setEditable(true);
        dateMateriel.setCellFactory(TextFieldTableCell.forTableColumn());
        salleMateriel.setCellFactory(TextFieldTableCell.forTableColumn());
        detailMateriel.setCellFactory(TextFieldTableCell.forTableColumn());
        degreMateriel.setCellFactory(TextFieldTableCell.forTableColumn());

        maintenanceMateriel.setCellFactory(col -> createCheckBoxCellMateriel());

        // Hyperlink pour impression
        impressionColumnmateriel.setCellFactory(col -> createHyperlinkCellMateriel());

        dateMateriel.setOnEditCommit(e -> e.getRowValue().setDate(e.getNewValue()));
        salleMateriel.setOnEditCommit(e -> e.getRowValue().setSalle(e.getNewValue()));
        detailMateriel.setOnEditCommit(e -> e.getRowValue().setDetail(e.getNewValue()));
        degreMateriel.setOnEditCommit(e -> e.getRowValue().setDegre(e.getNewValue()));

        ObservableList<PanneMateriel> data = FXCollections.observableArrayList(
                new PanneMateriel("", "", "", "", "", "imprimer"),
                new PanneMateriel("", "", "", "", "", "imprimer"),
                new PanneMateriel("", "", "", "", "", "imprimer"),
                new PanneMateriel("", "", "", "", "", "imprimer")
        );
        tableMateriel.setItems(data);

        tableMateriel.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        tableMateriel.setFixedCellSize(CELL_HEIGHT);
        // Update table height based on number of rows
        updateTableHeight(tableMateriel, data.size());
    }

    /**
     * Creates a cell with a hyperlink for the Impression column in the Logiciel table
     */
    private TableCell<PanneLogiciel, String> createHyperlinkCellLogiciel() {
        return new TableCell<>() {
            private final Hyperlink hyperlink = new Hyperlink("imprimer");

            {
                // Style the hyperlink
                hyperlink.setStyle("-fx-underline: false;");
                hyperlink.setId("imprimer");


                hyperlink.setCursor(Cursor.HAND);

                // Add hover effect
                hyperlink.setOnMouseEntered(e -> hyperlink.setStyle("-fx-underline: true;"));
                hyperlink.setOnMouseExited(e -> hyperlink.setStyle("-fx-underline: false;"));

                // Set action (to be implemented by backend developer)
                hyperlink.setOnAction(e -> {
                    if (getIndex() < getTableView().getItems().size()) {
                        PanneLogiciel item = getTableView().getItems().get(getIndex());
                        // This will be implemented by the backend developer
                        System.out.println("Imprimer clicked for logiciel row: " + getIndex());
                    }
                });
            }

            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                } else {
                    setGraphic(hyperlink);
                }
            }
        };
    }

    /**
     * Creates a cell with a hyperlink for the Impression column in the Materiel table
     */
    private TableCell<PanneMateriel, String> createHyperlinkCellMateriel() {
        return new TableCell<>() {
            private final Hyperlink hyperlink = new Hyperlink("imprimer");

            {
                // Style the hyperlink
                hyperlink.setId("imprimer");
                hyperlink.setStyle("-fx-underline: false;");
                hyperlink.setCursor(Cursor.HAND);

                // Add hover effect
                hyperlink.setOnMouseEntered(e -> hyperlink.setStyle("-fx-underline: true;"));
                hyperlink.setOnMouseExited(e -> hyperlink.setStyle("-fx-underline: false;"));

                // Set action (to be implemented by backend developer)
                hyperlink.setOnAction(e -> {
                    if (getIndex() < getTableView().getItems().size()) {
                        PanneMateriel item = getTableView().getItems().get(getIndex());
                        // This will be implemented by the backend developer
                        System.out.println("Imprimer clicked for materiel row: " + getIndex());
                    }
                });
            }

            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                } else {
                    setGraphic(hyperlink);
                }
            }
        };
    }

    /**
     * Updates the height of a table based on its number of items
     */
    private <T> void updateTableHeight(TableView<T> tableView, int rowCount) {
        // Calculate height based on row count plus header
        double height = HEADER_HEIGHT + (CELL_HEIGHT * rowCount) + TABLE_PADDING;
        tableView.setPrefHeight(height);
        tableView.setMinHeight(height);
        tableView.setMaxHeight(height);
    }

    private TableCell<PanneLogiciel, String> createCheckBoxCellLogiciel() {
        return new TableCell<>() {
            private final CheckBox checkBox = new CheckBox();

            {
                checkBox.selectedProperty().addListener((obs, oldVal, newVal) -> {
                    if (getIndex() < getTableView().getItems().size()) {
                        PanneLogiciel item = getTableView().getItems().get(getIndex());
                        item.setMaintenance(newVal ? "✔" : "");
                    }
                });
            }

            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                setGraphic(empty ? null : checkBox);
                if (!empty) checkBox.setSelected("✔".equals(item));
            }
        };
    }

    private TableCell<PanneMateriel, String> createCheckBoxCellMateriel() {
        return new TableCell<>() {
            private final CheckBox checkBox = new CheckBox();

            {
                checkBox.selectedProperty().addListener((obs, oldVal, newVal) -> {
                    if (getIndex() < getTableView().getItems().size()) {
                        PanneMateriel item = getTableView().getItems().get(getIndex());
                        item.setMaintenance(newVal ? "✔" : "");
                    }
                });
            }

            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                setGraphic(empty ? null : checkBox);
                if (!empty) checkBox.setSelected("✔".equals(item));
            }
        };
    }

    private void updateTableHeight(TableView<?> table) {
        double rowHeight = table.getFixedCellSize();
        double headerHeight = 25; // Approximate header height
        int rowCount = table.getItems().size();

        // Calculate new height based on number of rows (plus header)
        double newHeight = headerHeight + (rowCount * rowHeight);

        // Set minimum height to ensure scrolling works
        table.setMinHeight(newHeight);
        table.setPrefHeight(newHeight);

        // Force layout recalculation
        table.requestLayout();

        // Update the content VBox height to ensure scrolling works
        double totalHeight = 50 + // Top padding
                30 + // JOURNAL label
                30 + // Spacing
                tableLogiciel.getPrefHeight() +
                30 + // Spacing
                tableMateriel.getPrefHeight() +
                50 + // Bottom padding
                100; // Extra space

        contentVBox.setMinHeight(Math.max(1200, totalHeight));
    }

    @FXML
    private void ajouterLigneLogiciel() {

        tableLogiciel.getItems().add(new PanneLogiciel("", "", "", "", "", "imprimer"));
        updateTableHeight(tableLogiciel, tableLogiciel.getItems().size());
    }

    @FXML
    private void ajouterLigneMateriel() {

        tableMateriel.getItems().add(new PanneMateriel("", "", "", "", "", "imprimer"));
        updateTableHeight(tableMateriel, tableMateriel.getItems().size());
    }




    private void showAlert(String content) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Suppression impossible");
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }

    // Navigation
    @FXML private void AfficherAcceuil(ActionEvent event) throws IOException { Main.switchScene("PageAcceuil.fxml"); }
    @FXML private void AfficherEmploiDuTemps(ActionEvent event) throws IOException { Main.switchScene("EmploiDuTemps.fxml"); }
    @FXML private void AfficherStock(ActionEvent event) throws IOException { Main.switchScene("Stock.fxml"); }
    @FXML private void AfficherLogging(ActionEvent event) throws IOException { Main.switchScene("Logging.fxml"); }
    @FXML private void Deconnecter(ActionEvent event) throws IOException { Main.switchScene("Login.fxml"); }

    @FXML private void changertheme() {
        Main.setDarkMode(ModeToggle.isSelected());
    }

    @FXML
    public void openfiltrage(ActionEvent event) throws IOException {
        Stage popupWindow = Main.openPopupWindow2("FiltrageSalle.fxml", "filtrer", 550, 350);
        popupWindow.setX(40);
        popupWindow.setY(160);
    }

    // Classes internes
    public static class PanneLogiciel {
        private final SimpleStringProperty date = new SimpleStringProperty();
        private final SimpleStringProperty salle = new SimpleStringProperty();
        private final SimpleStringProperty detail = new SimpleStringProperty();
        private final SimpleStringProperty degre = new SimpleStringProperty();
        private final SimpleStringProperty maintenance = new SimpleStringProperty();
        private final SimpleStringProperty impression = new SimpleStringProperty();

        public PanneLogiciel(String date, String salle, String detail, String degre, String maintenance, String impression) {
            this.date.set(date);
            this.salle.set(salle);
            this.detail.set(detail);
            this.degre.set(degre);
            this.maintenance.set(maintenance);
            this.impression.set(impression);
        }

        public String getDate() { return date.get(); }
        public void setDate(String val) { date.set(val); }

        public String getSalle() { return salle.get(); }
        public void setSalle(String val) { salle.set(val); }

        public String getDetail() { return detail.get(); }
        public void setDetail(String val) { detail.set(val); }

        public String getDegre() { return degre.get(); }
        public void setDegre(String val) { degre.set(val); }

        public String getMaintenance() { return maintenance.get(); }
        public void setMaintenance(String val) { maintenance.set(val); }

        public String getImpression() { return impression.get(); }
        public void setImpression(String val) { impression.set(val); }
    }

    public static class PanneMateriel {
        private final SimpleStringProperty date = new SimpleStringProperty();
        private final SimpleStringProperty salle = new SimpleStringProperty();
        private final SimpleStringProperty detail = new SimpleStringProperty();
        private final SimpleStringProperty degre = new SimpleStringProperty();
        private final SimpleStringProperty maintenance = new SimpleStringProperty();
        private final SimpleStringProperty impression = new SimpleStringProperty();

        public PanneMateriel(String date, String salle, String detail, String degre, String maintenance, String impression) {
            this.date.set(date);
            this.salle.set(salle);
            this.detail.set(detail);
            this.degre.set(degre);
            this.maintenance.set(maintenance);
            this.impression.set(impression);
        }

        public String getDate() { return date.get(); }
        public void setDate(String val) { date.set(val); }

        public String getSalle() { return salle.get(); }
        public void setSalle(String val) { salle.set(val); }

        public String getDetail() { return detail.get(); }
        public void setDetail(String val) { detail.set(val); }

        public String getDegre() { return degre.get(); }
        public void setDegre(String val) { degre.set(val); }

        public String getMaintenance() { return maintenance.get(); }
        public void setMaintenance(String val) { maintenance.set(val); }

        public String getImpression() { return impression.get(); }
        public void setImpression(String val) { impression.set(val); }
    }

    /*Back end : enregistrer les infos du tableau +
    l'impressions+
    l'affichage de la panne au niveau de la salle+
    la supression de la ligne quand on click sur le checkbox et la suppression de la panne au niveau de la salle
     */
}
