package application;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class MorpionSolitaire extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    /**
     * The start method is the main entry point for all JavaFX applications.
     * It sets up the stage, creates the necessary views and model for the game, and shows the primary stage.
     *
     * @param primaryStage the primary stage for this application, onto which
     * the application scene can be set. The primary stage will be embedded in
     * the browser if the application was launched as an applet.
     * Stages created by the application will be standalone, but still a child
     * of the primary stage.
     */
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Morpion Solitaire 5T");
        primaryStage.setWidth(900);
        primaryStage.setHeight(600);

        Screen menuView = new Screen();
        BorderPane root = new BorderPane();
        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
        scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());

        menuView.getGameNameLabel().setText("5T Version");
        primaryStage.setScene(scene);
        GameInterface gameModel = new Implementation5T();

        GameScreen gameView = new GameScreen(menuView, gameModel);

        HBox hbox = new HBox();
        hbox.getChildren().addAll(menuView.getRestart(), menuView.getChangeGameButton(),
                menuView.getRandomSolveButton(), menuView.getCancelPlayButton());

        

        root.setTop(hbox);
        root.setCenter(gameView);
        root.setBottom(menuView);

        primaryStage.setScene(scene);
        primaryStage.show();

        gameView.updateView();
    }
}
