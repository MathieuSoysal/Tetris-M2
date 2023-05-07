package io.github.mathieusoysal.PuzzlePieces;

import io.github.mathieusoysal.Cell;
import io.github.mathieusoysal.LinkManager;

final class MiniSquarePuzzlePiece implements PuzzlePieceInterface {

    @Override
    public boolean canBePutedAt(int columnIndex, int rowIndex) {
        return true;
    }

    @Override
    public Cell[] generate(int columnIndex, int rowIndex, Cell[][] cells) {
        return new Cell[] { outOfBoundProtection(columnIndex, rowIndex, cells) };
    }

    @Override
    public String getPictureUrl() {
        return LinkManager.MINI_SQUARE_PICTURE_URL;
    }

    @Override
    public double getWidth() {
        return 1;
    }

    @Override
    public int getSize() {
        return 1;
    }

}
