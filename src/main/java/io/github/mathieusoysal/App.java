package io.github.mathieusoysal;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;

    private static PrimaryController primaryController;

    @Override
    public void start(Stage stage) throws IOException {
        stage.initStyle(StageStyle.UNDECORATED);

        stage.setResizable(false);
        stage.setTitle("Puzzle du poisson");

        FXMLLoader loader = new FXMLLoader(getClass().getResource("primary.fxml"));
        Parent root = loader.load();

        primaryController = loader.getController();
        primaryController.initialize();

        scene = new Scene(root);
        stage.setScene(scene);
        stage.getScene().setCursor(Cursor.cursor(
                "https://th.bing.com/th/id/R.7aecd6f0b9897503dedfa2683a0771ed?rik=D5U6EOHEkVcodw&riu=http%3a%2f%2fwww.rw-designer.com%2fcursor-view%2f21962.png&ehk=90LHbW%2bzLbs2u3%2b8wHxPpVn6i%2b4KR690KVeZoMVv9Mw%3d&risl=&pid=ImgRaw&r=0"));
        stage.show();

    }

    // The image follow the mouse

    static void disableCursor() {
        scene.setCursor(Cursor.NONE);
    }

    static void enableCursor() {
        scene.setCursor(Cursor.cursor(LinkManager.CURSOR_PICTURE_URL));
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    static void addElementToRoot(Node node) {
        scene.getRoot().getChildrenUnmodifiable().add(node);
    }

    static void addPuzzlePiece(PuzzlePiece puzzlePiece, double x, double y) {
        var image = new ImageView(puzzlePiece.getPictureUrl());
        image.setLayoutX(x);
        image.setLayoutY(y);
        image.setDisable(true);
        image.setPreserveRatio(true);
        image.setFitWidth(puzzlePiece.getWidth());
        primaryController.body.getChildren().add(image);
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }

}