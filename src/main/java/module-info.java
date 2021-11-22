module Noosphere {
    requires javafx.fxml;
    requires javafx.graphics;
    requires javafx.controls;

    exports controllers to javafx.graphics;


    opens controllers to javafx.fxml;
    opens main to javafx.graphics;
}