module com.example.thumbnailtags {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.thumbnailtags to javafx.fxml;
    exports com.example.thumbnailtags;
}