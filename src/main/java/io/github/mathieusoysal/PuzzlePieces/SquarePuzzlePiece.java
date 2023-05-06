package io.github.mathieusoysal.PuzzlePieces;

import io.github.mathieusoysal.Cell;
import io.github.mathieusoysal.LinkManager;

final class SquarePuzzlePiece implements PuzzlePieceInterface {

    @Override
    public boolean canPut(int columnIndex, int rowIndex) {
        return rowIndex < 3 && columnIndex < 6;
    }

    @Override
    public Cell[] generate(int columnIndex, int rowIndex, Cell[][] cells) {
        Cell[] squareCells = new Cell[4];
        squareCells[0] = outOfBoundProtection(columnIndex, rowIndex, cells);
        squareCells[1] = outOfBoundProtection(columnIndex + 1, rowIndex, cells);
        squareCells[2] = outOfBoundProtection(columnIndex, rowIndex + 1, cells);
        squareCells[3] = outOfBoundProtection(columnIndex + 1, rowIndex + 1, cells);
        return squareCells;
    }

    @Override
    public String getPictureUrl() {
        return LinkManager.SQUARE_PICTURE_URL;
    }

    @Override
    public double getWidth() {
        return 2;
    }

}
