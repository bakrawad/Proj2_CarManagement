module com.example.proj2 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.proj2 to javafx.fxml;
    exports com.example.proj2;
    exports com.example.proj2.model;
    opens com.example.proj2.model to javafx.fxml;
    exports com.example.proj2.datastructure;
    opens com.example.proj2.datastructure to javafx.fxml;
    exports com.example.proj2.helpers;
    opens com.example.proj2.helpers to javafx.fxml;
}