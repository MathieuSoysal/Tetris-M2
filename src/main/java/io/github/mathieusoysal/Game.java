package io.github.mathieusoysal;

import io.github.mathieusoysal.puzzle_pieces.PuzzlePiece;

public class Game {

    private static Game singletonGame;

    private int nbTurns;
    private PuzzlePiece selectedPuzzlePiece;
    private int nbUsedCells;
    private static final int NB_CELLS = 7 * 4;

    public Game() {
        nbTurns = 0;
        selectedPuzzlePiece = PuzzlePiece.NONE;
    }

    public void incrementNbTurn() {
        nbTurns++;
        App.getSceneController().updateNbTurn(nbTurns + "");
    }

    public void addPuzzlePiece(PuzzlePiece puzzlePiece, double x, double y) {
        nbUsedCells += puzzlePiece.getSize();
        App.addPuzzlePiece(puzzlePiece, x + 15, y + 50);
        resetSelectedPuzzlePiece();
        App.hiddenPuzzlePieaceView();
        if (isGameFinished()) {
            App.getSceneController().showWinMessage();
        }
    }

    public void reset() {
        nbTurns = 0;
        nbUsedCells = 0;
        App.getSceneController().updateNbTurn(nbTurns + "");
        App.removePuzzlePieces();
    }

    public boolean isGameFinished() {
        return nbUsedCells == NB_CELLS;
    }

    public PuzzlePiece getCurrentPuzzlePiece() {
        return selectedPuzzlePiece;
    }

    public void selectNewPuzzlePiece() {
        selectedPuzzlePiece = PuzzlePiece.getRandomPuzzlePiece();
    }

    public void resetSelectedPuzzlePiece() {
        selectedPuzzlePiece = PuzzlePiece.NONE;
    }

    public static Game getGameInstance() {
        if (singletonGame == null) {
            singletonGame = new Game();
        }
        return singletonGame;
    }

}
