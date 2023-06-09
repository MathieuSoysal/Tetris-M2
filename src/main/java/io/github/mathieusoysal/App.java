package io.github.mathieusoysal;

import java.io.IOException;
import java.util.ArrayList;

import io.github.mathieusoysal.puzzle_pieces.PuzzlePiece;
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

    private static SceneController sceneController;

    private static Stage stage;

    public static void main(String[] args) {
        launch();
    }

    // The image follow the mouse

    public static SceneController getSceneController() {
        return sceneController;
    }

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

    static void hiddenPuzzlePieaceView() {
        scene.setCursor(Cursor.cursor(LinkManager.CURSOR_PICTURE_URL));
        sceneController.imagePuzzle.setVisible(false);
    }

    private static ArrayList<ImageView> puzzlePieces = new ArrayList<>();

    static void addPuzzlePiece(PuzzlePiece puzzlePiece, double x, double y) {
        var image = new ImageView(puzzlePiece.getPictureUrl());
        image.setLayoutX(x);
        image.setLayoutY(y);
        image.setDisable(true);
        image.setPreserveRatio(true);
        image.setFitWidth(puzzlePiece.getWidth());
        puzzlePieces.add(image);
        sceneController.body.getChildren().add(image);
    }

    public static void removePuzzlePieces() {
        sceneController.body.getChildren().removeAll(puzzlePieces);
        puzzlePieces.clear();
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    private static void initScene(Stage stage, Parent root) {
        scene = new Scene(root);
        stage.setScene(scene);
        stage.getScene().setCursor(Cursor.cursor(
                "https://th.bing.com/th/id/R.7aecd6f0b9897503dedfa2683a0771ed?rik=D5U6EOHEkVcodw&riu=http%3a%2f%2fwww.rw-designer.com%2fcursor-view%2f21962.png&ehk=90LHbW%2bzLbs2u3%2b8wHxPpVn6i%2b4KR690KVeZoMVv9Mw%3d&risl=&pid=ImgRaw&r=0"));
        stage.show();
    }

    private static void initSceneController(FXMLLoader loader) {
        sceneController = loader.getController();
        sceneController.initialize();
    }

    public static void mouvWindow(double x, double y) {
        stage.setX(x);
        stage.setY(y);
    }

    public static double getStageX() {
        return stage.getX();
    }

    public static double getStageY() {
        return stage.getY();
    }

    @Override
    public void start(Stage stage) throws IOException {
        initStage(stage);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("primary.fxml"));
        Parent root = loader.load();
        initSceneController(loader);
        initScene(stage, root);
    }

    private static void initStage(Stage stage) {
        App.stage = stage;
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setResizable(false);
        stage.setTitle("Puzzle du poisson");
    }

}