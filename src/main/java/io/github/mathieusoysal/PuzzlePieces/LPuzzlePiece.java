package io.github.mathieusoysal.PuzzlePieces;

import io.github.mathieusoysal.Cell;
import io.github.mathieusoysal.LinkManager;

final class LPuzzlePiece implements PuzzlePieceInterface {

    @Override
    public boolean canPut(int columnIndex, int rowIndex) {
        return rowIndex < 3 && columnIndex < 6;
    }

    @Override
    public Cell[] generate(int columnIndex, int rowIndex, Cell[][] cells) {
        Cell[] lCells = new Cell[3];
        lCells[1] = outOfBoundProtection(columnIndex, rowIndex, cells);
        lCells[0] = outOfBoundProtection(columnIndex, rowIndex + 1, cells);
        lCells[2] = outOfBoundProtection(columnIndex + 1, rowIndex + 1, cells);
        return lCells;
    }

    @Override
    public String getPictureUrl() {
        return LinkManager.L_PICTURE_URL;
    }

    @Override
    public double getWidth() {
        return 2;
    }

    @Override
    public int getSize() {
        return 3;
    }

}
