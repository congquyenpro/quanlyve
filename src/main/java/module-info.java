module com.example.quanlive {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.quanlive to javafx.fxml;
    exports com.example.quanlive;
    exports com.example.quanlive.search;
    opens com.example.quanlive.search to javafx.fxml;
}