package io.github.mathieusoysal;

public class Cursor {
    private static PuzzlePiece puzzleKind;

    public static PuzzlePiece getPuzzleKind() {
        return puzzleKind;
    }

    public static void setPuzzleKind(PuzzlePiece puzzleKind) {
        Cursor.puzzleKind = puzzleKind;
    }
}
