module Noosphere {
    requires javafx.fxml;
    requires javafx.graphics;
    requires javafx.controls;
    requires java.sql;

    exports controllers to javafx.graphics;


    opens controllers to javafx.fxml;
    opens main to javafx.graphics;
}