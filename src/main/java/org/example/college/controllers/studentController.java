package org.example.college.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import org.example.college.modeles.Student;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Objects;

public class studentController {



    @FXML
    private TextArea ta_alert;

    @FXML
    private TextField ft_email;

    @FXML
    private TextField ft_age;

    @FXML
    private TextField ft_prename;

    @FXML
    private TextField tf_name;

    @FXML
    private TextField tf_password;

    @FXML
    private TextField ft_number_phone;

    @FXML
    private Button btnGoToLogin;

    @FXML
    private Button btnAddStudent;
    @FXML
    void AddStudent(ActionEvent event) {
        try {
            // Vérifier si tous les champs sont remplis
            if (tf_name.getText().isEmpty() || ft_prename.getText().isEmpty() ||
                    ft_age.getText().isEmpty() || ft_number_phone.getText().isEmpty() ||
                    ft_email.getText().isEmpty() || tf_password.getText().isEmpty()) {
                ta_alert.setText("Please fill in all fields");
                return;
            }

            // Vérifier si les champs contenant des nombres sont des entiers valides
            int age, numberPhone;
            try {
                age = Integer.parseInt(ft_age.getText());
                numberPhone = Integer.parseInt(ft_number_phone.getText());
            } catch (NumberFormatException e) {
                ta_alert.setText("Please enter valid numbers for age and phone");
                return;
            }

            // Créer l'étudiant en utilisant le nouveau constructeur
            Student s1 = new Student(tf_name.getText(),ft_prename.getText(),age,numberPhone,ft_email.getText(), tf_password.getText());
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

    private void clearForm() {
        tf_name.setText("");
        ft_prename.setText("");
        ft_age.setText("");
        ft_number_phone.setText("");
        ft_email.setText(" ");
        tf_password.setText(" ");

    }



    @FXML
    void GoToLoginNow(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/org/example/college/Login.fxml")));
            btnGoToLogin.getScene().setRoot(root);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

}
