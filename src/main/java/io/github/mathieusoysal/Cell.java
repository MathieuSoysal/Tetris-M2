package io.github.mathieusoysal;

import java.util.Arrays;

import javafx.scene.layout.Pane;

public class Cell {
    public static final int CELL_SIZE = 33;

    Pane pane;
    int columnIndex;
    int rowIndex;
    boolean isUsed;

    Cell[] stickCells = new Cell[3];
    Cell[] squareCells = new Cell[4];
    Cell[] bigRectangleCells = new Cell[6];
    Cell[] lCells = new Cell[3];
    Cell[] inversedLCells = new Cell[3];
    Cell[] zCells = new Cell[4];

    Cell[] coloredCells = new Cell[0];

    public Cell(Pane pane, int columnIndex, int rowIndex) {
        this.pane = pane;
        this.columnIndex = columnIndex;
        this.rowIndex = rowIndex;
        this.isUsed = false;

        pane.setOnMouseEntered(e -> {
            Cell[] cellsToColorOnWhite = new Cell[0];
            Cell[] cellsToColorOnRed = new Cell[0];
            switch (Cursor.getPuzzleKind()) {
                case STICK:
                    if (ShapeGenerator.canPutStick(columnIndex, rowIndex))
                        cellsToColorOnWhite = stickCells;
                    else
                        cellsToColorOnRed = stickCells;
                    break;
                case SQUARE:
                    if (ShapeGenerator.canPutSquare(columnIndex, rowIndex))
                        cellsToColorOnWhite = squareCells;
                    else
                        cellsToColorOnRed = squareCells;
                    break;
                case BIG_RECTANGLE:
                    if (ShapeGenerator.canPutBigRectangle(columnIndex, rowIndex))
                        cellsToColorOnWhite = bigRectangleCells;
                    else
                        cellsToColorOnRed = bigRectangleCells;
                    break;
                case L:
                    if (ShapeGenerator.canPutL(columnIndex, rowIndex))
                        cellsToColorOnWhite = lCells;
                    else
                        cellsToColorOnRed = lCells;
                    break;
                case INVERSED_L:
                    if (ShapeGenerator.canPutInversedL(columnIndex, rowIndex))
                        cellsToColorOnWhite = inversedLCells;
                    else
                        cellsToColorOnRed = inversedLCells;
                    break;
                case Z:
                    if (ShapeGenerator.canPutZ(columnIndex, rowIndex))
                        cellsToColorOnWhite = zCells;
                    else
                        cellsToColorOnRed = zCells;
                    break;
                case MINI_SQUARE:
                    cellsToColorOnWhite = new Cell[] { this };
                    break;
                default:
                    break;
            }

            if (cellsToColorOnWhite.length == 0 && cellsToColorOnRed.length != 0) {

                cellsToColorOnRed = Arrays.stream(cellsToColorOnRed).filter(cell -> cell != null).toArray(Cell[]::new);
                for (Cell cell : cellsToColorOnRed) {
                    if (cell != null)
                        cell.becomeRed();
                }

                coloredCells = cellsToColorOnRed;
            } else {
                for (Cell cell : cellsToColorOnWhite) {
                    cell.becomeWhite();
                }
                coloredCells = cellsToColorOnWhite;
            }
        });

        pane.setOnMouseExited(e -> {
            for (Cell cell : coloredCells) {
                cell.becomeBlack();
            }
        });

        pane.setOnMouseClicked(e -> {
            App.addPuzzlePiece(Cursor.getPuzzleKind(), pane.getLayoutX() + 15, pane.getLayoutY() + 50);
        });

    }

    public void setPuzzlesShapes(Cell[][] cells) {
        bigRectangleCells = ShapeGenerator.generateBigRectangle(columnIndex, rowIndex, cells);
        lCells = ShapeGenerator.generateL(columnIndex, rowIndex, cells);
        inversedLCells = ShapeGenerator.generateInversedL(columnIndex, rowIndex, cells);
        squareCells = ShapeGenerator.generateSquare(columnIndex, rowIndex, cells);
        zCells = ShapeGenerator.generateZ(columnIndex, rowIndex, cells);
        stickCells = ShapeGenerator.generateStick(columnIndex, rowIndex, cells);
    }

    public void becomeBlack() {
        pane.setStyle("-fx-background-color: rgba(0, 0, 0, 0.50);");
    }

    public void becomeWhite() {
        pane.setStyle("-fx-background-color: rgba(255, 255, 255, 0.25);");
    }

    public void becomeRed() {
        pane.setStyle("-fx-background-color: rgba(255, 0, 0, 0.25);");
    }

}
