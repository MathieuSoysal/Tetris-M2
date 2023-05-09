package io.github.mathieusoysal;

import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.StrokeType;
import javafx.scene.text.Text;

public class Popup extends StackPane {

    private Pane backgroundPane;
    private Text popupMessage;
    private Button button;

    public Popup(String textMessage, String buttonText, Runnable buttonAction) {
        super();
        super.alignmentProperty().set(javafx.geometry.Pos.CENTER);
        initPopupWindow();
        intiBackgroundPane();
        this.getChildren().add(backgroundPane);
        initPopupMessage(textMessage);
        initButton(buttonText, buttonAction);
        var vBox = new VBox();
        vBox.setAlignment(javafx.geometry.Pos.CENTER);
        vBox.getChildren().add(popupMessage);
        var pane = new Pane();
        pane.setPrefHeight(20.0);
        vBox.getChildren().add(pane);
        vBox.getChildren().add(button);
        super.getChildren().add(vBox);
        this.toFront();
    }

    private void initButton(String buttonText, Runnable buttonAction) {
        button = new Button(buttonText);
        button.setMnemonicParsing(false);
        button.setOnAction(event -> {
            buttonAction.run();
            this.hide();
        });
    }

    private void initPopupMessage(String textMessage) {
        popupMessage = new Text();
        popupMessage.setFill(Color.WHITE);
        popupMessage.setStrokeType(StrokeType.OUTSIDE);
        popupMessage.setStrokeWidth(0.0);
        popupMessage.setText(textMessage);
        // add padding to bot
        popupMessage.baselineOffsetProperty().add(20.0);
    }

    private void intiBackgroundPane() {
        backgroundPane = new Pane();
        backgroundPane.setLayoutX(-83.0);
        backgroundPane.setLayoutY(-58.0);
        backgroundPane.setOpacity(0.40);
        backgroundPane.setPrefHeight(249.0);
        backgroundPane.setPrefWidth(340.0);
        backgroundPane.setStyle("-fx-background-color: black;");
    }

    private void initPopupWindow() {
        this.setLayoutX(84.0);
        this.setLayoutY(59.0);
        this.setPrefHeight(123.0);
        this.setPrefWidth(170.0);
        this.setStyle(
                "-fx-background-radius: 10; -fx-border-radius: 10; -fx-border-color: gray; -fx-background-image: url("
                        + LinkManager.BACKGROUND_IMAGE_URL + ");");
        this.setVisible(false);
    }

    public void show() {
        this.setVisible(true);
        this.toFront();
    }

    public void hide() {
        this.setVisible(false);
    }

    public void showWithMessage(String message) {
        popupMessage.setText(message);
        this.setVisible(true);
    }
}
