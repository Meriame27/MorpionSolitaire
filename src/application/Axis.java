package application;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

public class Axis extends Canvas {

	private GameInterface gameModel;
    private final int NUMBER_OF_GRIDLINES = 20;
    final static int GRID_LINES_WIDTH = 40;
    private final int POINT_WIDTH = 15;

    public Axis() {
        setWidth(800);
        setHeight(800);
        setOnMouseClicked(this::handleMouseClick);
    }
    
    /**
     * This method is reserved for a future functionality.
     * @param hint A boolean indicator.
     */
    public void setHint(boolean hint) {
    }
    
    
    /**
     * Sets the game model associated with this canvas.
     * @param gameModel The game model to associate.
     */
    public void setGameModel(GameInterface gameModel) {
        this.gameModel = gameModel;
    }

    /**
     * Draws the grid, points, and crosses on the canvas.
     */
    public void drawGrid() {
        GraphicsContext gc = getGraphicsContext2D();
        gc.clearRect(0, 0, getWidth(), getHeight());
        gc.setStroke(Color.GRAY);

        for (int i = 0; i <= NUMBER_OF_GRIDLINES; ++i) {
            gc.strokeLine(i * GRID_LINES_WIDTH, 0, i * GRID_LINES_WIDTH, getHeight());
            gc.strokeLine(0, i * GRID_LINES_WIDTH, getWidth(), i * GRID_LINES_WIDTH);
        }

        gc.setStroke(Color.BLACK);
        for (Point p : gameModel.getGridPoints()) {
            double pointX = p.getX() * GRID_LINES_WIDTH;
            double pointY = p.getY() * GRID_LINES_WIDTH;

            double halfPointWidth = POINT_WIDTH / 2;
            gc.strokeLine(pointX - halfPointWidth, pointY - halfPointWidth, pointX + halfPointWidth, pointY + halfPointWidth);
            gc.strokeLine(pointX - halfPointWidth, pointY + halfPointWidth, pointX + halfPointWidth, pointY - halfPointWidth);
        }

        gc.setStroke(Color.RED);
        for (Point point : gameModel.getCrosses()) {
            double crossX = point.getX() * GRID_LINES_WIDTH;
            double crossY = point.getY() * GRID_LINES_WIDTH;
            double halfPointWidth = POINT_WIDTH / 2;
            gc.strokeLine(crossX - halfPointWidth, crossY - halfPointWidth, crossX + halfPointWidth, crossY + halfPointWidth);
            gc.strokeLine(crossX - halfPointWidth, crossY + halfPointWidth, crossX + halfPointWidth, crossY - halfPointWidth);
        }
    }

    /**
     * Handles mouse clicks on the canvas, identifies the clicked point and, if possible, places a cross.
     * @param event The mouse click event.
     */
    private void handleMouseClick(MouseEvent event) {
        double x = event.getX() / GRID_LINES_WIDTH;
        double y = event.getY() / GRID_LINES_WIDTH;

        int gridX = (int) x;
        int gridY = (int) y;

        Point clickedPoint = new Point(gridX, gridY);

        if (gameModel.canPlaceCross(clickedPoint)) {
            gameModel.placeCross(clickedPoint);
            drawGrid();
        }
    }
}
