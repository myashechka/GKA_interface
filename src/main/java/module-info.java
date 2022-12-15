module com.example.gka_interface {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.apache.httpcomponents.httpclient;
    requires org.apache.httpcomponents.httpcore;
    requires lombok;
    requires com.google.gson;


    opens com.example.gka_interface to javafx.fxml;
    exports com.example.gka_interface;
    exports models;
    exports services;
    exports currentUser;
}