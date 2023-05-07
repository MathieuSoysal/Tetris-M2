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

    private Game game;

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
        game = Game.getGameInstance();

        pane.setOnMouseEntered(e -> {
            if (game.getCurrentPuzzlePiece() == PuzzlePiece.NONE)
                return;
            Cell[] cellsToColorOnWhite = new Cell[0];
            Cell[] cellsToColorOnRed = new Cell[0];
            var currentPzPiece = game.getCurrentPuzzlePiece();
            if (currentPzPiece.canBePutedAt(columnIndex, rowIndex) && !containsUsedCell(shapeCells.get(currentPzPiece)))
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
            PuzzlePiece currentPzPiece = game.getCurrentPuzzlePiece();
            if (currentPzPiece != PuzzlePiece.NONE
                    && currentPzPiece.canBePutedAt(columnIndex, rowIndex)
                    && !containsUsedCell(shapeCells.get(currentPzPiece))) {
                game.addPuzzlePiece(currentPzPiece,pane.getLayoutX(), pane.getLayoutY());
                for (Cell cell : coloredCells)
                    cell.isUsed = true;
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
