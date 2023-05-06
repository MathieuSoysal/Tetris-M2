package io.github.mathieusoysal;

public class ShapeGenerator {

    // Utility class, no constructor
    private ShapeGenerator() {
    }

    public static boolean canPutStick(int columnIndex, int rowIndex) {
        return rowIndex < 2;
    }

    public static boolean canPutSquare(int columnIndex, int rowIndex) {
        return rowIndex < 3 && columnIndex < 6;
    }

    public static boolean canPutBigRectangle(int columnIndex, int rowIndex) {
        return rowIndex < 3 && columnIndex < 5;
    }

    public static boolean canPutL(int columnIndex, int rowIndex) {
        return rowIndex < 3 && columnIndex < 6;
    }

    public static boolean canPutInversedL(int columnIndex, int rowIndex) {
        return rowIndex < 3 && columnIndex < 6;
    }

    public static boolean canPutZ(int columnIndex, int rowIndex) {
        return canPutBigRectangle(columnIndex, rowIndex);
    }

    public static Cell[] generateStick(int columnIndex, int rowIndex, Cell[][] cells) {
        Cell[] stickCells = new Cell[3];
        stickCells[0] = outOfBoundProtection(columnIndex, rowIndex, cells);
        stickCells[2] = outOfBoundProtection(columnIndex, rowIndex + 1, cells);
        stickCells[1] = outOfBoundProtection(columnIndex, rowIndex + 2, cells);
        return stickCells;
    }

    public static Cell[] generateSquare(int columnIndex, int rowIndex, Cell[][] cells) {
        Cell[] squareCells = new Cell[4];
        squareCells[0] = outOfBoundProtection(columnIndex, rowIndex, cells);
        squareCells[1] = outOfBoundProtection(columnIndex + 1, rowIndex, cells);
        squareCells[2] = outOfBoundProtection(columnIndex, rowIndex + 1, cells);
        squareCells[3] = outOfBoundProtection(columnIndex + 1, rowIndex + 1, cells);
        return squareCells;
    }

    public static Cell[] generateBigRectangle(int columnIndex, int rowIndex, Cell[][] cells) {
        Cell[] bigRectangleCells = new Cell[6];
        bigRectangleCells[0] = outOfBoundProtection(columnIndex, rowIndex, cells);
        bigRectangleCells[1] = outOfBoundProtection(columnIndex, rowIndex + 1, cells);
        bigRectangleCells[2] = outOfBoundProtection(columnIndex + 1, rowIndex, cells);
        bigRectangleCells[3] = outOfBoundProtection(columnIndex + 1, rowIndex + 1, cells);
        bigRectangleCells[4] = outOfBoundProtection(columnIndex + 2, rowIndex, cells);
        bigRectangleCells[5] = outOfBoundProtection(columnIndex + 2, rowIndex + 1, cells);
        return bigRectangleCells;
    }

    public static Cell[] generateL(int columnIndex, int rowIndex, Cell[][] cells) {
        Cell[] lCells = new Cell[3];
        lCells[1] = outOfBoundProtection(columnIndex, rowIndex, cells);
        lCells[0] = outOfBoundProtection(columnIndex, rowIndex + 1, cells);
        lCells[2] = outOfBoundProtection(columnIndex + 1, rowIndex + 1, cells);
        return lCells;
    }

    public static Cell[] generateInversedL(int columnIndex, int rowIndex, Cell[][] cells) {
        Cell[] inversedLCells = new Cell[3];
        inversedLCells[1] = outOfBoundProtection(columnIndex, rowIndex, cells);
        inversedLCells[0] = outOfBoundProtection(columnIndex + 1, rowIndex, cells);
        inversedLCells[2] = outOfBoundProtection(columnIndex + 1, rowIndex + 1, cells);
        return inversedLCells;
    }

    public static Cell[] generateZ(int columnIndex, int rowIndex, Cell[][] cells) {
        Cell[] zCells = new Cell[4];
        zCells[0] = outOfBoundProtection(columnIndex, rowIndex, cells);
        zCells[1] = outOfBoundProtection(columnIndex + 1, rowIndex, cells);
        zCells[2] = outOfBoundProtection(columnIndex + 1, rowIndex + 1, cells);
        zCells[3] = outOfBoundProtection(columnIndex + 2, rowIndex + 1, cells);
        return zCells;
    }

    private static Cell outOfBoundProtection(int columnIndex, int rowIndex, Cell[][] cells) {
        try {
            return cells[columnIndex][rowIndex];
        } catch (ArrayIndexOutOfBoundsException e) {
            return null;
        }
    }

}
