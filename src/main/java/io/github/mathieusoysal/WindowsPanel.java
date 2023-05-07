package io.github.mathieusoysal;

import javafx.scene.effect.BlurType;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

public class WindowsPanel extends Pane {

    private Text title;

    private double xOffSet;
    private double yOffSet;

    public WindowsPanel() {
        super();
        initStyle();
        initTitle();
        super.getChildren().add(title);
        super.setEffect(new DropShadow(BlurType.GAUSSIAN,
                Color.rgb(0, 0, 0, 0.33), 25, 0.7, 5, 0));
        super.setOnMousePressed(mouseEvent -> {
            xOffSet = App.getStageX() - mouseEvent.getScreenX();
            yOffSet = App.getStageY() - mouseEvent.getScreenY();
        });

        super.setOnMouseDragged(mouseEvent -> {
            double deltaX = mouseEvent.getScreenX() + xOffSet;
            double deltaY = mouseEvent.getScreenY() + yOffSet;
            App.mouvWindow(deltaX, deltaY);
        });
    }

    private void initStyle() {
        super.setLayoutX(1d);
        super.setLayoutY(1d);
        super.setPrefHeight(25d);
        super.setPrefWidth(315d);
        super.setStyle(
                "-fx-background-image: url(https://user-images.githubusercontent.com/43273304/235619703-4edf8867-d5a8-44ea-ab27-57d2d79292f6.png); -fx-background-radius: 10; -fx-border-radius: 5; -fx-border-width: 1; -fx-border-color: gray;");
        super.toBack();
    }

    private void initTitle() {
        title = new Text();
        title.setFill(javafx.scene.paint.Color.WHITE);
        title.setLayoutX(114d);
        title.setLayoutY(17d);
        title.setStrokeType(javafx.scene.shape.StrokeType.INSIDE);
        title.setStrokeWidth(0d);
        title.setText("Puzzle du poisson");
        title.setFont(new javafx.scene.text.Font(11d));
    }

}
