module tpclasses.tp2 {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;

    opens tpclasses.tp2 to javafx.fxml;
    exports tpclasses.tp2;
}