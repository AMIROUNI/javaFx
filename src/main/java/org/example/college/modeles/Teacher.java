package org.example.college.modeles;

import org.example.college.connexion.DAO;
import org.example.college.interfaces.CRUDInterface;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Teacher extends DAO implements CRUDInterface<Teacher> {

    private  int id_teacher;
    private String name;
    private  String prename;
    private  String  email;
    private  int numberPhone;


    public Teacher(String name, String prename, String email, int numberPhone) {
        this.name = name;

    }

    public Teacher(int id_teacher, String name, String prename, String email, int numberPhone) {
        this.id_teacher = id_teacher;
        this.name = name;
        this.prename = prename;
        this.email = email;
        this.numberPhone = numberPhone;

    }

    public Teacher() {}


    public int getId_teacher() {
        return id_teacher;
    }

    public void setId_teacher(int id_teacher) {
        this.id_teacher = id_teacher;
    }

    public String getName() {
        return name;
    }



    public void setName(String name) {
        this.name = name;
    }

    public String getPrename() {
        return prename;
    }

    public void setPrename(String prename) {
        this.prename = prename;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getNumberPhone() {
        return numberPhone;
    }

    public void setNumberPhone(int numberPhone) {
        this.numberPhone = numberPhone;
    }






    public void createTableTeacher() throws SQLException {
        try (Connection connection = connect_db()) {
            String sql = "CREATE TABLE IF NOT EXISTS Teacher (" +
                    "id_teacher INTEGER PRIMARY KEY AUTO_INCREMENT," +
                    "name TEXT," +
                    "prename TEXT," +
                    "email TEXT," +
                    "numberPhone INTEGER," +
                    "department_id INTEGER," + // Foreign key reference to department table
                    "FOREIGN KEY (department_id) REFERENCES Department(id_Department)" +
                    ");";
            connection.createStatement().execute(sql);
        }
    }


    @Override
    public void add() throws SQLException {
        String sql = "INSERT INTO Teacher (name, prename, email, numberPhone) VALUES (?, ?, ?, ?)";
        try (PreparedStatement ps = connect_db().prepareStatement(sql)) {
            ps.setString(1, this.getName());
            ps.setString(2, this.getPrename());
            ps.setString(3, this.getEmail());
            ps.setInt(4, this.getNumberPhone());
            ps.executeUpdate();
        }
    }

    @Override
    public ArrayList<Teacher> getAll() throws SQLException {
        String sql = "SELECT * FROM Teacher";
        ArrayList<Teacher> teachers = new ArrayList<>();
        try (ResultSet rs = connect_db().createStatement().executeQuery(sql)) {
            while (rs.next()) {
                int id = rs.getInt("id_teacher");
                String name = rs.getString("name");
                String prename = rs.getString("prename");
                String email = rs.getString("email");
                int numberPhone = rs.getInt("numberPhone");
                Teacher teacher = new Teacher(name, prename, email, numberPhone); // Department is not retrieved here
                teacher.setId_teacher(id); // Set the ID retrieved from the database
                teacher.setPrename(prename);
                teacher.setEmail(email);
                teacher.setNumberPhone(numberPhone);
                teachers.add(teacher);
            }
        }
        return teachers;
    }

    @Override
    public void update() throws SQLException {
        String sql = "UPDATE Teacher SET name = ?, prename = ?, email = ?, numberPhone = ? WHERE id_teacher = ?";
        try (PreparedStatement ps = connect_db().prepareStatement(sql)) {
            ps.setString(1, this.getName());
            ps.setString(2, this.getPrename());
            ps.setString(3, this.getEmail());
            ps.setInt(4, this.getNumberPhone());
            ps.setInt(5, this.getId_teacher());
            ps.executeUpdate();
        }
    }

    @Override
    public void delete() throws SQLException {
        String sql = "DELETE FROM Teacher WHERE id_teacher = ?";
        try (PreparedStatement ps = connect_db().prepareStatement(sql)) {
            ps.setInt(1, this.getId_teacher());
            ps.executeUpdate();
        }
    }



}
