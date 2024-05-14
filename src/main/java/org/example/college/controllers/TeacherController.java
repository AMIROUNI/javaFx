package org.example.college.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import org.example.college.modeles.Teacher;

import java.sql.SQLException;
import java.util.ArrayList;

public class TeacherController {

    @FXML
    private Button btnAddTeacher;

    @FXML
    private TableColumn<?, ?> ColumnName;

    @FXML
    private Button btnGoToStudent;

    @FXML
    private Button btnDelete;

    @FXML
    private TableColumn<?, ?> ColmnEmail;

    @FXML
    private TableColumn<?, ?> CoulmnNumberPhone;

    @FXML
    private TableColumn<?, ?> ColumnPrename;

    @FXML
    private Button btnUpdate;

    @FXML
    private Button btnGoToDepartment;

    @FXML
    private Button btnGoToLogin;

    @FXML
    private Button btnGoToCourse;

    @FXML
    private TableView<Teacher> table;

    @FXML
    void GoToStudent(ActionEvent event) {

    }

    @FXML
    void GoToDepartment(ActionEvent event) {

    }

    @FXML
    void GoToCourse(ActionEvent event) {

    }

    @FXML
    void GoToLogin(ActionEvent event) {

    }

    @FXML
    void AddTeacher(ActionEvent event) {

    }

    @FXML
    void Upadate(ActionEvent event) {

    }

    @FXML
    void Delete(ActionEvent event) {

    }
    private void populateTable() {
        try {
            // Get all teachers from the database
            ArrayList<Teacher> teachers = new Teacher().getAll();

            // Convert the ArrayList to an ObservableList
            ObservableList<Teacher> observableTeachers = FXCollections.observableArrayList(teachers);

            // Set the table items
            table.setItems(observableTeachers);
        } catch (SQLException e) {
            e.printStackTrace();
            // Display an error message
        }
    }

}
