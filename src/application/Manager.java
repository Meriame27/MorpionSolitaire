package application;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

public class Manager implements EventHandler<ActionEvent> {
    private GameInterface gameModel;
    private GameScreen gameView;

    public Manager(GameScreen gameView, GameInterface gameModel) {
        this.gameView = gameView;
        this.gameModel = gameModel;

        gameView.getMenuView().getRestart().setOnAction(event -> handleRestartButton());
        gameView.getGridView().setOnMouseClicked(this::handleGridClick);
    }

    /**
     * Handles the specified action event.
     * 
     * @param event The action event.
     */
    public void handle(ActionEvent event) {
        gameView.updateView();
    }

    /**
     * Handles the restart button click event.
     */
    private void handleRestartButton() {
        gameModel.restartGame();
        gameView.updateView();
    }
    
    /**
     * Handles the grid click event.
     * 
     * @param event The mouse event associated with the grid click.
     */

    private void handleGridClick(MouseEvent event) {
        double x = event.getX();
        double y = event.getY();

        double gridLinesWidth = Axis.GRID_LINES_WIDTH;
        int gridX = (int) (x / gridLinesWidth);
        int gridY = (int) (y / gridLinesWidth);

        gameModel.placePoint(gridX, gridY);
        gameView.getMenuView().setActualScore(gameModel.getListOfAlignment().size());
        gameView.updateView();
    }
}
