package io.github.mathieusoysal;

import io.github.mathieusoysal.PuzzlePieces.PuzzlePiece;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

public class SceneController {

    @FXML
    Pane body;

    @FXML
    ImageView imagePuzzle;

    @FXML
    GridPane grid;

    @FXML
    Text nbTurn;

    Cell[][] cells = new Cell[7][4];

    public void initialize() {
        initBoard();
        body.getChildren().add(new CloseButton());

        imagePuzzle.setImage(new Image(LinkManager.INVERSED_L_PICTURE_URL));
        imagePuzzle.setDisable(true);
        imagePuzzle.setVisible(false);
    }

    private void initBoard() {
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
    }

    @FXML
    void mouseMouve(MouseEvent mouseEvent) {
        imagePuzzle.setLayoutX(mouseEvent.getSceneX());
        imagePuzzle.setLayoutY(mouseEvent.getSceneY() - 25);
        imagePuzzle.toFront();
    }

    @FXML
    private void getRandomPuzzleKind() {
        var randomPuzzleKind = PuzzlePiece.getRandomPuzzleKind();
        Cursor.setPuzzleKind(randomPuzzleKind);
        imagePuzzle.setImage(new Image(randomPuzzleKind.getPictureUrl()));
        imagePuzzle.setVisible(true);
        imagePuzzle.setFitWidth(randomPuzzleKind.getWidth());
        Game.getGameInstance().incrementNbTurn();
        App.disableCursor();
    }

    public void updateNbTurn(String newNbTurn) {
        nbTurn.setText(newNbTurn);
    }
}
