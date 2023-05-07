package io.github.mathieusoysal.PuzzlePieces;

import io.github.mathieusoysal.Cell;
import javafx.scene.image.ImageView;

public interface PuzzlePieceInterface {

    double getWidth();

    String getPictureUrl();

    boolean canBePutedAt(int columnIndex, int rowIndex);

    Cell[] generate(int columnIndex, int rowIndex, Cell[][] cells);

    int getSize();

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
