module io.github.mathieusoysal {
    requires javafx.controls;
    requires javafx.fxml;

    opens io.github.mathieusoysal to javafx.fxml;
    exports io.github.mathieusoysal;
}
