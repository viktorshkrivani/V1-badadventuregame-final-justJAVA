module com.example.badadventuregame {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.badadventuregame to javafx.fxml;
    exports com.example.badadventuregame;
}