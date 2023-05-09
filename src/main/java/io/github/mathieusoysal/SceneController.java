package io.github.mathieusoysal;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
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

    private Popup winnerPopup;

    private WindowsPanel windowsPanel;

    Cell[][] cells = new Cell[7][4];

    private Game game;

    public void initialize() {
        initBoard();
        game = Game.getGameInstance();
        windowsPanel = new WindowsPanel();
        var closeButton = new CloseButton();
        body.getChildren().addAll(windowsPanel, closeButton);
        initImagePuzzle();

        winnerPopup = new Popup("You win !", "Play again", () -> {
            game.reset();
            clearBoard();
            winnerPopup.hide();
        });
        body.getChildren().add(winnerPopup);
    }

    private void initImagePuzzle() {
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
        game.selectNewPuzzlePiece();
        var randomPuzzleKind = game.getCurrentPuzzlePiece();
        imagePuzzle.setImage(new Image(randomPuzzleKind.getPictureUrl()));
        imagePuzzle.setVisible(true);
        imagePuzzle.setFitWidth(randomPuzzleKind.getWidth());
        game.incrementNbTurn();
        App.disableCursor();
    }

    public void showWinMessage() {
        winnerPopup.show();
    }

    private void clearBoard() {
        for (int columnIndex = 0; columnIndex < 7; columnIndex++)
            for (int rowIndex = 0; rowIndex < 4; rowIndex++)
                cells[columnIndex][rowIndex].reset();
    }

    public void updateNbTurn(String newNbTurn) {
        nbTurn.setText(newNbTurn);
    }
}
