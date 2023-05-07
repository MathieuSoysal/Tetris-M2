package io.github.mathieusoysal;

import io.github.mathieusoysal.PuzzlePieces.PuzzlePiece;

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

    public void addPuzzlePiece(PuzzlePiece puzzlePiece) {
        nbUsedCells += puzzlePiece.getSize();
    }

    public boolean isGameFinished() {
        return nbUsedCells == NB_CELLS;
    }

    public static Game getGameInstance() {
        if (singletonGame == null) {
            singletonGame = new Game();
        }
        return singletonGame;
    }

}
