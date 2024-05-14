package org.example.college.modeles;

public abstract class SchoolDepartment {
    private String name;
    private int numOfStaff;

    public SchoolDepartment(String name, int numOfStaff) {
        this.name = name;
        this.numOfStaff = numOfStaff;
    }

    public String getName() {
        return name;
    }

    public int getNumOfStaff() {
        return numOfStaff;
    }

    public abstract void printDepartmentInfo();
}