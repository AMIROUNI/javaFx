package org.example.college.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;
import org.example.college.modeles.Student;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Objects;

public class LoginController {
    @FXML
    private TextField password;

    @FXML
    private Button btnLogin;

    @FXML
    private TextArea alert;

    @FXML
    private CheckBox admin;

    @FXML
    private Button signup;

    @FXML
    private TextField email;




    @FXML
    void signup(ActionEvent event) {

        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/org/example/college/Student_signup.fxml")));
            signup.getScene().setRoot(root);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    @FXML
    void login(ActionEvent event) throws SQLException {
        String userEmail = email.getText();
        String userPassword = password.getText();
        boolean isAdmin = admin.isSelected();

        // Perform authentication logic here, e.g., check against database

        // Dummy check: assuming admin credentials are "admin@example.com" and "admin123"
        String adminEmail = "admin@example.com";
        String adminPassword = "admin123";

        if (userEmail.equals(adminEmail) && userPassword.equals(adminPassword) && isAdmin) {
            try {
                Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/org/example/college/adminStudent.fxml")));
                btnLogin.getScene().setRoot(root);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        } else {
            Student s1=new Student(userEmail,userPassword);
           if( s1.studentIsExists()){
               try {
                   Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/org/example/college/studentPage.fxml")));
                   btnLogin.getScene().setRoot(root);
               } catch (IOException ex) {
                   throw new RuntimeException(ex);
               }

           }
           else {
                  alert.setText("that student in not exist plase sinqup");
           }
        }


        clearForm();
    }
    public void clearForm(){
        email.setText("");
        password.setText("");
        admin.setText("");

    }
}
