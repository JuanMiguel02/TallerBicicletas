module org.demo {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;

    // Ikonli
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.ikonli.fontawesome6;
    requires org.kordamp.ikonli.antdesignicons;

    opens org.demo to javafx.fxml;
    opens org.demo.launcher to javafx.fxml;
    opens org.demo.controllers to javafx.fxml;

    exports org.demo.controllers;
    exports org.demo.models;
    exports org.demo.launcher;

}