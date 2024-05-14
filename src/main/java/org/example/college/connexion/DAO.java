package org.example.college.connexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DAO {

    public Connection connect_db() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/collegedb";
        String user = "root";
        String password = "";
        return DriverManager.getConnection(url, user, password);


    }







}

