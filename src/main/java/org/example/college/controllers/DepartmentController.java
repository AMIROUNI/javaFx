package org.example.college.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import org.example.college.modeles.Department;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.ResourceBundle;

public class DepartmentController implements Initializable {
    @FXML
    private Button btnGoToCourse;
    @FXML
    private Button btnGoToSudent;
    @FXML
    private Button btnGoToLogin;

    @FXML
    private Button btnGoToTeacher;

    @FXML
    private TableView<Department> tabledep;

    @FXML
    private TextField tfLocation;

    @FXML
    private TextField tfNameDepartment;

    @FXML
    private Button btnDelete;

    @FXML
    private TextArea alertAddDepartment;

    @FXML
    private Button btnUpdate;

    @FXML
    private TableColumn<Department, Integer> id;

    @FXML
    private TableColumn<Department, String> namedep;

    @FXML
    private TableColumn<Department, String> locationColumn;

    @FXML
    private Button btnAddDepartemnt;








    @FXML
    void GoToStudnt(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/org/example/college/adminStudent.fxml")));
            btnGoToLogin.getScene().setRoot(root);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }


    @FXML
    void GoToTeacher(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/org/example/college/Teacher.fxml")));
            btnGoToLogin.getScene().setRoot(root);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    @FXML
    void GoToCourse(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/org/example/college/Course.fxml")));
            btnGoToLogin.getScene().setRoot(root);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    @FXML
    void GoToLogin(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/org/example/college/Login.fxml")));
            btnGoToLogin.getScene().setRoot(root);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    @FXML
    void update(ActionEvent event) {
        try {
            Department selectedDepartment = tabledep.getSelectionModel().getSelectedItem();
            if (selectedDepartment != null) {
                selectedDepartment.setName(tfNameDepartment.getText());
                selectedDepartment.setLocation(tfLocation.getText());
                selectedDepartment.update();
                alertAddDepartment.setText("Department information updated successfully.");
                populateTable();
            } else {
                alertAddDepartment.setText("Please select a department from the table.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            alertAddDepartment.setText("Error: Failed to update department information.");
        }
    }

    @FXML
    void Delete(ActionEvent event) {
        try {
            Department selectedDepartment = tabledep.getSelectionModel().getSelectedItem();
            if (selectedDepartment != null) {
                selectedDepartment.delete();
                alertAddDepartment.setText("Department deleted successfully.");
                populateTable();
            } else {
                alertAddDepartment.setText("Please select a department from the table.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            alertAddDepartment.setText("Error: Failed to delete department.");
        }
    }

    @FXML
    void addDepartment(ActionEvent event) {
        try {
            String name = tfNameDepartment.getText();
            String location = tfLocation.getText();
            Department department = new Department(name, location);
            department.add();
            alertAddDepartment.setText("Department added successfully.");
            populateTable();
        } catch (SQLException e) {
            e.printStackTrace();
            alertAddDepartment.setText("Error: Failed to add department.");
        }
    }

    @FXML
    private void populateTable() {
        try {
            Department department = new Department();
            ArrayList<Department> dataList = department.getAll();
            ObservableList<Department> observabletable = FXCollections.observableArrayList(dataList);

            id.setCellValueFactory(new PropertyValueFactory<>("id_Department"));
            namedep.setCellValueFactory(new PropertyValueFactory<>("name"));
            locationColumn.setCellValueFactory(new PropertyValueFactory<>("location"));

            tabledep.setItems(observabletable);
        } catch (SQLException e) {
            alertAddDepartment.setText("Error: Failed to populate table.");
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        populateTable();

        tabledep.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                tfNameDepartment.setText(newSelection.getName());
                tfLocation.setText(newSelection.getLocation());
            }
        });
    }
}