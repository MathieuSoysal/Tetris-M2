package io.github.mathieusoysal;

import java.io.IOException;

import io.github.mathieusoysal.PuzzlePieces.PuzzlePiece;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

public class PrimaryController {

    @FXML
    Pane body;

    @FXML
    ImageView imagePuzzle;

    @FXML
    GridPane grid;

    Cell[][] cells = new Cell[7][4];

    public void initialize() {
        for (int columnIndex = 0; columnIndex < 7; columnIndex++) {
            for (int rowIndex = 0; rowIndex < 4; rowIndex++) {
                Pane pane = new Pane();
                pane.setPrefSize(100, 100);
                pane.setStyle("-fx-background-color: none;");
                grid.add(pane, columnIndex, rowIndex);
                cells[columnIndex][rowIndex] = new Cell(pane, columnIndex, rowIndex);
            }
        }

        for (int columnIndex = 0; columnIndex < 7; columnIndex++) {
            for (int rowIndex = 0; rowIndex < 4; rowIndex++) {
                cells[columnIndex][rowIndex].setPuzzlesShapes(cells);
            }
        }

        imagePuzzle.setImage(new Image(LinkManager.INVERSED_L_PICTURE_URL));
        imagePuzzle.setDisable(true);
        imagePuzzle.setVisible(false);
    }

    public void becomeCellWhite(int x, int y) throws IOException {
        grid.getChildren().get(x + y * 6).setStyle("-fx-background-color: rgba(255, 255, 255, 0.5);");
    }

    @FXML
    void becomeWhite() throws IOException {
        becomeCellWhite(1, 0);
        becomeCellWhite(0, 0);
        becomeCellWhite(2, 0);
        becomeCellWhite(1, 1);
    }

    @FXML
    void mouseMouve(MouseEvent mouseEvent) {
        imagePuzzle.setLayoutX(mouseEvent.getSceneX());
        imagePuzzle.setLayoutY(mouseEvent.getSceneY() - 20);
        imagePuzzle.toFront();
    }

    @FXML
    private void switchToSecondary() throws IOException {
        App.setRoot("secondary");
    }

    @FXML
    private void getRandomPuzzleKind() {
        var randomPuzzleKind = PuzzlePiece.getRandomPuzzleKind();
        Cursor.setPuzzleKind(randomPuzzleKind);
        imagePuzzle.setImage(new Image(randomPuzzleKind.getPictureUrl()));
        imagePuzzle.setVisible(true);
        imagePuzzle.setFitWidth(randomPuzzleKind.getWidth());
        App.disableCursor();
    }
}
