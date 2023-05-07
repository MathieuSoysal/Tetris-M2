package io.github.mathieusoysal.puzzle_pieces;

import java.security.SecureRandom;

import io.github.mathieusoysal.Cell;
import javafx.scene.image.ImageView;

public enum PuzzlePiece implements PuzzlePieceInterface {
    STICK(new StickPuzzlePiece()),
    SQUARE(new SquarePuzzlePiece()),
    L(new LPuzzlePiece()),
    INVERSED_L(new InversedLPuzzlePiece()),
    Z(new ZPuzzlePiece()),
    MINI_SQUARE(new MiniSquarePuzzlePiece()),
    NONE(null);

    public static PuzzlePiece getRandomPuzzlePiece() {
        int rand = new SecureRandom().nextInt(6);
        switch (rand) {
            case 0:
                return STICK;
            case 1:
                return SQUARE;
            case 2:
                return L;
            case 3:
                return INVERSED_L;
            case 4:
                return Z;
            case 5:
                return MINI_SQUARE;
            default:
                return NONE;
        }
    }

    private final PuzzlePieceInterface pzPiece;

    PuzzlePiece(PuzzlePieceInterface puzzlePiece) {
        this.pzPiece = puzzlePiece;
    }

    public double getWidth() {
        return pzPiece.getWidth() * Cell.CELL_SIZE;
    }

    public String getPictureUrl() {
        return pzPiece.getPictureUrl();
    }

    public boolean canBePutedAt(int columnIndex, int rowIndex) {
        return pzPiece.canBePutedAt(columnIndex, rowIndex);
    }

    public Cell[] generate(int columnIndex, int rowIndex, Cell[][] cells) {
        return pzPiece.generate(columnIndex, rowIndex, cells);
    }

    public ImageView getImageView() {
        return pzPiece.getImageView();
    }

    public int getSize() {
        return pzPiece.getSize();
    }

}
