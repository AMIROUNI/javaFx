module org.example.college {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires java.sql;
    exports org.example.college.controllers;
    opens org.example.college to javafx.fxml;
    opens org.example.college.controllers to javafx.fxml;
    exports org.example.college;
}