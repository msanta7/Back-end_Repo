package com.example.gestiontp;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class EmploiDuTempsController implements Initializable {

    @FXML private HBox EmploiDuTempsInterf;
    @FXML private Button Filtrer;

    // Menu links
    @FXML private Hyperlink acceuilLink;
    @FXML private Hyperlink emploidutempsLink;
    @FXML private Hyperlink stockLink;
    @FXML private Hyperlink loggingLink;
    @FXML private Hyperlink deconnecterLink;
    @FXML private ToggleButton ModeToggle;
    @FXML private Label Mode;

    // Room groups
    @FXML private VBox A21Grp, A22Grp, A23Grp, A24Grp, A25Grp;
    @FXML private VBox A31Grp, A32Grp, A33Grp, A34Grp, UNIXGrp;
    @FXML private VBox A41Grp, A42Grp, A43Grp;

    // Room titles
    @FXML private Text A21Edt, A22Edt, A23Edt, A24Edt, A25Edt;
    @FXML private Text A31Edt, A32Edt, A33Edt, A34Edt, UNIXEdt;
    @FXML private Text A41Edt, A42Edt, A43Edt;

    // TableViews
    @FXML private TableView<ScheduleRow> timeTableView21, timeTableView22, timeTableView23, timeTableView24, timeTableView25;
    @FXML private TableView<ScheduleRow> timeTableView31, timeTableView32, timeTableView33, timeTableView34, timeTableViewUNIX;
    @FXML private TableView<ScheduleRow> timeTableView41, timeTableView42, timeTableView43;

    // Upload buttons
    @FXML private Button A21Button, A22Button, A23Button, A24Button, A25Button;
    @FXML private Button A31Button, A32Button, A33Button, A34Button, UNIXButton;
    @FXML private Button A41Button, A42Button, A43Button;

    // Data model for schedule rows
    public static class ScheduleRow {
        private final SimpleStringProperty day;
        private final SimpleStringProperty slot1;
        private final SimpleStringProperty slot2;
        private final SimpleStringProperty slot3;
        private final SimpleStringProperty slot4;
        private final SimpleStringProperty slot5;
        private final SimpleStringProperty slot6;

        public ScheduleRow(String day, String slot1, String slot2, String slot3, String slot4, String slot5, String slot6) {
            this.day = new SimpleStringProperty(day);
            this.slot1 = new SimpleStringProperty(slot1);
            this.slot2 = new SimpleStringProperty(slot2);
            this.slot3 = new SimpleStringProperty(slot3);
            this.slot4 = new SimpleStringProperty(slot4);
            this.slot5 = new SimpleStringProperty(slot5);
            this.slot6 = new SimpleStringProperty(slot6);
        }

        public String getDay() { return day.get(); }
        public void setDay(String value) { day.set(value); }
        public SimpleStringProperty dayProperty() { return day; }

        public String getSlot1() { return slot1.get(); }
        public void setSlot1(String value) { slot1.set(value); }
        public SimpleStringProperty slot1Property() { return slot1; }

        public String getSlot2() { return slot2.get(); }
        public void setSlot2(String value) { slot2.set(value); }
        public SimpleStringProperty slot2Property() { return slot2; }

        public String getSlot3() { return slot3.get(); }
        public void setSlot3(String value) { slot3.set(value); }
        public SimpleStringProperty slot3Property() { return slot3; }

        public String getSlot4() { return slot4.get(); }
        public void setSlot4(String value) { slot4.set(value); }
        public SimpleStringProperty slot4Property() { return slot4; }

        public String getSlot5() { return slot5.get(); }
        public void setSlot5(String value) { slot5.set(value); }
        public SimpleStringProperty slot5Property() { return slot5; }

        public String getSlot6() { return slot6.get(); }
        public void setSlot6(String value) { slot6.set(value); }
        public SimpleStringProperty slot6Property() { return slot6; }
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Initialize all tables with sample data
        initializeAllTables();
        showAllRooms();

        // Set up the visibility of room groups (initially only show A21)
    }

    private void initializeAllTables() {
        // Initialize each table with columns and sample data
        initializeTable(timeTableView21);
        initializeTable(timeTableView22);
        initializeTable(timeTableView23);
        initializeTable(timeTableView24);
        initializeTable(timeTableView25);
        initializeTable(timeTableView31);
        initializeTable(timeTableView32);
        initializeTable(timeTableView33);
        initializeTable(timeTableView34);
        initializeTable(timeTableViewUNIX);
        initializeTable(timeTableView41);
        initializeTable(timeTableView42);
        initializeTable(timeTableView43);
    }

    private void initializeTable(TableView<ScheduleRow> tableView) {
        tableView.setEditable(true);

        TableColumn<ScheduleRow, String> dayColumn = (TableColumn<ScheduleRow, String>) tableView.getColumns().get(0);
        dayColumn.setCellValueFactory(cellData -> cellData.getValue().dayProperty());
        dayColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        dayColumn.setOnEditCommit(event -> event.getRowValue().setDay(event.getNewValue()));

        TableColumn<ScheduleRow, String> slot1Column = (TableColumn<ScheduleRow, String>) tableView.getColumns().get(1);
        slot1Column.setCellValueFactory(cellData -> cellData.getValue().slot1Property());
        slot1Column.setCellFactory(TextFieldTableCell.forTableColumn());
        slot1Column.setOnEditCommit(event -> event.getRowValue().setSlot1(event.getNewValue()));

        TableColumn<ScheduleRow, String> slot2Column = (TableColumn<ScheduleRow, String>) tableView.getColumns().get(2);
        slot2Column.setCellValueFactory(cellData -> cellData.getValue().slot2Property());
        slot2Column.setCellFactory(TextFieldTableCell.forTableColumn());
        slot2Column.setOnEditCommit(event -> event.getRowValue().setSlot2(event.getNewValue()));

        TableColumn<ScheduleRow, String> slot3Column = (TableColumn<ScheduleRow, String>) tableView.getColumns().get(3);
        slot3Column.setCellValueFactory(cellData -> cellData.getValue().slot3Property());
        slot3Column.setCellFactory(TextFieldTableCell.forTableColumn());
        slot3Column.setOnEditCommit(event -> event.getRowValue().setSlot3(event.getNewValue()));

        TableColumn<ScheduleRow, String> slot4Column = (TableColumn<ScheduleRow, String>) tableView.getColumns().get(4);
        slot4Column.setCellValueFactory(cellData -> cellData.getValue().slot4Property());
        slot4Column.setCellFactory(TextFieldTableCell.forTableColumn());
        slot4Column.setOnEditCommit(event -> event.getRowValue().setSlot4(event.getNewValue()));

        TableColumn<ScheduleRow, String> slot5Column = (TableColumn<ScheduleRow, String>) tableView.getColumns().get(5);
        slot5Column.setCellValueFactory(cellData -> cellData.getValue().slot5Property());
        slot5Column.setCellFactory(TextFieldTableCell.forTableColumn());
        slot5Column.setOnEditCommit(event -> event.getRowValue().setSlot5(event.getNewValue()));

        TableColumn<ScheduleRow, String> slot6Column = (TableColumn<ScheduleRow, String>) tableView.getColumns().get(6);
        slot6Column.setCellValueFactory(cellData -> cellData.getValue().slot6Property());
        slot6Column.setCellFactory(TextFieldTableCell.forTableColumn());
        slot6Column.setOnEditCommit(event -> event.getRowValue().setSlot6(event.getNewValue()));

        ObservableList<ScheduleRow> data = FXCollections.observableArrayList(
                new ScheduleRow("SAMEDI", "", "", "", "", "", ""),
                new ScheduleRow("DIMANCHE", "", "", "", "", "", ""),
                new ScheduleRow("LUNDI", "", "", "", "", "", ""),
                new ScheduleRow("MARDI", "", "", "", "", "", ""),
                new ScheduleRow("MERCREDI", "", "", "", "", "", ""),
                new ScheduleRow("JEUDI", "", "", "", "", "", "")
        );


        tableView.setItems(data);

    }



    // Navigation methods
    @FXML
    private void AfficherAcceuil(javafx.event.ActionEvent event) throws IOException {
        Main.switchScene("PageAcceuil.fxml");
    }

    @FXML
    private void AfficherEmploiDuTemps(javafx.event.ActionEvent event) throws IOException {
        Main.switchScene("EmploiDuTemps.fxml");
    }

    @FXML
    private void AfficherStock(javafx.event.ActionEvent event) throws IOException {
        Main.switchScene("Stock.fxml");
    }

    @FXML
    private void AfficherLogging(javafx.event.ActionEvent event) throws IOException {
        Main.switchScene("Logging.fxml");
    }

    @FXML
    private void Deconnecter(javafx.event.ActionEvent event) throws IOException {
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

    // Upload button handlers
    @FXML
    private void handleUploadA21(ActionEvent event) {
        // Implement upload functionality for A21
        showAlert("Upload", "Uploading schedule for Room A21");
    }

    @FXML
    private void handleUploadA22(ActionEvent event) {
        // Implement upload functionality for A22
        showAlert("Upload", "Uploading schedule for Room A22");
    }

    @FXML
    private void handleUploadA23(ActionEvent event) {
        // Implement upload functionality for A22
        showAlert("Upload", "Uploading schedule for Room A23");
    }


    @FXML
    private void handleUploadA24(ActionEvent event) {
        // Implement upload functionality for A22
        showAlert("Upload", "Uploading schedule for Room A24");
    }

    @FXML
    private void handleUploadA25(ActionEvent event) {
        // Implement upload functionality for A22
        showAlert("Upload", "Uploading schedule for Room A25");
    }

    @FXML
    private void handleUploadA31(ActionEvent event) {
        // Implement upload functionality for A22
        showAlert("Upload", "Uploading schedule for Room A31");
    }

    @FXML
    private void handleUploadA32(ActionEvent event) {
        // Implement upload functionality for A22
        showAlert("Upload", "Uploading schedule for Room A32");
    }


    @FXML
    private void handleUploadA33(ActionEvent event) {
        // Implement upload functionality for A22
        showAlert("Upload", "Uploading schedule for Room A33");
    }

    @FXML
    private void handleUploadA34(ActionEvent event) {
        // Implement upload functionality for A22
        showAlert("Upload", "Uploading schedule for Room A34");
    }


    @FXML
    private void handleUploadAUNIX(ActionEvent event) {
        // Implement upload functionality for A22
        showAlert("Upload", "Uploading schedule for Room UNIX");
    }

    @FXML
    private void handleUploadA41(ActionEvent event) {
        // Implement upload functionality for A22
        showAlert("Upload", "Uploading schedule for Room 41");
    }


    @FXML
    private void handleUploadA42(ActionEvent event) {
        // Implement upload functionality for A22
        showAlert("Upload", "Uploading schedule for Room A42");
    }


    @FXML
    private void handleUploadA43(ActionEvent event) {
        // Implement upload functionality for A22
        showAlert("Upload", "Uploading schedule for Room A43");
    }


    // Add similar methods for other rooms...

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void showAllRooms() {
        A21Grp.setVisible(true);
        A22Grp.setVisible(true);
        A23Grp.setVisible(true);
        A24Grp.setVisible(true);
        A25Grp.setVisible(true);
        A31Grp.setVisible(true);
        A32Grp.setVisible(true);
        A33Grp.setVisible(true);
        A34Grp.setVisible(true);
        UNIXGrp.setVisible(true);
        A41Grp.setVisible(true);
        A42Grp.setVisible(true);
        A43Grp.setVisible(true);
    }


}