package io.github.mathieusoysal.PuzzlePieces;

import io.github.mathieusoysal.Cell;
import io.github.mathieusoysal.LinkManager;

final class InversedLPuzzlePiece implements PuzzlePieceInterface {

    @Override
    public boolean canPut(int columnIndex, int rowIndex) {
        return rowIndex < 3 && columnIndex < 6;
    }

    @Override
    public Cell[] generate(int columnIndex, int rowIndex, Cell[][] cells) {
        Cell[] inversedLCells = new Cell[3];
        inversedLCells[1] = outOfBoundProtection(columnIndex, rowIndex, cells);
        inversedLCells[0] = outOfBoundProtection(columnIndex + 1, rowIndex, cells);
        inversedLCells[2] = outOfBoundProtection(columnIndex + 1, rowIndex + 1, cells);
        return inversedLCells;
    }

    @Override
    public String getPictureUrl() {
        return LinkManager.INVERSED_L_PICTURE_URL;
    }

    @Override
    public double getWidth() {
        return 2;
    }

}
