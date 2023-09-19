module com.example.jason_fleming_assignment_2 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.jason_fleming_assignment_2 to javafx.fxml;
    exports com.example.jason_fleming_assignment_2;
}