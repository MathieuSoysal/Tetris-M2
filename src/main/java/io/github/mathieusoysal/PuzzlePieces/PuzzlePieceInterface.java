package io.github.mathieusoysal.PuzzlePieces;

import io.github.mathieusoysal.Cell;
import javafx.scene.image.ImageView;

public interface PuzzlePieceInterface {

    double getWidth();

    String getPictureUrl();

    boolean canPut(int columnIndex, int rowIndex);

    Cell[] generate(int columnIndex, int rowIndex, Cell[][] cells);

    default Cell outOfBoundProtection(int columnIndex, int rowIndex, Cell[][] cells) {
        if (columnIndex < 0 || rowIndex < 0 || columnIndex >= cells.length || rowIndex >= cells[0].length) {
            return null;
        }
        return cells[columnIndex][rowIndex];
    }

    default ImageView getImageView() {
        ImageView imageView = new ImageView(getPictureUrl());
        imageView.setPreserveRatio(true);
        imageView.setFitWidth(getWidth());
        return imageView;
    }

}
