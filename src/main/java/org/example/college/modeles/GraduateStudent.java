package org.example.college.modeles;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GraduateStudent extends org.example.college.modeles.Student {
    private String thesisTopic;

    public GraduateStudent(String name,String prename,int age,int numberPhone,String email,String password, String thesisTopic) {
        super(name,prename,age,numberPhone,email,password);
        this.thesisTopic = thesisTopic;
    }

    public void setThesisTopic(String thesisTopic) {
        this.thesisTopic = thesisTopic;
    }

    public String getThesisTopic() {
        return thesisTopic;
    }



    public void addGraduateStudent() throws SQLException {
        String sql = "INSERT INTO GraduateStudent (name, prename, age, numberPhone, email,thesisTopic) VALUES (?, ?, ?, ?, ?,?)";
        PreparedStatement ps = connect_db().prepareStatement(sql);
        ps.setString(1, this.getName());
        ps.setString(2, this.getPrename());
        ps.setInt(3, this.getAge());
        ps.setInt(4, this.getNumberPhone());
        ps.setString(5, this.getEmail());
        ps.setString(6,this.getThesisTopic());
        ps.executeUpdate();
    }
    public void editGraduateStudent(int id, String newName, String newPrename, int newAge, int newNumberPhone, String newEmail) throws SQLException {
        String sql = "UPDATE graduateStudent SET name = ?, prename = ?, age = ?, numberPhone = ?, email = ? WHERE name = ?";
        PreparedStatement ps = connect_db().prepareStatement(sql);
        ps.setString(1, newName);
        ps.setString(2, newPrename);
        ps.setInt(3, newAge);
        ps.setInt(4, newNumberPhone);
        ps.setString(5, newEmail);
        ps.setInt(6, id);
        ps.executeUpdate();
    }

    public void deleteStudent(int id) throws SQLException {
        String sql = "DELETE FROM GraduateStudent WHERE name = ?";
        PreparedStatement ps = connect_db().prepareStatement(sql);
        ps.setInt(1, id);
        ps.executeUpdate();
    }

    public void displayStudents() throws SQLException {
        String sql = "SELECT * FROM STUDENT";
        ResultSet rs = connect_db().createStatement().executeQuery(sql);
        while (rs.next()) {
            String name = rs.getString("name");
            String prename = rs.getString("prename");
            int age = rs.getInt("age");
            int numberPhone = rs.getInt("numberPhone");
            String email = rs.getString("email");
            String thesisTopic=rs.getString("thesisTpic");
            System.out.println("Name: " + name + ", Prename: " + prename + ", Age: " + age + ", Phone: " + numberPhone + ", Email: " + email);
        }
    }

    public void createTableStudent() throws SQLException {
        try (Connection connection = connect_db()) {
            String sql = "CREATE TABLE IF NOT EXISTS student (" +
                    "id INTEGER PRIMARY KEY AUTO_INCREMENT," +
                    "name TEXT," +
                    "prename TEXT," +
                    "age INTEGER," +
                    "numberPhone TEXT," +
                    "email TEXT" +
                    "thesisTpic TEXT"+
                    ")";
            connection.createStatement().execute(sql);
        }
    }


}
