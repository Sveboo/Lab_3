module lab {
    requires javafx.controls;
    requires javafx.fxml;


    opens lab_3 to javafx.fxml;
    exports lab_3;
}