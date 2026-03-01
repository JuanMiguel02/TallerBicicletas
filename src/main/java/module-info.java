module org.demo {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;

    // Ikonli
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.ikonli.fontawesome6;
    requires org.kordamp.ikonli.antdesignicons;
    requires javafx.base;
    requires org.demo;

    opens org.demo to javafx.fxml;
    opens org.demo.launcher to javafx.fxml;
    opens org.demo.viewController to javafx.fxml;

    exports org.demo.viewController;
    exports org.demo.models;
    exports org.demo.launcher;
    exports org.demo.viewController.VistasClientes;
    opens org.demo.viewController.VistasClientes to javafx.fxml;
    opens org.demo.viewController.VistasBicicletas to javafx.fxml;
    opens org.demo.viewController.VistasMecanicos to javafx.fxml;
    opens org.demo.viewController.VistaOrdenServicio to javafx.fxml;

}