package io.github.mathieusoysal;

import java.util.Arrays;
import java.util.EnumMap;
import java.util.Objects;
import java.util.stream.Stream;

import io.github.mathieusoysal.PuzzlePieces.PuzzlePiece;
import javafx.scene.layout.Pane;

public class Cell {
    public static final int CELL_SIZE = 33;

    private static boolean containsUsedCell(Cell... cells) {
        return Stream.of(cells).anyMatch(cell -> cell.isUsed);
    }

    Pane pane;
    int columnIndex;
    int rowIndex;

    boolean isUsed;

    EnumMap<PuzzlePiece, Cell[]> shapeCells = new EnumMap<>(PuzzlePiece.class);

    Cell[] coloredCells = new Cell[0];

    public Cell(Pane pane, int columnIndex, int rowIndex) {
        this.pane = pane;
        this.columnIndex = columnIndex;
        this.rowIndex = rowIndex;
        this.isUsed = false;

        pane.setOnMouseEntered(e -> {
            if (Cursor.getCurrentPuzzlePiece() == PuzzlePiece.NONE)
                return;
            Cell[] cellsToColorOnWhite = new Cell[0];
            Cell[] cellsToColorOnRed = new Cell[0];
            var currentPzPiece = Cursor.getCurrentPuzzlePiece();
            if (currentPzPiece.canPut(columnIndex, rowIndex) && !containsUsedCell(shapeCells.get(currentPzPiece)))
                cellsToColorOnWhite = shapeCells.get(currentPzPiece);
            else
                cellsToColorOnRed = shapeCells.get(currentPzPiece);
            if (cellsToColorOnWhite.length == 0 && cellsToColorOnRed.length != 0)
                colorOnRed(cellsToColorOnRed);
            else
                colorOnWhite(cellsToColorOnWhite);
        });

        pane.setOnMouseExited(e -> {
            for (Cell cell : coloredCells) {
                cell.becomeBlack();
            }
        });

        pane.setOnMouseClicked(e -> {
            if (Cursor.getCurrentPuzzlePiece() != PuzzlePiece.NONE
                    && Cursor.getCurrentPuzzlePiece().canPut(columnIndex, rowIndex)
                    && !containsUsedCell(shapeCells.get(Cursor.getCurrentPuzzlePiece()))) {
                Game.getGameInstance().addPuzzlePiece(Cursor.getCurrentPuzzlePiece());
                App.addPuzzlePiece(Cursor.getCurrentPuzzlePiece(), pane.getLayoutX() + 15, pane.getLayoutY() + 50);
                for (Cell cell : coloredCells)
                    cell.isUsed = true;
                Cursor.setPuzzleKind(PuzzlePiece.NONE);
                App.hiddenPuzzlePieaceView();
            }
        });

    }

    public void setPuzzlesShapes(Cell[][] cells) {
        for (PuzzlePiece puzzlePiece : PuzzlePiece.values())
            if (puzzlePiece != PuzzlePiece.NONE)
                shapeCells.put(puzzlePiece, puzzlePiece.generate(columnIndex, rowIndex, cells));
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

    private void colorOnWhite(Cell[] cellsToColorOnWhite) {
        for (Cell cell : cellsToColorOnWhite) {
            cell.becomeWhite();
        }
        coloredCells = cellsToColorOnWhite;
    }

    private void colorOnRed(Cell[] cellsToColorOnRed) {
        cellsToColorOnRed = Arrays.stream(cellsToColorOnRed).filter(Objects::nonNull).toArray(Cell[]::new);
        for (Cell cell : cellsToColorOnRed)
            if (cell != null)
                cell.becomeRed();
        coloredCells = cellsToColorOnRed;
    }

}
