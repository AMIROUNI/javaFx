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
import org.example.college.modeles.Student;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.ResourceBundle;


public class AdminStudentController implements Initializable {
    @FXML
    private TextArea testta;

    @FXML
    private Button btnUpdate;
    @FXML
    private Button btnDelete;
    @FXML
    private TableView<Student> table;
    @FXML
    private TextArea ta_alert;

    @FXML
    private TextField tf_prename;

    @FXML
    private Button btnDepartment;

    @FXML
    private Button btnCourse;

    @FXML
    private TableColumn<?, ?> columnNumberPhone;

    @FXML
    private TableColumn<?, ?> columnEmail;

    @FXML
    private TableColumn<?, ?> columnPassword;

    @FXML
    private TextField tf_email;

    @FXML
    private Button btnTeacher;

    @FXML
    private TextField tf_age;

    @FXML
    private Button btnAddStudent;

    @FXML
    private TextField tf_name;

    @FXML
    private TableColumn<?, ?> columnPrename;

    @FXML
    private TextField tf_numberPhone;

    @FXML
    private TextField tf_password;

    @FXML
    private TableColumn<?, ?> columnage;

    @FXML
    private TableColumn<?, ?> columnName;

    @FXML
    void AddStundent(ActionEvent event) {
        try {
            // Vérifier si tous les champs sont remplis
            if (tf_name.getText().isEmpty() || tf_prename.getText().isEmpty() ||
                    tf_age.getText().isEmpty() || tf_numberPhone.getText().isEmpty() ||
                    tf_email.getText().isEmpty() || tf_password.getText().isEmpty()) {
                ta_alert.setText("Please fill in all fields");
                return;
            }

            // Vérifier si les champs contenant des nombres sont des entiers valides
            int age, numberPhone;

            age = Integer.parseInt(tf_age.getText());
            numberPhone = Integer.parseInt(tf_numberPhone.getText());


            // Créer l'étudiant en utilisant le nouveau constructeur
            Student s1 = new Student(tf_name.getText(), tf_prename.getText(), age, numberPhone, tf_email.getText(), tf_password.getText());
            s1.add();

            // Effacer le formulaire après l'ajout
            clearForm();
            ta_alert.setText("Data is inserted. Redirecting to login page...");
        } catch (SQLException e) {
            // Gérer les erreurs SQLException et IOException
            ta_alert.setText("Error: Failed to insert data into database or load login page.");
            e.printStackTrace(); // À des fins de débogage, imprimez la trace de la pile
        }
    }

    @FXML
    void GoToDepartment(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/org/example/college/Department.fxml")));
            btnDepartment.getScene().setRoot(root);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }


    @FXML
    void GoToTeacher(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/org/example/college/Teacher.fxml")));
            btnTeacher.getScene().setRoot(root);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    @FXML
    void GoToCourse(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/org/example/college/Teacher.fxml")));
        btnCourse.getScene().setRoot(root);
    }


    @FXML
    private void populateTable() {
        try {
            Student student = new Student();
            ArrayList<Student> dataList = student.getAll();
            ObservableList<Student> observabletable = FXCollections.observableArrayList(dataList);

            columnName.setCellValueFactory(new PropertyValueFactory<>("name"));
            columnPrename.setCellValueFactory(new PropertyValueFactory<>("prename"));
            columnage.setCellValueFactory(new PropertyValueFactory<>("age"));
            columnNumberPhone.setCellValueFactory(new PropertyValueFactory<>("numberPhone"));
            columnEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
            columnPassword.setCellValueFactory(new PropertyValueFactory<>("password"));
            testta.setText(String.valueOf(new PropertyValueFactory<>("name")));

            table.setItems(observabletable);
        } catch (SQLException e) {
            ta_alert.setText("Error: Failed to populate table.");
            e.printStackTrace();
        }
    }


    private void clearForm() {
        tf_name.setText("");
        tf_prename.setText("");
        tf_age.setText("");
        tf_numberPhone.setText("");
        tf_email.setText(" ");
        tf_password.setText(" ");

    }


    @Override

    public void initialize(URL url, ResourceBundle resourceBundle) {
        populateTable(); // Call populateTable() method here to populate the table with student data

        table.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                // Update other fields based on the selected student if needed
                tf_name.setText(newSelection.getName());
                tf_prename.setText(newSelection.getPrename());
                tf_age.setText(String.valueOf(newSelection.getAge()));
                tf_numberPhone.setText(String.valueOf(newSelection.getNumberPhone()));
                tf_email.setText(newSelection.getEmail());
                tf_password.setText(newSelection.getPassword());
            }
        });
    }


    @FXML
    void Update(ActionEvent event) throws SQLException {
        // Get the data from the text fields
        String name = tf_name.getText();
        String prename = tf_prename.getText();
        int age = Integer.parseInt(tf_age.getText());
        int numberPhone = Integer.parseInt(tf_numberPhone.getText());
        String email = tf_email.getText();
        String password = tf_password.getText();

        // Create a new Student object with the updated information
        Student updatedStudent = new Student(name, prename, age, numberPhone, email, password);
        System.out.println(updatedStudent);

        // Update the student's information in the database
        updatedStudent.update();

        // Display a message to indicate that the update was successful
        ta_alert.setText("Student information updated successfully.");

        // Refresh the table to reflect the updated information
        populateTable();
    }


    @FXML
    void Delete(ActionEvent event) throws SQLException {
        // Get email and password from the text fields
        String email = tf_email.getText();
        String password = tf_password.getText();

        // Create a new Student object with email and password
        Student s1 = new Student(email, password);

        // Check if the student exists in the database
        if (s1.studentIsExists()) {
            // Delete the student from the database
            s1.delete();
            ta_alert.setText("Student deleted successfully.");
            populateTable(); // Refresh the table after deleting
            clearForm(); // Clear the form fields after deleting
        } else {
            ta_alert.setText("This student does not exist.");
        }
    }
}
