package io.github.mathieusoysal;

import javafx.scene.image.ImageView;

public enum PuzzlePiece {
    STICK(LinkManager.STICK_PICTURE_URL, 1),
    SQUARE(LinkManager.SQUARE_PICTURE_URL, 2),
    L(LinkManager.L_PICTURE_URL, 2),
    INVERSED_L(LinkManager.INVERSED_L_PICTURE_URL, 2),
    Z(LinkManager.Z_PICTURE_URL, 3),
    BIG_RECTANGLE("", 3),
    MINI_SQUARE(LinkManager.MINI_SQUARE_PICTURE_URL, 1);

    private final String pictureURL;

    private final double width;

    PuzzlePiece(String prictureURL, int nbCellsOnWidht) {
        this.pictureURL = prictureURL;
        this.width = nbCellsOnWidht * Cell.CELL_SIZE;
    }

    public String getPictureUrl() {
        return pictureURL;
    }

    public double getWidth() {
        return width;
    }

    public ImageView getImageView() {
        ImageView imageView = new ImageView(pictureURL);
        imageView.setPreserveRatio(true);
        imageView.setFitWidth(width);
        return imageView;
    }

    public static PuzzlePiece getRandomPuzzleKind() {
        int rand = (int) (Math.random() * 6);
        switch (rand) {
            case 0:
                return STICK;
            case 1:
                return SQUARE;
            case 2:
                return L;
            case 3:
                return INVERSED_L;
            case 4:
                return Z;
            case 5:
                return MINI_SQUARE;
            default:
                return BIG_RECTANGLE;
        }
    }
}
