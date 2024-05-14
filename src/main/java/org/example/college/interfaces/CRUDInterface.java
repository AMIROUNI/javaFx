package org.example.college.interfaces;

import java.sql.SQLException;
import java.util.ArrayList;

public interface CRUDInterface<T> {

    void add() throws SQLException;
    ArrayList<T> getAll() throws SQLException;
    void update () throws SQLException;
    void delete () throws SQLException;
}
