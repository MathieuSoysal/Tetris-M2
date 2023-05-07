package io.github.mathieusoysal;

import javafx.event.EventHandler;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class CloseButton extends ImageView {

    public CloseButton() {
        super(LinkManager.CLOSE_BUTTON_PICTURE_URL);
        super.setLayoutX(314d);
        super.setLayoutY(-1d);
        super.setPreserveRatio(true);
        super.setOnMouseClicked(exitGame());
    }

    private EventHandler<? super MouseEvent> exitGame() {
        return mouseEvent -> {
            App.enableCursor();
            App.hiddenPuzzlePieaceView();
            System.exit(0);
        };
    }

}
