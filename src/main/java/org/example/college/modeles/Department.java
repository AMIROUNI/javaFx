package org.example.college.modeles;// Department.java
import javafx.beans.binding.BooleanExpression;
import org.example.college.connexion.DAO;
import org.example.college.interfaces.CRUDInterface;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Department extends DAO implements CRUDInterface<Department> {
    private int id_Department;
    private String name;
    private String location;

    public Department() {}

    public Department(String name, String location) {

        this.name = name;
        this.location = location;
    }
    public Department(int id_Department,String name, String location) {
        this.id_Department=id_Department;
        this.name = name;
        this.location = location;
    }

    public int getId_Department() {
        return id_Department;
    }

    public void setId_Department(int id_Department) {
        this.id_Department = id_Department;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Override
    public void add() throws SQLException {
        String sql = "INSERT INTO department (name, location) VALUES (?, ?)";
        PreparedStatement ps = connect_db().prepareStatement(sql);
        ps.setString(1, this.getName());
        ps.setString(2, this.getLocation());
        ps.executeUpdate();
    }



    @Override
    public ArrayList<Department> getAll() throws SQLException {
        String sql = "SELECT * FROM department";
        ResultSet rs = connect_db().createStatement().executeQuery(sql);
        ArrayList<Department> departments = new ArrayList<>();
        while (rs.next()) {
            int id = rs.getInt("id_Department");
            String name = rs.getString("name");
            String location = rs.getString("location");
            Department department = new Department(id, name, location);
            departments.add(department);
        }
        return departments;
    }

    @Override
    public void update() throws SQLException {
        String sql = "UPDATE department SET name = ?, location = ? WHERE id_Department = ?";
        PreparedStatement ps = connect_db().prepareStatement(sql);
        ps.setString(1, this.getName());
        ps.setString(2, this.getLocation());
        ps.setInt(3, this.getId_Department());
        ps.executeUpdate();
    }

    @Override
    public void delete() throws SQLException {
        String sql = "DELETE FROM department WHERE id_Department = ?";
        PreparedStatement ps = connect_db().prepareStatement(sql);
        ps.setInt(1,this.getId_Department());
        ps.executeUpdate();
    }



}
