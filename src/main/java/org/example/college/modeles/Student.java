package org.example.college.modeles;

import org.example.college.connexion.DAO;
import org.example.college.interfaces.CRUDInterface;


import java.sql.*;
import java.util.ArrayList;

import static java.sql.DriverManager.getConnection;

public class Student extends DAO implements CRUDInterface<Student> {
    static private int id;
    private String name;
    private String prename;

    private int age;
    private int numberPhone;
    private String email;
    private String password;


    //private static int nextId = 1000;
    private static final String SCHOOL_NAME = "XYZ School";
    private Course enrolledCourse; // Relationship: Composition

   /* public org.example.college.modeles.Student(String name) {
        this.name = name;
        this.id = nextId++;
    }*/


    public Student(String name, String prename, int age, int numberPhone, String email, String password) {
        this.name = name;
        this.prename = prename;
        this.age = age;
        this.numberPhone = numberPhone;
        this.email = email;
        this.password=password;
    }

    public Student(String email,String password){
        this.email=email;
        this.password=password;
    }

    public Student() {
    }

    public static int getId() {
        return id;
    }

    public static void setId(int id) {
        Student.id = id;
    }

    public String getName() {
        return name;
    }


    public static String getSchoolName() {
        return SCHOOL_NAME;
    }

    public void setEnrolledCourse(Course enrolledCourse) {
        this.enrolledCourse = enrolledCourse;
    }

    public Course getEnrolledCourse() {
        return enrolledCourse;
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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getNumberPhone() {
        return numberPhone;
    }

    public void setNumberPhone(int numberPhone) {
        this.numberPhone = numberPhone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void enrollInCourse(Course course) {
        this.enrolledCourse = course;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    //add student in data base

    public void displayStudent() throws SQLException {
        String sql = "SELECT * FROM student";
        ResultSet rs = connect_db().createStatement().executeQuery(sql);
        while (rs.next()) {
            int id = rs.getInt("id");
            String name = rs.getString("name");
            String prename = rs.getString("prename");
            String email = rs.getString("email");
            int numberPhone = rs.getInt("numberPhone");
            System.out.println("ID: " + id + ", Name: " + name + ", Prename: " + prename + ",Age : " + age + ", Email: " + email + ", Phone: " + numberPhone);
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
                    ")";
            connection.createStatement().execute(sql);
        }
    }

    @Override
    public void add() throws SQLException {
        String sql = "INSERT INTO student (name, prename, age, numberPhone, email,password) VALUES (?, ?, ?, ?, ?,?)";
        PreparedStatement ps = connect_db().prepareStatement(sql);
        ps.setString(1, this.getName());
        ps.setString(2, this.getPrename());
        ps.setInt(3, this.getAge());
        ps.setInt(4, this.getNumberPhone());
        ps.setString(5, this.getEmail());
        ps.setString(6, this.getPassword());
        ps.executeUpdate();
    }
    @Override
    public ArrayList<Student> getAll() throws SQLException {
        String sql = "SELECT * FROM student";
        Statement statement = connect_db().createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        ArrayList<Student> studentsList = new ArrayList<>();
        while (resultSet.next()) {
            Student student = new Student();
            student.setId(resultSet.getInt("id"));
            student.setName(resultSet.getString("name"));
            student.setPrename(resultSet.getString("prename"));
            student.setAge(resultSet.getInt("age"));
            student.setNumberPhone(resultSet.getInt("numberPhone"));
            student.setEmail(resultSet.getString("email"));
            student.setPassword(resultSet.getString("password"));
            studentsList.add(student);
        }
        return studentsList;
    }

    @Override
    public void update() throws SQLException {
        String sql = "UPDATE student SET name = ?, prename = ?, age = ?, numberPhone = ?, email = ?,password=? WHERE email = ? AND password= ?";
        PreparedStatement ps = connect_db().prepareStatement(sql);
        ps.setString(1, this.name);
        ps.setString(2, this.prename);
        ps.setInt(3, this.age);
        ps.setInt(4, this.numberPhone);
        ps.setString(5, this.getEmail());
        ps.setString(6, this.password);
        ps.setString(7, String.valueOf(this.email));
        ps.setString(8, String.valueOf(this.password));

        ps.executeUpdate();
    }

    @Override
    public void delete() throws SQLException {
        String sql = "DELETE FROM student WHERE email = ? AND password =?";
        PreparedStatement ps = connect_db().prepareStatement(sql);
        ps.setString(1, this.email);
        ps.setString(2,this.password);
        ps.executeUpdate();
    }


    public   boolean studentIsExists() throws SQLException {
        String query = "SELECT COUNT(*) FROM student WHERE password = ?AND email = ?";
        try (Connection connection =connect_db();
             PreparedStatement statement = connection.prepareStatement(query)) {
            // Set parameters for the query

            statement.setString(2, this.getEmail());
            statement.setString(1,this.getPassword());

            // Execute the query
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    int count = resultSet.getInt(1);
                    return count > 0; // If count > 0, student exists
                }
            }
        }
        return false; // Default to false if the query doesn't return a result
    }
        }

