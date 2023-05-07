package io.github.mathieusoysal.puzzle_pieces;

import io.github.mathieusoysal.Cell;
import io.github.mathieusoysal.LinkManager;

final class StickPuzzlePiece implements PuzzlePieceInterface {

    @Override
    public boolean canBePutedAt(int columnIndex, int rowIndex) {
        return rowIndex < 2;
    }

    @Override
    public Cell[] generate(int columnIndex, int rowIndex, Cell[][] cells) {
        Cell[] stickCells = new Cell[3];
        stickCells[0] = outOfBoundProtection(columnIndex, rowIndex, cells);
        stickCells[2] = outOfBoundProtection(columnIndex, rowIndex + 1, cells);
        stickCells[1] = outOfBoundProtection(columnIndex, rowIndex + 2, cells);
        return stickCells;
    }

    @Override
    public String getPictureUrl() {
        return LinkManager.STICK_PICTURE_URL;
    }

    @Override
    public double getWidth() {
        return 1;
    }

    @Override
    public int getSize() {
        return 3;
    }

}
