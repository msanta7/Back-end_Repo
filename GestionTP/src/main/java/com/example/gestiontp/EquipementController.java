package com.example.gestiontp;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class EquipementController implements Initializable {

    @FXML private TableView<Equipement> EquipementTable;
    @FXML private TableColumn<Equipement, String> codeColumn;
    @FXML private TableColumn<Equipement, String> marqueColumn;
    @FXML private TableColumn<Equipement, String> ramColumn;
    @FXML private TableColumn<Equipement, String> seColumn;
    @FXML private TableColumn<Equipement, String> disqueColumn;
    @FXML private TableColumn<Equipement, String> cpuColumn;

    private final ObservableList<Equipement> equipementList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Initialize table columns with cell value factories
        codeColumn.setCellValueFactory(cellData -> cellData.getValue().codeProperty());
        marqueColumn.setCellValueFactory(cellData -> cellData.getValue().marqueProperty());
        ramColumn.setCellValueFactory(cellData -> cellData.getValue().ramProperty());
        seColumn.setCellValueFactory(cellData -> cellData.getValue().seProperty());
        disqueColumn.setCellValueFactory(cellData -> cellData.getValue().disqueProperty());
        cpuColumn.setCellValueFactory(cellData -> cellData.getValue().cpuProperty());

        // Enable editing with TextFieldTableCell
        codeColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        marqueColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        ramColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        seColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        disqueColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        cpuColumn.setCellFactory(TextFieldTableCell.forTableColumn());

        // Handle edit commit events
        codeColumn.setOnEditCommit(event -> event.getRowValue().setCode(event.getNewValue()));
        marqueColumn.setOnEditCommit(event -> event.getRowValue().setMarque(event.getNewValue()));
        ramColumn.setOnEditCommit(event -> event.getRowValue().setRam(event.getNewValue()));
        seColumn.setOnEditCommit(event -> event.getRowValue().setSe(event.getNewValue()));
        disqueColumn.setOnEditCommit(event -> event.getRowValue().setDisque(event.getNewValue()));
        cpuColumn.setOnEditCommit(event -> event.getRowValue().setCpu(event.getNewValue()));

        // Add initial empty rows
        for (int i = 0; i < 15; i++) {
            equipementList.add(new Equipement());
        }

        EquipementTable.setItems(equipementList);
        EquipementTable.setEditable(true);
    }

    @FXML
    private void handleAddRow() {
        equipementList.add(new Equipement());
    }

    @FXML
    private void closewindow(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }

    @FXML
    public void handleDeleteRow(ActionEvent event) {
        Equipement selected = EquipementTable.getSelectionModel().getSelectedItem();
        if (selected != null) {
            equipementList.remove(selected);
        }
    }

    /*+ Back end enregistrer les informations entrees et lier a la bdd*/
}