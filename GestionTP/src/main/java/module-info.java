module com.example.gestiontp {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.bootstrapfx.core;
    requires java.net.http;
    requires java.desktop;
    requires java.sql;
    requires jbcrypt;

    opens com.example.gestiontp to javafx.fxml;
    exports com.example.gestiontp;
}