package org.example.college.modeles;

import org.example.college.connexion.DAO;
import org.example.college.interfaces.CRUDInterface;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Course extends DAO implements CRUDInterface<Course> {
    private String name;
    private Department department;
    private Teacher instructor;

    public Course() {
    }

    public Course(String name, Department department, Teacher instructor) {
        this.name = name;
        this.department = department;
        this.instructor = instructor;
    }

    public String getName() {
        return name;
    }

    public Department getDepartment() {
        return department;
    }

    public Teacher getInstructor() {
        return instructor;
    }

    @Override
    public void add() throws SQLException {
        String sql = "INSERT INTO COURSE (name, department, instructor) VALUES (?, ?, ?)";
        try (PreparedStatement ps = connect_db().prepareStatement(sql)) {
            ps.setString(1, this.getName());
            ps.setString(2, this.getDepartment().getName());
            ps.setString(3, this.getInstructor().getName());
            ps.executeUpdate();
        }
    }

    @Override
    public ArrayList<Course> getAll() throws SQLException {
        ArrayList<Course> courses = new ArrayList<>();
        String sql = "SELECT * FROM COURSE";
        try (ResultSet rs = connect_db().createStatement().executeQuery(sql)) {
            while (rs.next()) {

                String instructorName = rs.getString("instructor");
                String prename = rs.getString("prename");
                String email=rs.getString("email");
                int numberPhone=rs.getInt("numberPhone");


                Teacher instructor = new Teacher(instructorName, prename, email, numberPhone); // Assuming the teacher object requires only the name
                Course course = new Course(name, department, instructor);
                courses.add(course);
            }
        }
        return courses;
    }

    @Override
    public void update() throws SQLException {
        String sql = "UPDATE COURSE SET department = ?, instructor = ? WHERE name = ?";
        try (PreparedStatement ps = connect_db().prepareStatement(sql)) {
            ps.setString(1, this.getDepartment().getName());
            ps.setString(2, this.getInstructor().getName());
            ps.setString(3, this.getName());
            ps.executeUpdate();
        }
    }

    @Override
    public void delete() throws SQLException {
        String sql = "DELETE FROM COURSE WHERE name = ?";
        try (PreparedStatement ps = connect_db().prepareStatement(sql)) {
            ps.setString(1, this.getName());
            ps.executeUpdate();
        }
    }
}
