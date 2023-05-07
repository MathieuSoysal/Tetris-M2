package io.github.mathieusoysal.PuzzlePieces;

import io.github.mathieusoysal.Cell;
import io.github.mathieusoysal.LinkManager;

final class ZPuzzlePiece implements PuzzlePieceInterface {

    @Override
    public boolean canBePutedAt(int columnIndex, int rowIndex) {
        return rowIndex < 3 && columnIndex < 5;
    }

    @Override
    public Cell[] generate(int columnIndex, int rowIndex, Cell[][] cells) {
        Cell[] zCells = new Cell[4];
        zCells[0] = outOfBoundProtection(columnIndex, rowIndex, cells);
        zCells[1] = outOfBoundProtection(columnIndex + 1, rowIndex, cells);
        zCells[2] = outOfBoundProtection(columnIndex + 1, rowIndex + 1, cells);
        zCells[3] = outOfBoundProtection(columnIndex + 2, rowIndex + 1, cells);
        return zCells;
    }

    @Override
    public String getPictureUrl() {
        return LinkManager.Z_PICTURE_URL;
    }

    @Override
    public double getWidth() {
        return 3;
    }

    @Override
    public int getSize() {
        return 4;
    }

}
