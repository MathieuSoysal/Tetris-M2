package io.github.mathieusoysal;

import io.github.mathieusoysal.PuzzlePieces.PuzzlePiece;

public class Cursor {
    private static PuzzlePiece puzzleKind = PuzzlePiece.NONE;

    public static PuzzlePiece getCurrentPuzzlePiece() {
        return puzzleKind;
    }

    public static void setPuzzleKind(PuzzlePiece puzzleKind) {
        Cursor.puzzleKind = puzzleKind;
    }
}
